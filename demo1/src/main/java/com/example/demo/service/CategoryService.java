package com.example.demo.service;

import com.example.demo.domian.Category;
import com.example.demo.domian.dto.CategoryCreateDTO;
import com.example.demo.domian.dto.CategoryUpdateDTO;

import java.util.List;

/**
 * 商品分类Service接口
 */
public interface CategoryService {
    /**
     * 查询所有分类
     */
    List<Category> findAll();

    /**
     * 根据ID查询分类
     */
    Category findById(Long id);

    /**
     * 根据父ID查询分类
     */
    List<Category> findByParentId(Long parentId);

    /**
     * 插入分类
     */
    Boolean insert(Category category);

    /**
     * 更新分类
     */
    Boolean update(Category category);

    /**
     * 根据ID删除分类
     */
    Boolean deleteById(Long id);

    /**
     * 查询顶级分类
     */
    List<Category> findTopCategory();

    /**
     * 查询分类树
     */
    List<Category> findCategoryTree();

    /**
     * 创建分类
     */
    Boolean create(CategoryCreateDTO categoryCreateDTO);

    /**
     * 更新分类
     */
    Boolean update(CategoryUpdateDTO categoryUpdateDTO);

    /**
     * 批量删除分类
     */
    Boolean deleteBatch(List<Long> ids);

}
