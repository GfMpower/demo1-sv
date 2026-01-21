package com.example.demo.controller;

import com.example.demo.domian.Category;
import com.example.demo.domian.Order;
import com.example.demo.domian.OrderItem;
import com.example.demo.domian.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.utils.Result;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    //查询所有分类
    @GetMapping("/categoryAll")
    public Result<List<Category>> getCategoryAll() {
        List<Category> categoryList = categoryService.findAll();
        return Result.success(categoryList);
    }
    //查询所有商品
    @GetMapping("/productAll")
    public Result<List<Product>> getProductAll() {
        List<Product> productList = productService.findAll();
        return Result.success(productList);
    }
    //查询所有订单
    @GetMapping("/orderAll")
    public Result<List<Order>> getOrderAll() {
        List<Order> orderList = orderService.findAll();
        return Result.success(orderList);
    }
    //查询所有订单项
    @GetMapping("/orderItemAll")
    public Result<List<OrderItem>> getOrderItemAll() {
        List<OrderItem> orderItemList = orderItemService.findAll();
        return Result.success(orderItemList);
    }
}
