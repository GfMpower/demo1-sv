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
//导入Pinia状态管理库
import { createPinia } from 'pinia'
// 导入ECharts组件
import ECharts from 'vue-echarts'
// 导入ECharts核心模块
import { use } from 'echarts/core'
// 导入ECharts渲染器
import { CanvasRenderer } from 'echarts/renderers'
// 导入ECharts图表类型
import { LineChart, PieChart } from 'echarts/charts'
// 导入ECharts组件
import { GridComponent, TooltipComponent, LegendComponent,TitleComponent   } from 'echarts/components'

//创建Pinia实例
const pinia = createPinia()
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

// 注册ECharts组件
use([
    CanvasRenderer,
    LineChart,
    PieChart,
    GridComponent,
    TooltipComponent,
    LegendComponent,
    TitleComponent
])

// 注册全局组件
app.component('v-chart', ECharts)


//使用pinia
app.use(pinia)
//挂载应用程序到http中id为app的DOM元素上
app.mount('#app')
