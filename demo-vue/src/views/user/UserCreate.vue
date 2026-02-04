<template>
  <div style="padding: 20px">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>创建用户</span>
        </div>
      </template>

      <el-form
          ref="userFormRef"
          :model="userForm"
          :rules="rules"
          label-width="100px"
          style="max-width: 600px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userForm.username" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="userForm.nickname" placeholder="请输入昵称"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email" placeholder="请输入邮箱"/>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="userForm.phone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="userForm.status">
            <el-radio :value=1>正常</el-radio>
            <el-radio :value=0>禁用</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">创建</el-button>
          <el-button @click="resetForm">重置</el-button>
          <el-button @click="router.go(-1)">返回</el-button>
        </el-form-item>

      </el-form>

    </el-card>
  </div>
</template>

<script setup>
import {ref, reactive} from "vue";
import {useRouter} from "vue-router";

import userApi from '@/api/user.js'
import {ElMessage} from "element-plus";

const router = useRouter()

//用户表单数据
const userForm = reactive({
  username: '',
  password: '',
  nickname: '',
  email: '',
  phone: '',
  status: 1
})

//表单验证规格
const rules = reactive({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 2, max: 12, message: '用户名长度为2-12个字符', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 20, message: '密码长度为6-20个字符', trigger: 'blur'},
  ]
})

//表单实例
const userFormRef = ref()

//重置表单
const resetForm = () => {
  userFormRef.value.resetFields()
}

//提交表单
const submitForm = () => {
  if (!userFormRef.value) return

  userFormRef.value.validate((valid, fields) => {
    if (valid) {
      userApi.createUser(userForm).then(res => {
        ElMessage.success('用户创建成功')
        router.go(-1)
      })
    }
  })
}

</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>