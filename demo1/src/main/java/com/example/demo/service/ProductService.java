package com.example.demo.service;

import com.example.demo.domian.Product;
import java.util.List;

public interface ProductService {
    //查询所有商品
    List<Product> findAll();
    //根据ID查询商品
    Product findById(Long id);
    //根据分类ID查询商品
    List<Product> findByCategoryId(Long categoryId);
    //插入商品
    Boolean insert(Product product);
    //更新商品
    Boolean update(Product product);
    //删除商品
    Boolean deleteById(Long id);
}
