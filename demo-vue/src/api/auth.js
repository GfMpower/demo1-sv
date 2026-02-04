//导入基础api类, 继承其封装的http请求方法
import BaseApi from "@/api/base.js";

/**
 * 认证授权API类
 * 继承自BaseApi, 专门处理用户认证相关的api请求
 */
class AuthApi extends BaseApi {
    //构造函数
    constructor() {
        //调用父类构造函数, 设置基础URL路径
        super('/api/auth');
    }

    //用户登录接口
    login(data) {
        return this.post('/login', data)
    }

    //用户注册接口
    register(data) {
        return this.post('/register', data)
    }

    //刷新token接口
    refreshToken() {
        return this.post('/refresh', {})
    }
}

//导出实例
export default new AuthApi()