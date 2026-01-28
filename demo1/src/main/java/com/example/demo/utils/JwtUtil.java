package com.example.demo.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {
    //JWT密钥
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //JWT过期时间(毫秒), 这里设置为24小时
    private static final long EXPIRATION = 24 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     * @param userId 用户ID
     * @param username 用户名
     * @return JWT令牌字符串
     */
    public static String generateToken(Long userId,String  username){
        // 获取当前时间
        Date now =new Date();
        //计算过期时间
        Date expriyDate = new Date(now.getTime() + EXPIRATION);
        //创建JWT声明（payload）
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        // 构建JWT并返回字符串
        return Jwts.builder()
                .setClaims(claims) //设置声明
                .setSubject(username)//
                .setIssuedAt(now) //设置签发时间
                .setExpiration(expriyDate) //设置过期时间
                .signWith(SECRET_KEY) //设置密钥
                .compact();//生成令牌字符串
    }

    /**
     * 解析JWT令牌
     * @param token JWT令牌字符串
     * @return 包含JWT中的所有声明信息
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) //设置签名密钥
                .build()
                .parseClaimsJws(token)//解析令牌
                .getBody(); //获取声明体
    }

    /**
     * 验证JWT令牌是否有效
     * @param token JWT令牌字符串
     * @return true表示令牌有效, false表示令牌无效或者已过期
     */
    public static Boolean validateToken(String token) {
        try {
            //尝试解析令牌, 如果解析成功则说明令牌有效
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) //设置签名密钥
                    .build()
                    .parseClaimsJws(token); //解析令牌
            return true;
        } catch (Exception e) {
            //解析过程中出现异常 说明令牌无效或者已过期
            return false;
        }
    }
    /**
     * 从JWT令牌中获取用户ID
     * @param token
     * @return
     */
    public static Long getUserId(String token){
        Claims claims = parseToken(token);
        //从声明中获取userId
        return Long.parseLong(claims.get("userId").toString());
    }

    /**
     * 从JWT令牌中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        Claims claims = parseToken(token);
        //从声明中获取userName
        return claims.get("username").toString();
    }
}
