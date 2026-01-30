package com.example.demo.controller;


import com.example.demo.domian.vo.CategoryStatisticsVo;
import com.example.demo.domian.vo.SalesStatisticsVO;
import com.example.demo.domian.vo.UserStatisticsVO;
import com.example.demo.service.StatisticsService;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 统计管理Controller
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    //获取销售统计信息
    @GetMapping("/sales")
    public Result<SalesStatisticsVO> getSalesStatistics(@RequestParam(defaultValue = "7") int days){
        try {
            //调用服务层方法获取销售统计数据
            SalesStatisticsVO salesStatistics = statisticsService.getSalesStatistics(days);
            return Result.success(salesStatistics);
        } catch (Exception e){
            return Result.error("获取销售统计失败:" + e.getMessage());
        }
    }

    //获取用户统计信息
    @GetMapping("/users")
    public Result<UserStatisticsVO> getUserStatistics(@RequestParam(defaultValue = "7") int days){
        try {
            //调用服务层方法获取用户统计数据
            UserStatisticsVO userStatistics = statisticsService.getUserStatistics(days);
            return Result.success(userStatistics);
        } catch (Exception e){
            return Result.error("获取用户统计失败:" + e.getMessage());
        }
    }

    //获取分类统计信息
    @GetMapping("/categorys")
    public Result<CategoryStatisticsVo> getCategoryStatistics(){
        try {
            //调用服务层方法获取分类统计数据
            CategoryStatisticsVo categoryStatistics = statisticsService.getCategoryStatistics();
            return Result.success(categoryStatistics);
        } catch (Exception e){
            return Result.error("获取分类统计失败:" + e.getMessage());
        }
    }

    //获取指定日期范围内的销售统计信息
    @GetMapping("/sales/date-range")
    public Result<SalesStatisticsVO> getSalesStatisticsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate
    ){
        try{
            //创建日期格式化器
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

//            // 校验时间范围是否合理
//            Date earliestRecordTime = statisticsService.getEarliestOrderTime();
//            if (end.before(earliestRecordTime)) {
//                return Result.error("指定的时间范围早于数据库中的最早记录时间：" + sdf.format(earliestRecordTime));
//            }

            //调用服务层方法获取指定日期范围内的销售统计数据
            SalesStatisticsVO salesStatistics = statisticsService.getSalesStatisticsByDateRange(start, end);
            return Result.success(salesStatistics);
        }catch (Exception e){
            return Result.error("获取指定日期范围内的销售统计失败:" + e.getMessage());
        }
    }

    //获取指定年份的销售统计
    @GetMapping("/sales/year")
    public Result<SalesStatisticsVO> getSalesStatisticsByYear(@RequestParam int year) {
        try {
            SalesStatisticsVO salesStatistics = statisticsService.getSalesStatisticsByYear(year);
            return Result.success(salesStatistics);
        } catch (Exception e) {
            return Result.error("获取销售统计失败: " + e.getMessage());
        }
    }
}
