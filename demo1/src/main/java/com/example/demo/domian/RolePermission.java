package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermission extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private Long roleId; //角色ID
    private Long permissionId; //权限ID
}
