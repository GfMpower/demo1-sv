import request from '@/utils/request.js'

class BaseApi {
    constructor(baseURL) {
        this.baseURL = baseURL
    }

    //GET请求
    get(url, params) {
        return request({
            url: this.baseURL + url,
            method: 'get',
            params
        })
    }

    //POST请求
    post(url, data) {
        return request({
            url: this.baseURL + url,
            method: 'post',
            data
        })
    }

    //PUT请求
    put(url, data) {
        return request({
            url: this.baseURL + url,
            method: 'put',
            data
        })
    }

    //DELETE请求
    delete(url, data) {
        return request({
            url: this.baseURL + url,
            method: 'delete',
            data
        })
    }
}

export default BaseApi