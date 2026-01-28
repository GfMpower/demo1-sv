package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Permission extends  BaseEntity{
    private static final long serialVersionUID = 1L;

    private String name; //权限名称
    private String code; //权限编码
    private Integer type; //权限类型 1-菜单 2-按钮
    private Long parentId; //父权限ID
    private String path; //路由地址
    private String component; //组件路径
    private String icon; //图标
    private String sort; //排序
    private Integer status; //状态 0-禁用 1-正常
}
