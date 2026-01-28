package com.example.demo.service.impl;

import com.example.demo.domian.Category;
import com.example.demo.domian.dto.CategoryCreateDTO;
import com.example.demo.domian.dto.CategoryUpdateDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    //根据ID删除分类
    @Override
    public Boolean deleteById(Long id) {
        //直接调用递归删除方法
        return deleteCategoryAndChildren(id);
    }

    //查询顶级分类
    @Override
    public List<Category> findTopCategory() {
        return categoryMapper.findTopCategory();
    }

    //查询分类树
    @Override
    public List<Category> findCategoryTree() {
        //从数据库中获取所有分类数据列表
        List<Category> allCategoryList = categoryMapper.findAll();

        // 调用构建分类树形结构的方法, 从根节点(0)开始构建
        return buildCategoryTree(allCategoryList, 0L);
    }

    /**
     * 递归构建分类树形结构
     * @param allCategoryList 所有分类数据列表
     * @param parentId 当前层级的父分类ID
     * @return 构建好的树形结构分类列表
     */
    private List<Category> buildCategoryTree(List<Category> allCategoryList, Long parentId) {
        return allCategoryList.stream()
                //过滤出当前父节点的所有子分类
                .filter(category -> category.getParentId().equals(parentId))
                //为每个分类递归设置子分类
                .peek(category -> category.setChildren(buildCategoryTree(allCategoryList, category.getId())))
                //收集为List
                .collect(Collectors.toList());
    }

    //创建分类
    @Override
    public Boolean create(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        //将DTO属性拷贝到实体对象
        BeanUtils.copyProperties(categoryCreateDTO, category);

        //设置分类层级, 如果父分类ID为0, 则为一级分类, 否则层级为父分类层级+1
        if (category.getParentId() == 0) {
            category.setLevel(1);
        } else {
            Category parentCategory = categoryMapper.findById(category.getParentId());
            if (parentCategory != null) {
                category.setLevel(parentCategory.getLevel() + 1);
            } else {
                category.setLevel(1); //父分类不存在时设为一级分类
            }
        }

        //设置创建时间和更新时间
        Date now = new Date();
        category.setCreateTime(now);
        category.setUpdateTime(now);

        //执行数据库插入操作
        return categoryMapper.insert(category);
    }

    //更新分类
    @Override
    public Boolean update(CategoryUpdateDTO categoryUpdateDTO) {
        //根据ID查询现有分类
        Category category = categoryMapper.findById(categoryUpdateDTO.getId());
        if (category == null) {
            return false;
        }

        //将DTO属性拷贝到实体对象
        BeanUtils.copyProperties(categoryUpdateDTO, category);

        //重新计算层级
        if (category.getParentId() == 0) {
            category.setLevel(1);
        } else {
            Category parentCategory = categoryMapper.findById(category.getParentId());
            if (parentCategory != null) {
                category.setLevel(parentCategory.getLevel() + 1);
            } else {
                category.setLevel(1); //父分类不存在时设为一级分类
            }
        }

        //设置更新时间
        category.setUpdateTime(new Date());

        //执行数据库更新操作
        return categoryMapper.update(category);
    }

    //批量删除分类
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        boolean result = true;
        //遍历所有要删除的分类ID
        for (Long id : ids) {
            //直接调用删除分类及其子分类的方法
            if (!deleteCategoryAndChildren(id)) {
                result = false;
            }
        }
        return result;
    }

    //递归删除分类及其所有子分类
    private Boolean deleteCategoryAndChildren(Long id) {
        //首先查询并且递归所有子分类
        List<Category> children = categoryMapper.findByParentId(id);
        for (Category child : children) {
            deleteCategoryAndChildren(child.getId());
        }

        //删除完所有子分类后, 再删除当前分类
        return categoryMapper.deleteById(id);
    }
}
