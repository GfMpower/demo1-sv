package com.example.demo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {
    /**
     * 需要的权限编码数组
     * 支持多个权限编码, 用户需要拥有指定的权限才能访问
     * @return 权限编码数组
     */
    String[] value();
}
