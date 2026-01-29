package com.example.demo.service;

import com.example.demo.domian.Product;
import com.example.demo.domian.dto.ProductCreateDTO;
import com.example.demo.domian.dto.ProductUpdateDTO;
import java.util.List;

/**
 * 商品Service接口
 */
public interface ProductService {
    //查询所有商品
    List<Product> findAll();
    //根据ID查询商品
    Product findById(Long id);
    //根据分类ID查询商品
    List<Product> findByCategoryId(Long categoryId);

    /**
     * 分页查询商品
     * @param pageNum 页码
     * @param pageSize 每页的数量
     * @param name 商品名称（可选）
     * @param categoryId 分类ID（可选）
     * @return 商品列表
     */
    List<Product> findPage(int pageNum,int pageSize , String name, Long categoryId);

    /**
     * 查询商品数量
     * @param name 商品名称（可选）
     * @param categoryId 分类ID（可选）
     * @return 商品数量
     */
    int count(String name, Long categoryId);
    //插入商品
    Boolean insert(ProductCreateDTO productCreateDTO);
    //更新商品
    Boolean update(ProductUpdateDTO productUpdateDTO);
    //删除商品
    Boolean deleteById(Long id);

    //批量删除商品
    Boolean deleteBatch(List<Long> ids);
}
