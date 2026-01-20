package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String username; //用户名
    private String password; //密码
    private String nickname; //昵称
    private String email; //邮箱
    private String phone; //手机号
    private String avatar; //头像
    private Integer status; //状态 0-禁用 1-正常
}
