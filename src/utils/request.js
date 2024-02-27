//定制请求的实例
import axios from 'axios';
import { ElMessage } from "element-plus";
import { useTokenStore } from "@/stores/token.js";
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
        console.log("服务异常!");
        return Promise.reject(err); //将异步状态转换成失败的状态。
    }
)

export default instance;
