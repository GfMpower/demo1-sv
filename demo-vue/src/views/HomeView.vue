<template>
  <!-- 首页组兼容器 -->
  <div class = "home">
    <el-container>
      <!-- 顶部头部区域 -->
      <el-header>
        <div class ="header-content">
          <h1>后台管理系统</h1>
        </div>
      </el-header>
      <!-- 主要内容区 -->
      <el-main>
        <el-card>
          <div>
            <h2>欢迎使用后台管理系统</h2>
          </div>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>

</script>

<style scoped>
.home {
  height: 100vh;
}
.header-content {
   --color: white;
}
.el-header {
  background-color: #de8fe1;
  color: white;
  height: 70px;
  display: flex;
  align-items: center;
}
.el-main {
  background-color: #dbeaff;
  padding: 20px;
}

</style>