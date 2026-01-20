package com.example.demo.domian;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/*
* 基础实体类
* */

@Data//使用lombok注解，生成getter和setter方法
public class BaseEntity implements Serializable {
    //显示声明与劣化版本UID, 确保类兼容性
    private static final long serialVersionUID = 1L;

    private Long id;//主键ID
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
}
