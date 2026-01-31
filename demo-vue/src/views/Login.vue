<template>
  <div class="login-container">
    <el-card class="login-card">
      <div slot="header" class="login-header">
        <span>后台管理系统登录</span>
      </div>
      <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" :loading="loading">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import router from "@/router/index.js";
import {useUserStore} from "@/store/user.js";

const userStore = useUserStore()

// 响应式表单数据
const loginForm = reactive({
  username: '',
  password: ''
});

// 表单验证规则
const loginRules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ]
});

// 模板引用（替代this.$refs）
const loginFormRef = ref(null);

// 加载状态
const loading = ref(false)

// 登录方法
const handleLogin = () => {
  loginFormRef.value?.validate((valid) => {
    if (valid) {
      loading.value = true
      try {
        userStore.login(loginForm.value)
        router.push('/')
      } catch (error) {
        console.error('登录失败:', error)
      } finally {
        loading.value = false
      }
    }
  });
};
</script>

<style scoped>
/* 登录容器样式 */
.login-container {
  display: flex;           /* 使用Flex布局 */
  justify-content: center; /* 水平居中 */
  align-items: center;     /* 垂直居中 */
  height: 100vh;           /* 高度为整个视口高度 */
  background-color: #f0f2f5; /* 浅灰色背景，常见于登录页面 */
}

/* 登录卡片样式  */
.login-card {
  width: 400px;           /* 固定宽度 */
}

/* 登录标题样式 */
.login-header {
  text-align: center;     /* 文本居中 */
  font-size: 18px;        /* 字体大小 */
  font-weight: bold;      /* 字体加粗 */
}
</style>