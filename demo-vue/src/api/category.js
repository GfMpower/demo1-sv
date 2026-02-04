import BaseApi from '@/api/base'

class CategoryApi extends BaseApi {
    constructor() {
        super('/api/categories')
    }

    // 获取分类列表
    getCategoryList() {
        return this.get('')
    }

    // 获取分类树
    getCategoryTree() {
        return this.get('/tree')
    }

    // 获取分类详情
    getCategoryById(id) {
        return this.get(`/${id}`)
    }

    // 创建分类
    createCategory(data) {
        return this.post('', data)
    }

    // 更新分类
    updateCategory(data) {
        return this.put('', data)
    }

    // 删除分类
    deleteCategory(id) {
        return this.delete(`/${id}`)
    }

    // 批量删除分类
    batchDeleteCategories(ids) {
        return this.delete('/batch', { ids })
    }
}

export default new CategoryApi()