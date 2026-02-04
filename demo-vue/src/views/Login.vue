<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-header">
        <h2 class="login-title">后台管理系统</h2>
      </div>

      <el-form
          :model="loginForm"
          :rules="loginRules"
          ref="loginFormRef"
          class="login-form"
          @keyup.enter="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              prefix-icon="el-icon-user"
              size="large"
              clearable
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="el-icon-lock"
              size="large"
              show-password
          />
        </el-form-item>

        <el-form-item>
          <el-checkbox v-model="rememberMe">记住密码</el-checkbox>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              :loading="loading"
              @click="handleLogin"
              size="large"
              class="login-button"
              block
          >
            {{ loading ? '登录中...' : '登录' }}
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/store/user'
import authApi from '@/api/auth'
import userApi from '@/api/user'

// 使用 store
const userStore = useUserStore()
const router = useRouter()
const loginFormRef = ref(null)

// 表单数据
const loginForm = reactive({
  username: 'admin',
  password: '123456',
  captcha: ''
})

// 表单验证规则
const loginRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 4, max: 20, message: '用户名长度为4-20个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 3, max: 20, message: '密码长度为3-20个字符', trigger: 'blur' }
  ],
  captcha: [
    { required: true, message: '请输入验证码', trigger: 'blur' }
  ]
}

// 响应式状态
const loading = ref(false)
const rememberMe = ref(false)

// 处理登录
const handleLogin = () => {
  loginFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true
      try {
        // 调用登录API
        const response = await authApi.login({
          username: loginForm.username,
          password: loginForm.password
        })

        // 保存Token
        userStore.setToken(response.data.token)

        // 获取用户信息
        const userInfoResponse = await userApi.getCurrentUserInfo()
        userStore.setUserInfo(userInfoResponse.data)

        // 记住密码
        if (rememberMe.value) {
          localStorage.setItem('rememberedUser', JSON.stringify({
            username: loginForm.username,
            password: loginForm.password
          }))
        } else {
          localStorage.removeItem('rememberedUser')
        }

        // 显示成功消息
        ElMessage.success('登录成功')

        // 跳转到首页
        await router.push('/')
      } catch (error) {
        loading.value = false
      } finally {
        loading.value = false
      }
    }
  })
}

// 页面加载时检查是否记住密码
onMounted(() => {
  const rememberedUser = localStorage.getItem('rememberedUser')
  if (rememberedUser) {
    try {
      const user = JSON.parse(rememberedUser)
      loginForm.username = user.username
      loginForm.password = user.password
      rememberMe.value = true
    } catch (e) {
      console.error('解析记住的用户信息失败:', e)
    }
  }
})
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-box {
  width: 100%;
  max-width: 400px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 10px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10px);
}

.login-header {
  text-align: center;
  margin-bottom: 30px;
}

.login-logo {
  width: 60px;
  height: 60px;
  margin-bottom: 15px;
}

.login-title {
  margin: 0;
  font-size: 24px;
  color: #333;
  font-weight: 600;
}

.login-form {
  margin-bottom: 20px;
}

.login-button {
  margin-top: 10px;
}

:deep(.el-input__wrapper) {
  border-radius: 6px;
}

:deep(.el-button) {
  border-radius: 6px;
}
</style>