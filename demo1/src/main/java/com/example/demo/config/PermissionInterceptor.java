package com.example.demo.config;


import com.example.demo.service.PermissionService;
import com.example.demo.utils.Result;
import com.example.demo.utils.UserContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 权限拦截器
 * 实现Spring Mvc拦截器, 用于在请求处理之前进行权限验证
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private PermissionService permissionService;

    //JSON处理器
    private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //如果不是映射到方法就直接通过 (比如静态资源)
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //首先检查方法上是否有RequiresPermissions注解
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions == null) {
            //如果该方法上没有这个权限验证注解, 再检查类级别是否有requiresPermissions注解
            requiresPermissions = handlerMethod.getBeanType().getAnnotation(RequiresPermissions.class);
        }

        //如果没有权限注解, 直接通过验证
        if (requiresPermissions == null) {
            return true;
        }

        //从用户上下文中获取当前用户ID
        Long userId = UserContext.getUserId();
        if (userId == null) {
            //用户未登录
            sentError(response, "用户未登录");
            return false;
        }

        //获取注解中定义的所需权限编码数组
        String[] permissions = requiresPermissions.value();

        //检查用户是否拥有任一所需权限
        for (String permission : permissions) {
            if (permissionService.hasPermission(userId, permission)) {
                return true;
            }
        }

        //用户没有任何所需权限, 返回权限不足错误
        sentError(response, "没有权限访问该资源");

        return false;
    }

    /**
     * 发送错误响应信息
     * @param response HTTP对象
     * @param massage 错误消息内容
     * @throws Exception 异常信息
     */
    private void sentError(HttpServletResponse response, String massage) throws Exception {
        //设置HTTP状态码为403(禁止访问)
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //设置响应内容为JSON
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //构建统一的错误响应结果
        Result<Object> result = Result.forbidden(massage);
        //将结果对象转换为JSON字符串写入响应
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
