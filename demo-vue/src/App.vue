<template>
  <!-- 根组件容器，Vue应用挂载点 -->
  <div id="app">

    <!-- 路由视图出口，用于渲染当前路由对应的组件 -->
    <router-view/>
  </div>
</template>

<style lang="scss">
/* 应用根组件样式 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif; /* 设置字体族 */
  -webkit-font-smoothing: antialiased; /* 字体抗锯齿渲染，提升Webkit浏览器字体显示效果 */
  -moz-osx-font-smoothing: grayscale; /* 字体抗锯齿渲染，提升Firefox浏览器字体显示效果 */
  text-align: center; /* 文本居中 */
  color: #2c3e50; /* 主要文本颜色 */
}

/* 全局HTML和Body元素样式 */
html, body {
  margin: 0; /* 清除默认外边距 */
  padding: 0; /* 清除默认内边距 */
  height: 100%; /* 设置高度为100%，确保页面可以全屏布局 */
}
</style>