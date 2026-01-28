package com.example.demo.domian.vo;

import lombok.Data;
/**
 * 登录响应VO
 */
@Data
public class LoginVo {
    private String token;// 响应token
    private Long userId;// 用户id
    private String username;// 用户名
    private String nickname;// 昵称
}
