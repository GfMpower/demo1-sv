package com.example.demo.service;

import com.example.demo.domian.Permission;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 权限Service接口
 */
public interface PermissionService {
    //根据角色ID查询权限列表
    List<Permission> findPermissionByRoleId(@Param("roleId") Long roleId);
    //根据用户ID查询权限列表
    List<Permission> findPermissionByUserId(@Param("userId") Long userId);
    //根据权限编码查询权限信息
    Permission findByCode(@Param("code") String code);
    /**
     * 检查用户是否拥有指定权限
     * @param userId 用户ID
     * @param permissionCode 权限编码
     * @return 是否拥有权限
     */
    Boolean hasPermission(Long userId, String permissionCode);
}
