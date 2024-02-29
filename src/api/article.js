//导入请求工具类
import request from '@/utils/request.js'

//文章分类列表查询
export const articleCategoryListService = () => {
    return request.get('/category')
}

//文章分类添加
export const articleCategoryAddService = (categoryData) => {
    return request.post('/category', categoryData)
}

//修改文章分类
export const articleCategoryUpdateService = (categoryData) => {
    return request.put('/category', categoryData)
}