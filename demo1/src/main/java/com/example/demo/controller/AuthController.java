package com.example.demo.controller;

import com.example.demo.domian.dto.UserLoginDTO;
import com.example.demo.domian.dto.UserRegisterDTO;
import com.example.demo.domian.vo.LoginVo;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterDTO 用户在网页注册时填入的注册表单信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            //调用服务层注册方法
            Boolean success = userService.register(userRegisterDTO);
            if (success) {
                //注册成功
                return Result.success("注册成功~");
            } else {
                //注册失败
                return Result.error("注册失败");
            }
        } catch (Exception e) {
            //捕获异常并返回错误信息(如用户名已存在)
            return Result.error(e.getMessage());
        }
    }
    @PostMapping("/login")
    public Result<LoginVo> register(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            LoginVo loginVo = userService.login(userLoginDTO);
            return Result.success("登录成功", loginVo);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
