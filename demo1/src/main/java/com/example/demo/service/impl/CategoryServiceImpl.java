package com.example.demo.service.impl;

import com.example.demo.domian.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类Service实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    //查询所有分类
    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
    //根据ID查询分类
    @Override
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }
    //根据父ID查询分类

    @Override
    public List<Category> findByParentId(Long parentId) {
        return categoryMapper.findByParentId(parentId);
    }
    //插入分类
    @Override
    public Boolean insert(Category category) {
        return categoryMapper.insert(category);
    }
    //更新分类
    @Override
    public Boolean update(Category category) {
        return categoryMapper.update(category);
    }
    //删除分类
    @Override
    public Boolean deleteById(Long id) {
        return categoryMapper.deleteById(id);
    }
    //查询顶级分类
    @Override
    public List<Category> findTopCategory() {
        return categoryMapper.findTopCategory();
    }
}
