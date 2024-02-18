
import request from '../utils/request';

//获取所有文章数据的函数
export function articleGetAll() {
    return request.get('/article/getAll');
}

//搜索文章内容和发布状态的函数
export function articleGetAllBySearch(conditions) {
    return request.get('/article/search', { params: conditions });
}

