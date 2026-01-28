package com.example.demo.utils;
/**
 * 用户上下文类
 * 实现用户信息的线程安全存储与访问
 */
public class UserContext {
    //存储当前线程的用户ID, 确保线程安全
    private static final ThreadLocal<Long> userIdHolder = new ThreadLocal<>();
    //存储当前线程的用户名, 确保线程安全
    private static final ThreadLocal<String> usernameHolder = new ThreadLocal<>();

    /**
     * 设置当前线程的userId
     * @param userId 用户ID
     */
    public static void setUserId(Long userId) {
        userIdHolder.set(userId);
    }
    /**
     * 获取当前线程的userId
     * @return 用户ID, 如果未设置则返回null
     */
    public static long getUserId() {
        return userIdHolder.get();
    }

    /**
     * 设置当前线程的username
     * @param username 用户名
     */
    public static void setUsername(String username) {
        usernameHolder.set(username);
    }
    /**
     * 获取当前线程的username
     * @return 用户名, 如果未设置则返回null
     */
    public static String getUsername() {
        return usernameHolder.get();
    }
    /**
     * 清楚当前线程存储的用户信息
     * 防止内存泄漏
     */
    public static void clear() {
        userIdHolder.remove();
        usernameHolder.remove();
    }
}
