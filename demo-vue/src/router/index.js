// 导入Vue Router的创建函数和历史模式创建函数
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue";
import Statistics from "@/views/statistics/Statistics.vue";
import UserList from "@/views/user/UserList.vue";
import UserCreate from "@/views/user/UserCreate.vue";
import UserEdit from "@/views/user/UserEdit.vue";
import CategoryList from "@/views/categorys/CategoryList.vue";
import ProductList from "@/views/products/ProductList.vue";
import OrderList from "@/views/orders/OrderList.vue";
import login from "@/views/Login.vue";
import {getToken} from "@/utils/auth.js";

//定义路由配置数组
const routes = [
  {
    path: '/', //路由路径, 根路径
    name: 'home', //路由名称: home
    component: HomeView, //对应的组件: HomeView(直接导入)
    redirect: '/statistics', //默认重定向到数据统计页面
    children: [ //嵌套路由, 这些路由将显示在Home组件的<router-view/>中
      {
        path : '/statistics',
        name: 'statistics',
        component:Statistics,
        meta:{title:'数据统计',requiresAuth:true}
      },
      {
        path : '/users',
        name: 'UserList',
        component:UserList,
        meta:{title:'用户管理',requiresAuth:true}
      },
      {
        path : '/users/create',
        name: 'UserCreate',
        component:UserCreate,
        meta:{title:'创建用户',requiresAuth:true}
      },
      {
        path : '/users/edit/:id',
        name: 'UserEdit',
        component:UserEdit,
        meta:{title:'编辑用户',requiresAuth:true}
      },
      {
        path : '/statistics',
        name: 'statistics',
        component:Statistics,
        meta:{title:'数据统计',requiresAuth:true}
      },
      {
        path : '/categorys',
        name: 'CategoryList',
        component:CategoryList,
        meta:{title:'分类管理',requiresAuth:true}
      },
      {
        path : '/products',
        name: 'ProductList',
        component:ProductList,
        meta:{title:'商品管理',requiresAuth:true}
      },
      {
        path : '/orders',
        name: 'OrderList',
        component:OrderList,
        meta:{title:'订单管理',requiresAuth:true}
      },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component:login,
    meta:{title:'登录',requiresAuth:false} //登录页不需要认证
  }
]

//创建路由器实例
const router = createRouter({
  history: createWebHistory(import.meta.env.VUE_APP_API_BASE_URL),
  routes,//路由配置数组
})


//全局前置首页: 在路由跳转前执行
router.beforeEach((to, from, next) => {
  // 设置页面标题，优先使用环境变量中的标题，否则使用默认标题
  document.title = import.meta.env.VUE_APP_TITLE || '后台管理系统'

  // 检查目标路由是否需要认证
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  // 从本地存储获取token
  const token = getToken()

  if (requiresAuth && !token) {
    // 需要认证但没有token，跳转到登录页
    next('/login')
  } else if (to.name === 'Login' && token) {
    // 已经登录了还要访问登录页，跳转到首页
    next('/')
  } else {
    // 其他情况正常跳转
    next()
  }
})

//导出路由器实例, 以便在main.js中使用
export default router