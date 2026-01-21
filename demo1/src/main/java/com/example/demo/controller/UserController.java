package com.example.demo.controller;

/*
*用户控制类
 */

import com.example.demo.domian.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //查询用户列表
    @GetMapping
    public Result<List<User>> getfindAll() {
        List<User> userList = userService.findAll();
        return Result.success(userList);
    }
    //根据id查询用户
    @GetMapping("/{id}")
    public Result<User> getfindById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null){
            return Result.success(user);
        }else {
            return Result.error(ResultCode.USER_NOT_EXIST);//用户不存在
        }
    }

    @GetMapping("/page" )
    public Result<Map<String,Object>> getfindByPage(@RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        Map<String, Object> pageResult = PageUtils.toPageResult(pageInfo);
        return Result.success(pageResult);
    }


}
