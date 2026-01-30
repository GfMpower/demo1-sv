package com.example.demo.mapper;

import com.example.demo.domian.vo.CategoryStatisticsVo;
import com.example.demo.domian.vo.SalesStatisticsVO;
import com.example.demo.domian.vo.UserStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * 统计Mapper接口
 */
@Mapper
public interface StatisticsMapper {
    //获取总销售额
    Double getTotalSales();

    //获取总订单数
    Integer getTotalOrders();

    //获取总用户数
    Integer getTotalUsers();

    //根据日期获取总用户数
    Integer getTotalUsersByDate(Date date);

    //获取指定日期范围内的日销售记录
    List<SalesStatisticsVO.DailySalesVO> getDailySalesStatistics(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //获取指定年份的月销售统计
    List<SalesStatisticsVO.MonthSalesVO> getMonthSalesStatistics(@Param("year") Integer year);

    //获取指定日期范围内的新增用户数
    Integer getNewUsers(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //获取指定日期范围内的日用户增长统计
    List<UserStatisticsVO.DailyUserGrowthVO> getDailyUserSalesStatistics(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //获取指定年份的日用户增长统计
    List<UserStatisticsVO.MonthUserGrowthVO> getMonthlyUserSalesStatistics(@Param("year") int year);

    //获取分类销售统计
    List<CategoryStatisticsVo.CategorySalesVO> getCategorySalesStatistics();

    //获取分类商品数量统计
    List<CategoryStatisticsVo.CategoryProductVO> getCategoryProductStatistics();

//    /**
//     * 获取数据库中最早的订单创建时间
//     * @return 最早的订单创建时间
//     */
//    Date getEarliestOrderTime();

//    // 1. 指定年份的总销售额
//    @Select("SELECT COALESCE(SUM(total_amount), 0) FROM `order` WHERE YEAR(create_time) = #{year} AND status >= 2")
//    Double getTotalSalesByYear(@Param("year") int year);
//
//    // 2. 指定年份的总订单数
//    @Select("SELECT COUNT(*) FROM `order` WHERE YEAR(create_time) = #{year} AND status >= 2")
//    Integer getTotalOrdersByYear(@Param("year") int year);
//
//    // 3. 指定年份的总用户数（按用户创建时间统计）
//    @Select("SELECT COUNT(*) FROM user WHERE YEAR(create_time) = #{year}")
//    Integer getTotalUsersByYear(@Param("year") int year);
//
//    // 4. 修正方法名：日用户增长统计
//    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS new_users FROM user WHERE create_time >= #{startDate} AND create_time <= #{endDate} GROUP BY DATE(create_time) ORDER BY date")
//    List<UserStatisticsVO.DailyUserGrowthVO> getDailyUserGrowthStatistics(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
