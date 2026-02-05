import BaseApi from '@/api/base'

class OrderApi extends BaseApi {
    constructor() {
        super('/api/orders')
    }

    // 获取订单列表
    getOrderList(params) {
        return this.get('/page', params)
    }

    // 获取订单详情
    getOrderById(id) {
        return this.get(`/${id}`)
    }

    // 创建订单
    createOrder(data) {
        return this.post('', data)
    }

    // 更新订单
    updateOrder(data) {
        return this.put('', data)
    }

    // 删除订单
    deleteOrder(id) {
        return this.delete(`/${id}`)
    }

    // 批量删除订单
    batchDeleteOrders(ids) {
        return this.delete('/batch', { ids })
    }

    // 更新订单状态
    updateOrderStatus(id, status) {
        return this.put(`/${id}/status?status=${status}`)
    }
}

export default new OrderApi()