package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String name;//商品名称
    private Long categoryId;//分类ID
    private BigDecimal price;//商品价格
    private String stock;//库存
    private String image;//图片
    private String description;//描述
    private Integer status;//状态 0-禁用 1-正常
}
