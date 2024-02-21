//定制请求的实例
import axios from 'axios';
const baseURL = '/api';
const instance = axios.create({ baseURL });


//添加响应拦截器
instance.interceptors.response.use(
    res => {
        return res.data;
    },
    err => {
        console.log("服务异常");
        return Promise.reject(err); //将异步状态转换成失败的状态。
    }
)

export default instance;
