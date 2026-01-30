package com.example.demo.domian.vo;

import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

/**
 * 销售统计VO
 */
@Data
public class SalesStatisticsVO {
    private BigDecimal totalSales;//销售总额
    private Integer totalOrders;//订单总数
    private Integer totalUsers;//用户总数
    private List<DailySalesVO> dailySalesList;//每日销售数据列表
    private List<MonthSalesVO> monthSalesList;//每月销售数据列表

    /**
     *  每日销售数据VO
     */
    @Data
    public static class DailySalesVO {
        private String date;//日期
        private  BigDecimal sales;//销售金额
        private Integer orders;//订单数
    }
    /**
     * 每月销售数据VO
     */
    @Data
    public static class MonthSalesVO {
        private String month;//月份
        private BigDecimal sales;//销售金额
        private Integer orders;//订单数
    }
}
