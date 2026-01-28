package com.example.demo.controller;

import com.example.demo.domian.Category;
import com.example.demo.domian.dto.CategoryCreateDTO;
import com.example.demo.domian.dto.CategoryUpdateDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理Controller
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 查询所有分类
     */
    @GetMapping
    public Result<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryService.findAll();
        return Result.success(categoryList);
    }
    /**
     * 查询分类树
     */
    @GetMapping("/tree")
    public Result<List<Category>> getCategoryTree(){
        List<Category> categoryTree = categoryService.findCategoryTree();
        return Result.success(categoryTree);
    }
    /**
     * 创建分类
     */
    @PostMapping
    public Result<Void> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO){
        try{
            Boolean success = categoryService.create(categoryCreateDTO);
            if (success){
                return Result.success("创建成功");
            } else {
                return Result.error("创建失败");
            }
        }catch(Exception e){
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新分类
     */
    @PutMapping
    public Result<Void> updateCategory(@RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        try {
            Boolean success = categoryService.update(categoryUpdateDTO);
            if (success) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteCategory(@PathVariable Long id ){
        try{
            Boolean success =categoryService.deleteById(id);
            if (success){
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        }catch(Exception e){
            return Result.error(e.getMessage());
        }
    }

    /**
     * 批量删除分类
     */
    @DeleteMapping("/batch")
    public Result<Void> deleteCategoryBatch(@RequestBody List<Long> ids ){
        try{
            Boolean success =categoryService.deleteBatch(ids);
            if (success){
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        }catch(Exception e){
            return Result.error(e.getMessage());
        }
    }
}
