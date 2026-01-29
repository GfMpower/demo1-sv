package com.example.demo.service.impl;

import com.example.demo.domian.Product;
import com.example.demo.domian.dto.ProductCreateDTO;
import com.example.demo.domian.dto.ProductUpdateDTO;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 分页查询商品
     * @param pageNum 页码
     * @param pageSize 每页的数量
     * @param name 商品名称(可选)
     * @param categoryId 分类ID(可选)
     * @return 商品列表
     */
    @Override
    public List<Product> findPage(int pageNum, int pageSize, String name, Long categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        return productMapper.findPage(name, categoryId);
    }

    /**
     * 查询商品数量
     * @param name 商品名称(可选)
     * @param categoryId 分类ID(可选)
     * @return 商品数量
     */
    @Override
    public int count(String name, Long categoryId) {
        return productMapper.count(name, categoryId);
    }

    //插入商品
    @Override
    public Boolean insert(ProductCreateDTO productCreateDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productCreateDTO, product);

        //设置创建时间和更新时间
        Date now = new Date();
        product.setCreateTime(now);
        product.setUpdateTime(now);

        return productMapper.insert(product);
    }

    //更新商品
    @Override
    public Boolean update(ProductUpdateDTO productUpdateDTO) {
        Product product = productMapper.findById(productUpdateDTO.getId());
        if (product == null) {
            return false;
        }

        BeanUtils.copyProperties(productUpdateDTO, product);

        //设置创建时间和更新时间
        product.setUpdateTime(new Date());

        return productMapper.update(product);
    }


    //删除商品
    @Override
    public Boolean deleteById(Long id) {
        return productMapper.deleteById(id);
    }

    //批量删除商品
    @Override
    public Boolean deleteBatch(List<Long> ids) {
        boolean result = true;
        for (Long id : ids) {
            if (!deleteById(id)) {
                result = false;
            }
        }
        return result;
    }
}
