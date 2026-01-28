package com.example.demo.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;


/**
 * 当前用户工具类
 * 提供从HTTP请求中获取当前登录用户信息的静态方法
 */

public class CurrentUser {
    /**
     * 获取JWT令牌并且解析出用户ID
     * @return 用户ID, 如果无法获取请求或令牌失效则返回null
     */
    public static Long getCurrentUserId() {
        //获取当前请求的属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            //获取对象
            HttpServletRequest request = attributes.getRequest();
            //从请求头中获取Authorization字段
            String token = request.getHeader("Authorization");
            //检查token是否存在以Bearer开头
            if (token != null && token.startsWith("Bearer")) {
                //去除Bearer前缀, 获取纯token字符串
                token = token.substring(7);
                //使用JWT工具类从token中解析用户ID
                return JwtUtil.getUserIdFromToken(token);
            }
        }
        return null;
    }
    /**
     * 获取JWT令牌并且解析出用户名
     * @return 用户名, 如果无法获取请求或令牌失效则返回null
     */
    private static String getCurrentUsername() {
        //获取当前请求的属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            //获取对象
            HttpServletRequest request = attributes.getRequest();
            //从请求头中获取Authorization字段
            String token = request.getHeader("Authorization");
            //检查token是否存在以Bearer开头
            if (token != null && token.startsWith("Bearer")) {
                //去除Bearer前缀, 获取纯token字符串
                token = token.substring(7);
                //使用JWT工具类从token中解析用户名
                return JwtUtil.getUserNameFromToken(token);
            }

        }
        return null;
    }
}
