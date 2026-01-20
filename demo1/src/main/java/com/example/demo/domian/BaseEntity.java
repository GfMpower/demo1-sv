package com.example.demo.domian;

import lombok.Data;

import java.util.Date;


/*
* 基础实体类
* */

@Data//使用lombok注解，生成getter和setter方法
public class BaseEntity {

    private Long id;//主键ID

    private Date createTime;//创建时间

    private Date updateTime;//修改时间


}
