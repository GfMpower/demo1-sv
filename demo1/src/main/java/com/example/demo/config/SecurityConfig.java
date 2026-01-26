package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

/**
 * Spring Security安全配置类
 * 配置应用程序的安全策略，包括认证、授权和CORS设置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 配置密码编码器
     * 使用BCrypt强哈希算法进行密码加密和验证
     *
     * @return BCryptPasswordEncoder实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置安全过滤器链
     * 定义HTTP请求的安全规则和策略
     *
     * @param http HttpSecurity对象，用于配置Web安全
     * @return 配置好的SecurityFilterChain
     * @throws Exception 配置过程中可能出现的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ 先配置 CORS
                .csrf(csrf -> csrf.disable()) // ✅ 禁用 CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ 无状态会话
                .authorizeHttpRequests(authz -> authz // ✅ 使用现代 API
                        .antMatchers("/", "/**").permitAll() // ✅ 明确开放路径
                        .anyRequest().authenticated() // ✅ 其他请求需认证
                );

        return http.build();
    }
    /**
     * 配置CORS（跨域资源共享）策略
     * 定义允许跨域访问的规则
     *
     * @return CorsConfigurationSource实例，包含CORS配置信息
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
