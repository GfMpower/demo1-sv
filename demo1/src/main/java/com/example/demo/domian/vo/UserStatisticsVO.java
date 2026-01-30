package com.example.demo.domian.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户统计VO
 */
@Data
public class UserStatisticsVO {
    private Integer totalUsers;//总用户数
    private Integer newUsers;//新增用户数
    private List<DailyUserGrowthVO> dailyUserGrowthList;//每日用户增长量
    private List<MonthUserGrowthVO> monthUserGrowthList;//每月用户增长量
    /**
     * 每日用户增长量VO
     */
    @Data
    public static class DailyUserGrowthVO {
        private String date;//日期
        private Integer newUsers;// 新增用户数
        private Integer totalUsers;//累计用户数
    }
    /**
     * 每月用户增长量VO
     */
    @Data
    public static class MonthUserGrowthVO {
        private String month;//月份
        private Integer newUsers;// 新增用户数
        private Integer totalUsers;//累计用户数
    }
}
