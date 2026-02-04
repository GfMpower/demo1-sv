package com.example.demo.controller;

/*
*用户控制类
 */

import com.example.demo.domian.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    //分页查询
    @GetMapping("/page" )
    public Result<Map<String,Object>> getfindByPage(@RequestParam(defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        Map<String, Object> pageResult = PageUtils.toPageResult(pageInfo);
        return Result.success(pageResult);
    }

    //获取当前用户信息
    @GetMapping("/info")
    public Result<User> getCurrentUserInfo(){
        Long userId = UserContext.getUserId();
        if (userId != null){
            User user = userService.findById(userId);
            if (user != null){
                return Result.success(user);
            }
        }
        return Result.error(ResultCode.USER_NOT_EXIST);
    }

    //删除用户
    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        try {
            Boolean success = userService.deleteById(id);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //创建用户
    @PostMapping
    public Result<User> createUser(@RequestBody User user) {
        try {
            //设置创建时间和更新时间
            Date now = new Date();
            user.setCreateTime(now);
            user.setUpdateTime(now);
            //默认状态为正常
            if (user.getStatus() == null) {
                user.setStatus(1);
            }
            Boolean success = userService.insert(user);
            if (success) {
                return Result.success(user);
            } else {
                return Result.error("创建用户失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //更新用户
    @PutMapping
    public Result<User> updateUser(@RequestBody User user) {
        try {
            //设置更新时间
            Date now = new Date();
            user.setUpdateTime(now);
            Boolean success = userService.update(user);
            if (success) {
                return Result.success(user);
            } else {
                return Result.error("更新用户信息失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


}
