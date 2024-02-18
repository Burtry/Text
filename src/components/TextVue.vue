
<script setup>
import { articleGetAll, articleGetAllBySearch } from "../api/article.js";
import { ref } from "vue";

//定义响应式数据
const list = ref([]);
const searchCOnditions = ref({
	content: '',
	state: ''
});

const getAllArticle = async function () {
	let data = await articleGetAll();
	list.value = data;
}();


//search函数
const search = async function () {
	let data = await articleGetAllBySearch({ ...searchCOnditions.value });
	list.value = data;
};



</script>

<template lang="">
    <div>
        <div>

	文章内容: <input type="text"v-model="searchCOnditions.content">

	发布状态: <input type="text" v-model="searchCOnditions.state">

	<button v-on:click="search">搜索</button>

	<br />
	<br />
	<table border="1 solid" colspa="0" cellspacing="0">
		<tr>
			<th>文章标题</th>
			<th>内容</th>
			<th>发表时间</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<tr v-for="(article, index) in list">
			<td>{{article.title}}</td>
			<td>{{article.content}}</td>
			<td>2000-{{article.createTime}}-01</td>
			<td>{{article.state}}</td>
			<td>
				<button>编辑</button>
				<button>删除</button>
			</td>
		</tr>

	</table>
</div>

    </div>
</template>

<style lang="">
    
</style>