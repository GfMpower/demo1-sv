import BaseApi from './base'

class UserApi extends BaseApi {
    constructor() {
        super('/api/users')
    }

    // 获取用户列表
    getUserList(params) {
        return this.get('/page', params)
    }

    // 获取用户详情
    getUserById(id) {
        return this.get(`/${id}`)
    }

    // 创建用户
    createUser(data) {
        return this.post('', data)
    }

    // 更新用户
    updateUser(data) {
        return this.put('', data)
    }

    // 删除用户
    deleteUser(id) {
        return this.delete(`/${id}`)
    }

    // 批量删除用户
    batchDeleteUsers(ids) {
        return this.delete('/batch', { ids })
    }

    // 获取当前用户信息
    getCurrentUserInfo() {
        return this.get('/info')
    }
}

export default new UserApi()