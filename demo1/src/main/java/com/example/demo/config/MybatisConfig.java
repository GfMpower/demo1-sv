package com.example.demo.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/*
* Mybatis配置类
* 用于配置和初始化 mybatis 框架在Spring Boot中的运行环境
* 使用@Configuration 注解，标识这是一个springboot类；（将MybatisConfig类声明为配置类）
* 使用@MapperScan("com.example.demo.mapper") 注解，自动扫描和注册mapper接口（指定mapper接口的扫描路径）
 */
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisConfig {
}
