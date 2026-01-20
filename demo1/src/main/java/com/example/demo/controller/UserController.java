package com.example.demo.controller;

/*
*用户控制类
 */

import com.example.demo.domian.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/page" )
    public Map<String,Object> getfindByPage(@RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return PageUtils.toPageResult(pageInfo);
    }


}
