package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 角色实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name; //角色名称
    private String code; //角色编码
    private String description; //角色描述
    private Integer status; //状态

    // 角色拥有的权限列表
    private List<Permission> permissions;
}
