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
//使用根组件App创建Vue应用程序
const app = createApp(App)
//使用路由插件
app.use(router)
//使用Element Plus-UI组件库
app.use(ElementPlus)
//挂载应用程序到http中id为app的DOM元素上
app.mount('#app')
