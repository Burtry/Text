<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { articleCategoryListService, articleCategoryAddService, articleCategoryUpdateService } from "@/api/article.js";
import { ElMessage } from 'element-plus'

const categorys = ref([])
//控制添加分类弹窗
const dialogVisible = ref(false)
//定义弹窗标题的展示内容

const dialogTitle = ref("")

//添加分类数据模型
const categoryModel = ref({
    categoryName: '',
    categoryAlias: ''
})

//获取文章分类列表

const articleList = async () => {
    await articleCategoryListService().then(res => {
        categorys.value = res.data
    })

}
articleList()

//添加文章分类函数
const addCategory = () => {

    if (categoryModel.value.categoryName === '' || categoryModel.value.categoryAlias === '') {
        ElMessage.error('分类名称和分类别名不能为空')
        return
    }
    articleCategoryAddService(categoryModel.value).then(res => {
        if (res.code === 0) {
            ElMessage.success('添加成功')
            dialogVisible.value = false
            categoryModel.value.categoryName = ''
            categoryModel.value.categoryAlias = ''


            articleList()
        } else {
            ElMessage.error(res.message ? res.message : '添加失败')
        }
    })
}

//清空数据模型的数据
const clearCategoryModel = () => {
    categoryModel.value.categoryName = ''
    categoryModel.value.categoryAlias = ''

}

//修改文章分类
const updateCategory = () => {
    if (categoryModel.value.categoryName === '' || categoryModel.value.categoryAlias === '') {
        ElMessage.error('分类名称和分类别名不能为空')
        return
    }
    articleCategoryUpdateService(categoryModel.value).then(res => {
        if (res.code === 0) {
            ElMessage.success('修改成功')

            dialogVisible.value = false
            categoryModel.value.categoryName = ''
            categoryModel.value.categoryAlias = ''
            articleList()
        } else {
            ElMessage.error(res.message ? res.message : '修改失败')
        }
    }
    )
}


//添加分类表单校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}
//展示编辑分类弹窗
const showDialog = (row) => {
    dialogVisible.value = true,
        dialogTitle.value = '编辑分类'
    categoryModel.value.categoryName = row.categoryName
    categoryModel.value.categoryAlias = row.categoryAlias
    categoryModel.value.id = row.id
}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <el-button type="primary"
                        @click="dialogVisible = true, dialogTitle = '添加分类', clearCategoryModel()">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" prop="id"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click=showDialog(row)></el-button>
                    <el-button :icon="Delete" circle plain type="danger"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title=dialogTitle width="30%">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="dialogTitle == '添加分类' ? addCategory() : updateCategory()"> 确认
                    </el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

<style scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>