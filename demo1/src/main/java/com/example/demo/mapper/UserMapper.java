package com.example.demo.mapper;

import com.example.demo.domian.User;
import com.example.demo.domian.dto.UserLoginDTO;
import com.example.demo.domian.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserMapper extends com.example.demo.mapper.BaseMapper<User> {
    //查询用户列表
    List<User> findAll();
    //根据用户ID查询用户信息
    User findById(Long id);
    //根据用户名查询用户信息
    User findByUserName(String username);
    //插入用户
    Boolean insert(User user);
    //更新用户
    Boolean update(User user);
    //删除用户
    Boolean deleteById(Long id);
    //检查用户名是否已存在
    Boolean isUsernameExists(String username);
    //检查邮箱是否已存在
    Boolean isEmailExists(String email);
    //检查手机号是否已存在
    Boolean isPhoneExists(String phone);
}
