package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRole extends BaseEntity{
    private static final long serialVersionUID = 1L;

    private Long userId; //用户ID
    private Long roleId; //角色ID
}
