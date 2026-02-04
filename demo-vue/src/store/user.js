import {defineStore} from 'pinia'
import {getToken, getUserInfo, removeToken, removeUserInfo, setToken, setUserInfo} from "@/utils/auth.js";
import authApi from '@/api/auth.js'
import userApi from '@/api/user.js'
//定义并导出名字为 'user' 的Pinia Store
export const useUserStore = defineStore('user', {
    //定义状态 (数据)
    state: () => ({
        //用户信息对象, 初始化为null
        userInfo: getUserInfo(),
        //从本地存储中获取token, 如果没有则设为空字符串
        token : getToken() || ''
    }),

    //定义计算属性
    getters: {
        //检查用户是否已登录
        isLoggedIn: (state) => !!state.token,
        //获取用户名, 如果userInfo不存在则返回空字符串
        username: (state) => state.userInfo?.username || '',
        //获取用昵称, 如果userInfo不存在则返回空字符串
        nickname: (state) => state.userInfo?.nickname || '',
    },

    actions: {
        //设置token并保存到本地存储
        setToken(token) {
            this.token = token
            setToken( token)
        },
        //设置用户信息
        setUserInfo(userInfo) {
            setUserInfo(userInfo)
        },
        //注销用户: 清楚token和用户信息, 并且移除本地存储中的token
        logout() {
            this.token = ''
            this.userInfo = null
            //清除token
            removeToken()
            //清除用户信息
            removeUserInfo()
        },
        //模拟登录请求
        async login(loginData) {
            try {
                //调用登录api
                let res = await authApi.login();
                //保存token
                this.setToken(res.data.token)

                //获取用户信息
                let userInfoRes = await userApi.getCurrentUserInfo();
                this.setUserInfo(userInfoRes)

                return res
            } catch (error) {
                //登录失败时清除token
                this.logout()
                throw error
            }
        }
    }
})