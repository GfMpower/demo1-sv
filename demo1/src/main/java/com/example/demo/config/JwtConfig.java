package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT配置类
 * 用于读取和管理JWT相关的配置信息
 */
@Component
@ConfigurationProperties(prefix = "jwt") //绑定配置文件中的jwt的前缀属性
@Data
public class JwtConfig {
    /**
     * JWT加密密钥, 默认值为mySecretKey
     * 可通过配置文件中的jwt.secret属性覆盖
     */
    private String secret = "mySecretKey";
    /**
     * JWT过期时间(单位: 秒)
     */
    private long expiration = 86400;
    /**
     * HTTP请求头中鞋带的JWT字段名称
     */
    private String header = "Authorization";
}
