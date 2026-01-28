package com.example.demo.service.impl;

import com.example.demo.domian.User;
import com.example.demo.domian.dto.UserLoginDTO;
import com.example.demo.domian.dto.UserRegisterDTO;
import com.example.demo.domian.vo.LoginVo;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.BusinessException;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.PasswordEncoderUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/*
*用户Service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    //查询用户列表
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
    //根据用户id查询用户
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
    //用户注册
    @Override
    public Boolean register(UserRegisterDTO userRegisterDTO) {
        //1. 验证两次密码是否一致 - 确保用户输入密码没有错误
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new BusinessException("两次输入的密码不一致");
        }

        //2. 检查用户名是否已存在 - 防止用户名重复
        if (isUsernameExists(userRegisterDTO.getUsername())) {
            throw new BusinessException("用户名已存在");
        }

        //3. 检查邮箱是否已存在 - 防止邮箱重复
        if (isEmailExists(userRegisterDTO.getEmail())) {
            throw new BusinessException("邮箱已存在");
        }

        //4. 检查手机号是否已存在 - 防止手机号重复
        if (isPhoneExists(userRegisterDTO.getPhone())) {
            throw new BusinessException("手机号已存在");
        }

        //5. 创建用户对象 - 将DTO转换为实体对象
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);

        //6. 加密密码
        user.setPassword(PasswordEncoderUtil.encode(userRegisterDTO.getPassword()));

        //7. 设置默认状态
        user.setStatus(1);

        //8. 设置创建时间和更新时间
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);

        //7. 插入数据库
        return userMapper.insert(user);
    }

    //用户登录
    @Override
    public LoginVo login(UserLoginDTO userLoginDTO) {
        //1.根据用户名查找用户
        User user = userMapper.findByUserName(userLoginDTO.getUsername());
        if (user == null) {
            throw new BusinessException("用户名错误");
        }

        //2.验证密码
        if (!PasswordEncoderUtil.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        //3.检查用户状态
        if (user.getStatus() == 0) {
            throw new BusinessException("用户已被禁用");
        }

        //4. 生成JWT令牌
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());

        //5. 构造返回对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setUserId(user.getId());
        loginVo.setUsername(user.getUsername());
        loginVo.setNickname(user.getNickname());

        return loginVo;
    }

    //检查用户名是否已存在
    @Override
    public Boolean isUsernameExists(String username) {
            return userMapper.isUsernameExists(username);
    }
    //检查邮箱是否已存在
    @Override
    public Boolean isEmailExists(String email) {
        return userMapper.isEmailExists(email);
    }
    //检查手机号是否已存在
    @Override
    public Boolean isPhoneExists(String phone) {
        return userMapper.isPhoneExists(phone);
    }

    //根据用户ID查询用户信息 (包含角色信息)
    @Override
    public User findUserWithRolesById(Long userId) {
        User user = userMapper.findById(userId);
        if (user != null) {
            //查询用户的角色列表
            user.setRoles(roleMapper.findRolesByUserId(userId));
        }
        return user;
    }

}
