package com.example.demo.service;

import com.example.demo.domian.vo.CategoryStatisticsVo;
import com.example.demo.domian.vo.SalesStatisticsVO;
import com.example.demo.domian.vo.UserStatisticsVO;

import java.util.Date;

/**
 * 统计Service接口
 */
public interface StatisticsService {
    /**
     * 获取销售统计信息
     * @param days 天数（近几天的数据）
     * @return 销售统计信息
     */
    SalesStatisticsVO getSalesStatistics(int days);

    /**
     * 获取用户统计信息
     * @param days 天数（近几天的数据）
     * @return 用户统计信息
     */
    UserStatisticsVO getUserStatistics(int days);

    /**
     * 获取分类统计信息
     * @return 分类统计信息
     */
    CategoryStatisticsVo getCategoryStatistics();

    /**
     * 获取指定日期范围的销售统计
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 销售统计信息
     */
    SalesStatisticsVO getSalesStatisticsByDateRange(Date startDate, Date endDate);

    /**
     * 获取指定年份的销售统计
     * @param year 年份
     * @return 销售统计信息
     */
    SalesStatisticsVO getSalesStatisticsByYear(int year);

//    /**
//     * 获取数据库中最早的订单创建时间
//     * @return 最早的订单创建时间
//     */
//    Date getEarliestOrderTime();

}
