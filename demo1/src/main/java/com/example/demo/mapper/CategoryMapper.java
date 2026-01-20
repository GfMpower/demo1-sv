package com.example.demo.mapper;

import com.example.demo.domian.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品分类Mapper接口
 */
@Mapper
public interface CategoryMapper extends com.example.demo.mapper.BaseMapper<Category> {
    //查询所有分类
    List<Category> findAll();

    //根据ID查询分类
    Category findById(Long id);

    //根据父ID查询分类
    List<Category> findByParentId(Long parentId);

    //插入分类
    Boolean insert(Category category);

    //更新分类
    Boolean update(Category category);

    //根据ID删除分类
    Boolean deleteById(Long id);

    //查询顶级分类
    List<Category> findTopCategory();
}
