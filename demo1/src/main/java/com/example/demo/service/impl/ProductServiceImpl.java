package com.example.demo.service.impl;

import com.example.demo.domian.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    //查询所有商品
    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }
    //根据ID查询商品
    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
    }
    //根据分类ID查询商品
    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productMapper.findByCategoryId(categoryId);
    }
    //插入商品
    @Override
    public Boolean insert(Product product) {
        return productMapper.insert(product);
    }
    //更新商品
    @Override
    public Boolean update(Product product) {
        return productMapper.update(product);
    }
    //删除商品
    @Override
    public Boolean deleteById(Long id) {
        return productMapper.deleteById(id);
    }
}
