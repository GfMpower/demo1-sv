package com.example.demo.domian.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDTO {
    private Long id; //商品ID
    private String name; //商品名称
    private Long categoryId; //分类ID
    private BigDecimal price; //价格
    private String stock; //库存
    private String image; //图片
    private String description; //描述
    private Integer status = 1; //状态 0-下架 1-上架
}
