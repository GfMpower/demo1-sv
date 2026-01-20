package com.example.demo.service.impl;

import com.example.demo.domian.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
*用户Service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    //查询用户列表
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
    //根据id查询用户
    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }
    //根据用户名查询用户
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
    //插入用户
    @Override
    public Boolean insert(User user) {
        return userMapper.insert(user);
    }
    //更新用户
    @Override
    public Boolean update(User user) {
        return userMapper.update(user);
    }
    //删除用户
    @Override
    public Boolean deleteById(Long id) {
        return userMapper.deleteById(id);
    }
}
