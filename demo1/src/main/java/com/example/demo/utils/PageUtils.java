package com.example.demo.utils;


import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页工具类
 * 提供与分页相关的通用工具方法
 */
public class PageUtils {
    /**
     * 将 PageInfo 对象转换为前端友好的分页结果 Map
     * @param pageInfo 分页插件的分页信息对象
     * @return 包含分页数据和分页信息的 Map 对象
     * @param <T> 数据泛型类型
     */
    public static <T> Map<String, Object> toPageResult(PageInfo<T> pageInfo) {
        HashMap<String, Object> result = new HashMap<>();
        //当前页的数据列表
        result.put("list", pageInfo.getList());
        //总记录数
        result.put("total", pageInfo.getTotal());
        //当前页码(从1开始)
        result.put("pageNum", pageInfo.getPageNum());
        //每页显示的数据条数
        result.put("pageSize", pageInfo.getPageSize());
        //总页数
        result.put("pages", pageInfo.getPages());
        //是否存在下一页
        result.put("hasNextPage", pageInfo.isHasNextPage());
        //是否存在上一页
        result.put("hasPreviousPage", pageInfo.isHasPreviousPage());

        return result;
    }


}
