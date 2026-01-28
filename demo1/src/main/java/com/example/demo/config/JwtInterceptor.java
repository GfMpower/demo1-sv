package com.example.demo.config;

import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Result;
import com.example.demo.utils.UserContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
/**
 * JWT拦截器
 * 用于验证请求中的JWT令牌有效性
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    //JSON序列化工具
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 请求预处理方法
     * @param request //请求对象
     * @param response //响应对象
     * @param handler//处理器对象
     * @return //放行结果
     * @throws Exception //抛出异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中的token
        String token = request.getHeader("Authorization");

        //获取请求url
        String requestURI = request.getRequestURI();

        //如果是登录或者注册接口, 直接放行(无需token验证)
        if (requestURI.contains("/api/auth/login") || requestURI.contains("/api/auth/register")) {
            return true;
        }

        //验证token是否存在并且格式正确
        if (token != null && token.startsWith("Bearer")) {
            //提取token
            token = token.substring(7);

            //验证token有效性
            if (JwtUtil.validateToken(token)) {
                //如果token有效, 设置用户上下文
                Long userId = JwtUtil.getUserIdFromToken(token);
                String username = JwtUtil.getUserNameFromToken(token);
                UserContext.setUserId(userId);
                UserContext.setUsername(username);
                return true;
            }
        }

        //token无效或者缺失, 返回401未授权错误
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        //构建响应输出
        PrintWriter writer = response.getWriter();
        Result<Object> result = Result.unaRequest("未授权, 请重新登录");
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();

        //拒绝请求
        return false;
    }
}
