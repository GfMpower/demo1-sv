//导入Vue的createApp的函数，用于创建应用程序
import { createApp } from 'vue'
//导入根组件App.vue
import App from './App.vue'
//导入路由配置
import router from './router'
//导入Element Plus-UI的组件
import ElementPlus from 'element-plus'
//导入Element Plus-UI的样式文件
import 'element-plus/dist/index.css'
//导入ElementPlus的中文语言包
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//导入自定义主题色
import '@/assets/styles/element-variables.scss'

//使用根组件App创建Vue应用程序
const app = createApp(App)
//使用路由插件
app.use(router)
//使用Element Plus-UI组件库
app.use(ElementPlus)
//设置ElementPlus组件库的默认语言为中文
app.use(ElementPlus, {
    locale: zhCn,
})
//挂载应用程序到http中id为app的DOM元素上
app.mount('#app')
