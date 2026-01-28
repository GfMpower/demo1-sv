package com.example.demo.service.impl;


import com.example.demo.domian.Permission;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    //根据角色ID查询权限列表
    @Override
    public List<Permission> findPermissionByRoleId(Long roleId) {
        return permissionMapper.findPermissionByRoleId(roleId);
    }

    //根据用户ID查询权限列表
    @Override
    public List<Permission> findPermissionByUserId(Long userId) {
        return permissionMapper.findPermissionByUserId(userId);
    }

    //根据权限编码查询权限信息
    @Override
    public Permission findByCode(String code) {
        return permissionMapper.findByCode(code);
    }

    //检查用户是否拥有指定权限
    @Override
    public Boolean hasPermission(Long userId, String permissionCode) {
        List<Permission> permissions = permissionMapper.findPermissionByUserId(userId);
        return permissions.stream()
                .anyMatch(permission -> permission.getCode().equals(permissionCode));
    }
}
