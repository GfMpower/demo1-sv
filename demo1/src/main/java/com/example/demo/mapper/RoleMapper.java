package com.example.demo.mapper;

import com.example.demo.domian.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 角色Mapper
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据用户ID查询角色信息
     * @param userId 用户ID
     * @return 角色列表
     */
    List<Role> findRolesByUserId(@Param("userId") Long userId);
    /**
     * 根据角色ID查询角色信息
     * @param id 角色ID
     * @return 角色信息
     */
    Role findById(@Param("id") Long id);
}
