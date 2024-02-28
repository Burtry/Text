//导入请求工具类
import request from '@/utils/request.js'

//文章分类列表查询
export const articleCategoryListService = () => {
    return request.get('/category')
}