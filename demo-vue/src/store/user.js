import {defineStore} from 'pinia'

//定义并导出名字为 'user' 的Pinia Store
export const useUserStore = defineStore('user', {
    //定义状态 (数据)
    state: () => ({
        //用户信息对象, 初始化为null
        userInfo: null,
        //从本地存储中获取token, 如果没有则设为空字符串
        token: localStorage.getItem('token') || ''
    }),

    //定义计算属性
    getters: {
        //检查用户是否已登录
        isLoggedIn: (state) => !!state.token,
        //获取用户名, 如果userInfo不存在则返回空字符串
        username: (state) => state.userInfo.username || ''
    },

    actions: {
        //设置token并保存到本地存储
        setToken(token) {
            this.token = token
            localStorage.setItem('token', token)
        },
        //设置用户信息
        setUserInfo(userInfo) {
            this.userInfo = userInfo
        },
        //注销用户: 清楚token和用户信息, 并且移除本地存储中的token
        logout() {
            this.token = ''
            this.userInfo = null
            localStorage.removeItem('token')
        },
        //模拟登录请求
        async login(loginData) {
            //返回一个模拟异步请求
            return new Promise((resolve) => {
                //生成模拟token
                const token = 'fake-token-' + Date.now()
                //设置token
                this.setToken(token)
                //设置模拟用户信息
                this.setUserInfo({
                    id: 1,
                    username: loginData.username,
                    nickname: '管理员'
                })
                resolve({token})
            })
        }
    }
})