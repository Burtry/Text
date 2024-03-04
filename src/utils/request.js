//定制请求的实例
import axios from 'axios';
import { ElMessage } from "element-plus";
import { useTokenStore } from "@/stores/token.js";
import router from '@/router/index.js'
const baseURL = '/api';
const instance = axios.create({ baseURL });

//添加请求拦截器
instance.interceptors.request.use(
    (config) => {
        //添加token
        const tokenStore = useTokenStore();
        config.headers.token = tokenStore.token;
        return config;
    },
    error => {
        return Promise.reject(error);
    }

)

//添加响应拦截器
instance.interceptors.response.use(
    res => {
        if (res.data.code === 0) {
            return res.data;
        }
        ElMessage.error(res.data.message ? res.data.message : '系统异常');
        return Promise.reject(res.data);

    },
    err => {
        if (err.response.status === 401) {
            ElMessage.error('登录状态已过期，请重新登录');
            const tokenStore = useTokenStore();
            tokenStore.removeToken();
            router.push('/login');
        } else {
            ElMessage.error('系统异常');
            return Promise.reject(err);
        }


    }
)

export default instance;
