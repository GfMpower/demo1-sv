package com.example.demo.service.impl;

import com.example.demo.domian.vo.CategoryStatisticsVo;
import com.example.demo.domian.vo.SalesStatisticsVO;
import com.example.demo.domian.vo.UserStatisticsVO;
import com.example.demo.mapper.StatisticsMapper;
import com.example.demo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 统计ServiceImpl实现类
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 获取销售统计信息
     * @param days 天数（近几天的数据）
     * @return 销售统计信息
     */
    @Override
    public SalesStatisticsVO getSalesStatistics(int days) {
        SalesStatisticsVO salesStatistics = new SalesStatisticsVO();

        //获取总销售额
        Double totalSales = statisticsMapper.getTotalSales();
        salesStatistics.setTotalSales(BigDecimal.valueOf(totalSales));

        //获取总订单数
        Integer totalOrders = statisticsMapper.getTotalOrders();
        salesStatistics.setTotalOrders(totalOrders);

        //获取总用户数
        Integer totalUsers = statisticsMapper.getTotalUsers();
        salesStatistics.setTotalUsers(totalUsers);

        //计算日期范围: 从当前日期往前推(days - 1)天到当前日期
        Calendar instance = Calendar.getInstance();
        Date endDate = instance.getTime(); //结束日期为当前日志
        instance.add(Calendar.DAY_OF_MONTH, -(days - 1)); //计算开始日期
        Date startDate = instance.getTime();

        //获取日销售统计数据
        List<SalesStatisticsVO.DailySalesVO> dailySalesList = statisticsMapper.getDailySalesStatistics(startDate, endDate);
        salesStatistics.setDailySalesList(dailySalesList);

        return salesStatistics;
    }

    /**
     * 获取用户统计信息
     * @param days 天数（近几天的数据）
     * @return 用户统计信息
     */
    @Override
    public UserStatisticsVO getUserStatistics(int days) {
        UserStatisticsVO userStatisticsVO = new UserStatisticsVO();

        //获取用户总数
        Integer totalUsers = statisticsMapper.getTotalUsers();
        userStatisticsVO.setTotalUsers(totalUsers);

        //计算日期范围: 从当前日期往前推(days - 1)天到当前日期
        Calendar instance = Calendar.getInstance();
        Date endDate = instance.getTime(); //结束日期为当前日志
        instance.add(Calendar.DAY_OF_MONTH, -(days - 1)); //计算开始日期
        Date startDate = instance.getTime();

        //获取指定日期范围内的新增用户数
        Integer newUsers = statisticsMapper.getNewUsers(startDate, endDate);
        userStatisticsVO.setNewUsers(newUsers);

        //获取日用户增长统计数据
        List<UserStatisticsVO.DailyUserGrowthVO> dailyGrowthList = statisticsMapper.getDailyUserSalesStatistics(startDate, endDate);

        //为每一天计算累计用户数
        for (UserStatisticsVO.DailyUserGrowthVO dailyGrowth : dailyGrowthList) {
            try {
                //将字符串日期转换为Date对象
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(dailyGrowth.getDate());

                //计算到当天为止的总用户数
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, 1); //截止到第二天凌晨
                Date nextDay = cal.getTime();

                //查询截止到当前日期的总用户数
                Integer nextTotalUsers = statisticsMapper.getTotalUsersByDate(nextDay);
                userStatisticsVO.setTotalUsers(nextTotalUsers);

            } catch (Exception e) {
                //如果日期解析失败, 设置默认值
                dailyGrowth.setTotalUsers(0);
            }
        }

        userStatisticsVO.setDailyUserGrowthList(dailyGrowthList);

        return userStatisticsVO;
    }

    /**
     * 获取分类统计信息
     * @return 分类统计信息
     */
    @Override
    public CategoryStatisticsVo getCategoryStatistics() {
        CategoryStatisticsVo categoryStatistics = new CategoryStatisticsVo();

        //获取分类销售统计数据
        List<CategoryStatisticsVo.CategorySalesVO> categorySalesList = statisticsMapper.getCategorySalesStatistics();

        //计算所有分类的总销售额
        double totalSales = categorySalesList.stream()
                .mapToDouble(CategoryStatisticsVo.CategorySalesVO::getSales)
                .sum();

        //计算每个分类的销售额占比
        for (CategoryStatisticsVo.CategorySalesVO categorySales : categorySalesList) {
            if (totalSales > 0) {
                double percentage = (categorySales.getSales() / totalSales) * 100;
                //格式化百分比, 保留两位小数
                categorySales.setPercentage(Double.valueOf(String.format("%.2f", percentage)));
            } else {
                categorySales.setPercentage(0.0);
            }
        }

        categoryStatistics.setCategorySalesList(categorySalesList);

        //获取分类商品数量统计数据
        List<CategoryStatisticsVo.CategoryProductVO> categoryProductList = statisticsMapper.getCategoryProductStatistics();

        //计算所有分类的总商品数
        double totalProducts = categoryProductList.stream()
                .mapToDouble(CategoryStatisticsVo.CategoryProductVO::getProductCount)
                .sum();

        //计算每个分类的商品数量占比
        for (CategoryStatisticsVo.CategoryProductVO categoryProduct : categoryProductList) {
            if (totalProducts > 0) {
                double percentage = (categoryProduct.getProductCount() / totalProducts) * 100;
                //格式化百分比, 保留两位小数
                categoryProduct.setPercentage(Double.valueOf(String.format("%.2f", percentage)));
            } else {
                categoryProduct.setPercentage(0.0);
            }
        }

        categoryStatistics.setCategoryProductList(categoryProductList);

        return categoryStatistics;
    }

    /**
     * 获取指定日期范围的销售统计
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 销售统计信息
     */
    @Override
    public SalesStatisticsVO getSalesStatisticsByDateRange(Date startDate, Date endDate) {
        SalesStatisticsVO salesStatistics = new SalesStatisticsVO();

        //获取总销售额
        Double totalSales = statisticsMapper.getTotalSales();
        salesStatistics.setTotalSales(BigDecimal.valueOf(totalSales));

        //获取总订单数
        Integer totalOrders = statisticsMapper.getTotalOrders();
        salesStatistics.setTotalOrders(totalOrders);

        //获取总用户数
        Integer totalUsers = statisticsMapper.getTotalUsers();
        salesStatistics.setTotalUsers(totalUsers);

        //获取指定日期范围内的日销售统计数据
        List<SalesStatisticsVO.DailySalesVO> dailySalesList = statisticsMapper.getDailySalesStatistics(startDate, endDate);
        salesStatistics.setDailySalesList(dailySalesList);

        return salesStatistics;
    }

    /**
     * 获取指定年份的销售统计
     * @param year 年份
     * @return 销售统计信息
     */
    @Override
    public SalesStatisticsVO getSalesStatisticsByYear(int year) {
        SalesStatisticsVO salesStatistics = new SalesStatisticsVO();

        //获取总销售额
        Double totalSales = statisticsMapper.getTotalSales();
        salesStatistics.setTotalSales(BigDecimal.valueOf(totalSales));

        //获取总订单数
        Integer totalOrders = statisticsMapper.getTotalOrders();
        salesStatistics.setTotalOrders(totalOrders);

        //获取总用户数
        Integer totalUsers = statisticsMapper.getTotalUsers();
        salesStatistics.setTotalUsers(totalUsers);

        //获取指定年份的月销售统计数据
        List<SalesStatisticsVO.MonthSalesVO> monthSalesList = statisticsMapper.getMonthSalesStatistics(year);
        salesStatistics.setMonthSalesList(monthSalesList);

        return salesStatistics;
    }
//
//    @Override
//    public Date getEarliestOrderTime() {
//        return statisticsMapper.getEarliestOrderTime();
//    }
}
