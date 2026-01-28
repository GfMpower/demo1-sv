package com.example.demo.domian.dto;

import lombok.Data;

@Data
public class CategoryUpdateDTO {
    private Long id;//分类ID
    private String name; //分类名称
    private Long parentId; //父分类ID
    private Integer level; //分类层级
    private Integer sort; //排序
    private String icon; //图标
    private String description; //描述
    private Integer status; //状态 0-禁用 1-正常
}
