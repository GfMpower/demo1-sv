package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private String name; //分类名称
    private String parentId; //父分类ID
    private Integer level; //分类层级
    private Integer sort; //排序
    private String icon; //图标
    private String description; //描述
    private Integer status; //状态 0-禁用 1-正常
}
