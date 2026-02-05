import BaseApi from '@/api/base'

class ProductApi extends BaseApi {
    constructor() {
        super('/api/products')
    }

    // 获取商品列表
    getProductList(params) {
        return this.get('/page', params)
    }

    // 获取商品详情
    getProductById(id) {
        return this.get(`/${id}`)
    }

    // 创建商品
    createProduct(data) {
        return this.post('', data)
    }

    // 更新商品
    updateProduct(data) {
        return this.put('', data)
    }

    // 删除商品
    deleteProduct(id) {
        return this.delete(`/${id}`)
    }

    // 批量删除商品
    batchDeleteProducts(ids) {
        return this.delete('/batch', { ids })
    }
}

export default new ProductApi()