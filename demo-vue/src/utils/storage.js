/**
 * 存储相关工具函数
 */

//设置本地存储
export function setLocalStorage(key, value) {
    try {
        localStorage
            .setItem(key, JSON.stringify(value))
    } catch (error) {
        console.error('设置本地存储失败：',error)
    }
}

//获取本地存储
export function getLocalStorage(key, defaultValue = null) {
    try {
        const value = localStorage.getItem( key);
        return value ? JSON.parse(value) : defaultValue;
    } catch (error) {
        console.error('获取本地存储失败：',error)
        return  defaultValue
    }
}

//删除本地存储
export function removeLocalStorage(key) {
    localStorage.removeItem(key)
}

//清空本地存储
export function clearLocalStorage() {
    localStorage.clear()
}

//设置会话存储
export function getSessionStorage(key, defaultValue = null) {
    try {
        const value = sessionStorage.getItem(key);
        return value ? JSON.parse(value) :defaultValue
    } catch (error) {
        console.error('获取会话存储失败', error)
        return defaultValue
    }
}

//删除会话存储
export function removeSessionStorage(key) {
    sessionStorage.removeItem(key)
}

//清空会话存储
export function clearSessionStorage() {
    sessionStorage.clear()
}