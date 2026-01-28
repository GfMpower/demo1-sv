package com.example.demo.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* Mybatis配置类
* 用于配置和初始化 mybatis 框架在Spring Boot中的运行环境
* 使用@Configuration 注解，标识这是一个springboot类；（将MybatisConfig类声明为配置类）
* 使用@MapperScan("com.example.demo.mapper") 注解，自动扫描和注册mapper接口（指定mapper接口的扫描路径）
 */
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor; //注入JWT拦截器

    /**
     * 添加拦截器配置
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册JWT拦截器, 配置拦截规则
        registry.addInterceptor(jwtInterceptor)
                //拦截所有以/api开头的请求路径
                .addPathPatterns("/api/**")
                //排除登录和注册接口
                .excludePathPatterns("/api/auth/login", "/api/auth/register");
    }
}
