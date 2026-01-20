package com.example.demo.mapper;

import java.util.List;

/**
 * 通用Mapper接口
 */
public interface BaseMapper<T> {
    //查询所有
    List<T> findAll();
    //根据ID查询
    T findById(Long id);
    //插入
    Boolean insert(T entity);
    //更新
    Boolean update(T entity);
    //根据ID删除
    Boolean deleteById(Long id);
}