import axios from  'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useUserStore } from "@/store/user.js";

//创建axios实例
const service = axios.create({
    //Api的基础URL, 优先使用环境变量中的配置
    baseURL: import.meta.env.VUE_APP_API_BASE_URL || 'http://localhost:8080',
    //请求超时时间, 单位毫秒
    timeout: 15000
})

//请求拦截器
service.interceptors.request.use(
    config => {
        //在发送请求之前执行的操作
        const userStore = useUserStore();
        //检查用户状态中是否存在token
        if (userStore.token) {
            //为每个请求头添加Authorization, 携带Bearer token
            config.headers['Authorization'] = 'Bearer ' + userStore.token
        }
        return config
    },
    error => {
        //请求错误处理
        return Promise.reject(error)
    }
)

//响应拦截器
service.interceptors.response.use(
    response => {
        const res = response.data

        //检查响应状态码是否为200(成功)
        if (res.code !== 200) {
            //处理特定的token相关错误码
            if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
                const userStore = useUserStore();
                //显示确认对话框, 提示用户被登出
                ElMessageBox.confirm(
                    '你已被登出, 可以选择继续留在该页面, 或者重新登陆',
                    '确定登出',
                    {
                        confirmButtonText: '重新登录',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    //用户确认后执行登出操作
                    userStore.logout()
                    //刷新页面以重新初始化路由, 避免状态问题
                    location.reload()
                })
            } else {
                //对其他错误码, 显示错误消息
                ElMessage({
                    message: res.message || 'Error',
                    type: 'error',
                    duration: 5 * 1000
                })
            }
            //返回一个被拒绝的消息, 传递错误信息
            return Promise.reject(new Error(res.message || 'Error'))
        } else {
            //请求成功, 返回响应数据
            return res
        }
    },
    error => {
        //响应错误处理(网络错误, 服务器错误)
        ElMessage({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        })
        return Promise.reject(error)
    }
)

//导出配置好的实例
export default service