//导入Node.js中的fileURLToPath和URL模块相功能，用于处理文件路径
import { fileURLToPath, URL } from 'node:url'
//导入Vite的defineConfig函数，用于创建Vite配置对象
import { defineConfig } from 'vite'
//导入Vue插件
import vue from '@vitejs/plugin-vue'
//导入Vue开发工具插件
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  //开发服务器配置
  server: {
    port: 8081,
    host: 'localhost', //主机地址
    open: true, //自动打开浏览器
    proxy: {
      '/api': {
        target: 'http://localhost:8080', //后端api地址
        changeOrigin: true, //是否改变源地址
        rewrite: (path) => path.replace(/^\/api/, '') //重写路径
      }
    }
  },
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
})
