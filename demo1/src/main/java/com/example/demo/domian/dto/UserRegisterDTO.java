package com.example.demo.domian.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username; //用户名
    private String password; //密码
    private String confirmPassword; //确认密码
    private String nickname; //昵称
    private String email; //邮箱
    private String phone; //手机号
}
