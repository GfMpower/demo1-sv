package com.example.demo.service;

import com.example.demo.domian.User;
import com.example.demo.domian.dto.UserRegisterDTO;


import java.util.List;

/*
*用户Service接口
 */

public interface UserService {
    //查询用户列表
    List<User> findAll();

    //根据id查询用户
    User findById(Long id);
    //根据用户名查询用户
    User findByUserName(String username);
    //插入用户
    Boolean insert(User user);
    //更新用户
    Boolean update(User user);
    //删除用户
    Boolean deleteById(Long id);
    //用户注册
    Boolean register(UserRegisterDTO userRegisterDTO);
    //检查用户名是否已存在
    Boolean isUsernameExists(String username);
    //检查邮箱是否已存在
    Boolean isEmailExists(String email);
    //检查手机号是否已存在
    Boolean isPhoneExists(String phone);
}
