package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    /**
     * 对原始密码进行加密处理
     * @param password 原始密码(明文)
     * @return 加密后的密码(哈希值) , 包括算法版本 成本因子 盐值 哈希结果
     */
    public static String encode(String password) {
        return encoder.encode(password);
    }
    /**
     * 验证原始密码和加密密码是否匹配
     * @param rawPassword 用户输入的原始密码(明文)
     * @param encodePassword 加密后的密码(哈希值)
     * @return true-密码匹配 false-密码不匹配
     */
    public static boolean matches(String rawPassword, String encodePassword) {
        return encoder.matches(rawPassword, encodePassword);
    }
}
