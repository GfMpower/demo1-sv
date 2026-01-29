package com.example.demo.mapper;
import com.example.demo.domian.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductMapper extends BaseMapper<Product>{
    //查询所有商品
    List<Product> findAll();
    //根据ID查询商品
    Product findById(Long id);
    //根据分类ID查询商品
    List<Product> findByCategoryId(Long categoryId);
    //分页查询商品
    List<Product> findPage(@Param("name") String name, @Param("categoryId") Long categoryId);
    //查询商品数量
    int count(@Param("name") String name, @Param("categoryId") Long categoryId);
    //插入商品
    Boolean insert(Product product);
    //更新商品
    Boolean update(Product product);
    //删除商品
    Boolean deleteById(Long id);
}
