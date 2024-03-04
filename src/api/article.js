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

//文章分类删除
export const articleCategoryDeleteService = (categoryId) => {
    return request.delete(`/category?id=` + categoryId)
}

//文章列表查询
export const articleListService = (params => {
    return request.get('/article/page', { params: params })
})

//添加文章
export const articleAddService = (articleModel) => {
    return request.post('/article', articleModel)
}