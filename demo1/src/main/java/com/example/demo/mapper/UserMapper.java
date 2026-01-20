package com.example.demo.mapper;

import com.example.demo.domian.User;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface UserMapper {
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
}
