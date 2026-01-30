package com.example.demo.domian.vo;

import lombok.Data;

import java.util.List;

/**
 * 分类统计VO
 */
@Data
public class CategoryStatisticsVo {
    private List<CategorySalesVO> categorySalesList;//分类销售占比列表
    private List<CategoryProductVO> categoryProductList;//分类商品统计列表
    /**
     * 类别销售VO
     */
    @Data
    public static class CategorySalesVO {
        private String categoryName;//分类名称
        private Double sales;//销售额
        private Double percentage;//占比
    }
    /**
     * 类别商品数量VO
     */
    @Data
    public static class CategoryProductVO {
        private String categoryName;//分类名称
        private Integer productCount;//商品数量
        private Double percentage;//占比
    }
}
