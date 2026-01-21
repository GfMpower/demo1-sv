package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    /**
     * 创建CORS过滤器Bean
     * 这个过滤器会检查每个请求, 如果是跨域请求, 会添加必要的响应头
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        //1. 创建跨域配置对象, 设置各种规则
        CorsConfiguration config = new CorsConfiguration();

        //1.1 允许哪些网站访问我们的接口
        config.addAllowedOriginPattern("http://localhost:5173");

        //1.2 允许请求携带哪些头信息
        config.addAllowedHeader("*");

        //1.3 允许哪些HTTP方法(GET, POST等等)
        config.addAllowedMethod("*");

        //1.4 是否允许携带认证信息
        config.setAllowCredentials(true);

        //1.5 预检请求的有效期 (单位:秒)
        config.setMaxAge(3600L);

        //2. 创建配置源, 将上面的配置应用到所有接口地址
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        //2.1 将配置映射到所有路径
        source.registerCorsConfiguration("/**", config);

        //3. 创建并且返回跨域过滤器
        return new CorsFilter(source);
    }
}
