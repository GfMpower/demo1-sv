package com.example.demo.mapper;

import com.example.demo.domian.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface PermissionMapper {
    /**
     * 根据角色ID查询权限列表
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<Permission> findPermissionByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据用户ID查询权限列表
     * @param userId 用户ID
     * @return 权限列表
     */
    List<Permission> findPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据权限编码查询权限信息
     * @param code 权限编码
     * @return 权限信息
     */
    Permission findByCode(@Param("code") String code);
}
