package com.example.demo.controller;

/*
*用户控制类
 */

import com.example.demo.domian.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //查询用户列表
    @GetMapping
    public List<User> getfindAll() {
        return userService.findAll();
    }
    //根据id查询用户
    @GetMapping("/{id}")
    public User getfindById(@PathVariable Long id) {
        return userService.findById(id);
    }


}
