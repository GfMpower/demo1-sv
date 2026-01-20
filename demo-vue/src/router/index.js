// 导入Vue Router的创建函数和历史模式创建函数
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue";

//定义路由配置数组
const routes = [
  {
    path: '/', //路由路径, 根路径
    name: 'home', //路由名称: home
    component: HomeView //对应的组件: HomeView(直接导入)
  }
]

//创建路由器实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

//导出路由器实例, 以便在main.js中使用
export default router