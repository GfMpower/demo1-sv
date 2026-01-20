package com.example.demo.controller;

import com.example.demo.domian.Category;
import com.example.demo.domian.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    //查询所有分类
    @GetMapping("/categoryAll")
    public List<Category> getCategoryAll() {
        return categoryService.findAll();
    }
    //查询所有商品
    @GetMapping("/productAll")
    public List<Product> getProductAll() {
        return productService.findAll();
    }
}
