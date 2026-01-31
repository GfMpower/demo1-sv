import { defineStore } from 'pinia'

// 定义并导出名为 'global' 的全局状态 Pinia store
export const useGlobalStore = defineStore('global', {
    // 定义状态（数据）
    state: () => ({
        // 全局加载状态，用于控制页面级加载效果
        loading: false,
        // 侧边栏折叠状态，控制侧边栏的展开/收起
        sidebarCollapsed: false,
        // 面包屑导航数组，存储页面导航路径信息
        breadcrumbs: []
    }),

    // 定义 actions（方法）
    actions: {
        // 设置全局加载状态
        setLoading(loading) {
            this.loading = loading
        },

        // 切换侧边栏的折叠/展开状态
        toggleSidebar() {
            this.sidebarCollapsed = !this.sidebarCollapsed
        },

        // 设置面包屑导航数据
        setBreadcrumbs(breadcrumbs) {
            this.breadcrumbs = breadcrumbs
        }
    }
})