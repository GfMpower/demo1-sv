import BaseApi from '@/api/base'

class StatisticsApi extends BaseApi {
    constructor() {
        super('/api/statistics')
    }

    // 获取销售统计
    getSalesStatistics(params) {
        return this.get('/sales', params)
    }

    // 获取用户统计
    getUserStatistics(params) {
        return this.get('/users', params)
    }

    // 获取分类统计
    getCategoryStatistics() {
        return this.get('/categories')
    }

    // 获取指定日期范围的销售统计
    getSalesStatisticsByDateRange(params) {
        return this.get('/sales/date-range', params)
    }

    // 获取指定年份的销售统计
    getSalesStatisticsByYear(params) {
        return this.get('/sales/year', params)
    }
}

export default new StatisticsApi()