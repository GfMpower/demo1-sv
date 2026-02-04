/**
 * 认证相关工具函数
 */

//检查是否已登录
export function isLoggedIn() {
    const token = localStorage.getItem('token');
    return !!token;
}

//获取token
export function getToken() {
    return localStorage.getItem('token')
}

//设置token
export function setToken(token) {
    return localStorage.setItem('token' , token)
}

//清除token
export function removeToken() {
    localStorage.removeItem('token')
}

//获取用户信息
export function getUserInfo() {
    const userInfo = localStorage.getItem('userInfo');
    return userInfo ? JSON.parse(userInfo) : null;
}

//设置用户信息
export function setUserInfo(userInfo) {
    localStorage.setItem('userInfo', JSON.stringify(userInfo))
}

//清除用户信息
export function removeUserInfo() {
    localStorage.removeItem('userInfo')
}

//退出登录
export function logout() {
    removeToken();
    removeUserInfo();
}

//检查权限
export function hasPermission(permission) {
    const userInfo = getUserInfo();
    if (!userInfo || !userInfo.roles) {
        return false
    }

    //检查用户是否拥有指定权限
    return userInfo.roles.some(role => {
        role.permission && role.permission.some(p => p.code === permission)
    })
}