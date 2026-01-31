<template>
  <!-- 使用Element Plus的容器组件，设置根容器类名 -->
  <el-container class="layout-container">
    <!-- 侧边栏区域，使用el-aside组件，宽度根据侧边栏折叠状态动态变化 -->
    <el-aside :width="sidebarCollapsed ? '64px' : '200px'" class="sidebar">
      <!-- Logo区域 -->
      <div class="logo">
        <!-- 当侧边栏未折叠时显示Logo图片 -->
        <img src="../../../public/favicon.ico" alt="Logo" class="logo-img" v-if="!sidebarCollapsed">
        <!-- 当侧边栏未折叠时显示系统名称 -->
        <span class="logo-text" v-if="!sidebarCollapsed">后台管理系统</span>
      </div>
      <!-- 导航菜单，使用Element Plus的el-menu组件 -->
      <el-menu
          :default-active="activeMenu"
          :collapse="sidebarCollapsed"
          background-color="#304156"
          text-color="#bfcbd9"
          active-text-color="#409EFF"
          router
      >
        <!-- 菜单项，点击跳转到对应路由 -->
        <el-menu-item index="/statistics">
          <template #title>数据统计</template>
        </el-menu-item>
        <el-menu-item index="/users">
          <template #title>用户管理</template>
        </el-menu-item>
        <el-menu-item index="/categorys">
          <template #title>分类管理</template>
        </el-menu-item>
        <el-menu-item index="/products">
          <template #title>商品管理</template>
        </el-menu-item>
        <el-menu-item index="/orders">
          <template #title>订单管理</template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <!-- 主体区域，包含头部和主要内容 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header class="header">
        <!-- 头部左侧区域 -->
        <div class="header-left">
          <!-- 折叠侧边栏按钮 -->
          <el-button icon="el-icon-fold" @click="toggleSidebar" circle></el-button>
          <!-- 面包屑导航 -->
          <el-breadcrumb separator="/" class="breadcrumb">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <!-- 头部右侧区域 -->
        <div class="header-right">
          <!-- 用户信息下拉菜单 -->
          <el-dropdown @command="handleUserCommand">
                        <span class="user-info">
                            <!-- 用户头像 -->
                            <el-avatar :size="30" icon="el-icon-user"></el-avatar>
                          <!-- 用户名 -->
                            <span class="username">管理员</span>
                        </span>
            <!-- 下拉菜单选项 -->
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>

      <!-- 主要内容区域 -->
      <el-main class="main">
        <!-- 路由视图，显示当前路由对应的组件内容 -->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
// 导入Vue组合式API
import {computed} from 'vue'
// 导入Vue Router相关函数
import {useRoute, useRouter} from 'vue-router'
// 导入状态管理
import {useGlobalStore} from '@/store/global'
import {useUserStore} from '@/store/user'

// 使用Vue Router
const route = useRoute()  // 当前路由信息
const router = useRouter()  // 路由实例

// 使用状态管理
const globalStore = useGlobalStore()  // 全局状态
const userStore = useUserStore()      // 用户状态

// 计算属性：侧边栏折叠状态
const sidebarCollapsed = computed(() => globalStore.sidebarCollapsed)
// 计算属性：当前激活的菜单，使用当前路由路径
const activeMenu = computed(() => route.path)

// 切换侧边栏折叠状态的方法
const toggleSidebar = () => {
  globalStore.toggleSidebar()
}

// 处理用户下拉菜单命令的方法
const handleUserCommand = (command) => {
  // 如果命令是logout，执行退出登录操作
  if (command === 'logout') {
    userStore.logout()  // 调用用户状态管理的退出登录方法
    router.push('/login')  // 跳转到登录页面
  }
  // 可以在这里添加其他命令的处理逻辑，如'profile'
}
</script>

<style scoped>
/* 布局容器样式 */
.layout-container {
  height: 100vh; /* 设置容器高度为整个视口高度 */
}

/* 侧边栏样式 */
.sidebar {
  background-color: #304156; /* 设置侧边栏背景颜色 */
  transition: width 0.28s; /* 添加宽度变化的过渡动画，持续0.28秒 */
  overflow: hidden; /* 隐藏溢出的内容 */
}

/* Logo区域样式 */
.logo {
  height: 60px; /* 设置Logo区域高度 */
  display: flex; /* 使用Flex布局 */
  align-items: center; /* 垂直居中对齐 */
  justify-content: center; /* 水平居中对齐 */
  background-color: #2c3e50; /* Logo区域背景颜色，比侧边栏稍深 */
  color: #fff; /* 文字颜色为白色 */
}

/* Logo图片样式 */
.logo-img {
  height: 32px; /* 设置Logo图片高度 */
  margin-right: 10px; /* 图片右侧外边距 */
}

/* Logo文字样式 */
.logo-text {
  font-size: 16px; /* 字体大小 */
  font-weight: bold; /* 字体粗细 */
}

/* 头部区域样式 */
.header {
  display: flex; /* 使用Flex布局 */
  align-items: center; /* 垂直居中对齐 */
  justify-content: space-between; /* 两端对齐 */
  background-color: #fff; /* 背景颜色为白色 */
  border-bottom: 1px solid #eee; /* 底部边框，浅灰色 */
  padding: 0 20px; /* 左右内边距 */
}

/* 头部左侧区域样式 */
.header-left {
  display: flex; /* 使用Flex布局 */
  align-items: center; /* 垂直居中对齐 */
}

/* 面包屑导航样式 */
.breadcrumb {
  margin-left: 20px; /* 左侧外边距 */
}

/* 头部右侧区域样式 */
.header-right {
  display: flex; /* 使用Flex布局 */
  align-items: center; /* 垂直居中对齐 */
}

/* 用户信息区域样式 */
.user-info {
  display: flex; /* 使用Flex布局 */
  align-items: center; /* 垂直居中对齐 */
  cursor: pointer; /* 鼠标指针样式为手型 */
}

/* 用户名样式 */
.username {
  margin-left: 10px; /* 左侧外边距 */
  font-size: 14px; /* 字体大小 */
}

/* 主要内容区域样式 */
.main {
  background-color: #f0f2f5; /* 背景颜色，浅灰色 */
  padding: 20px; /* 内边距 */
  overflow: auto; /* 内容溢出时显示滚动条 */
}
</style>