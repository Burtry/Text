//导入vue-router
import { createRouter, createWebHistory } from 'vue-router'
//导入组件
import LoginVue from '../views/Login.vue'


//定义路由关系
const routes = [
    { path: '/login', component: LoginVue },
    {
        path: '/',
        redirect: '/article/category',
        component: () => import("../views/Layout.vue"),
        //子路由
        children: [
            { path: '/article/category', component: import('../views/article/ArticleCategory.vue') },
            { path: '/article/manage', component: import('../views/article/ArticleManage.vue') },
            { path: '/user/info', component: import('../views/user/UserInfo.vue') },
            { path: '/user/avatar', component: import('../views/user/UserAvatar.vue') },
            { path: '/user/password', component: import('../views/user/UserResetPassword.vue') },
        ]
    },


]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router