<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- 注册表单 -->
      <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData" :rules="rules">
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input :prefix-icon="Lock" type="password" placeholder="请输入再次密码"
            v-model="registerData.rePassword"></el-input>
        </el-form-item>
        <!-- 注册按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space v-on:click="register">
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = false" v-on:click="clearData">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>
      <!-- 登录表单 -->
      <el-form ref="form" size="large" autocomplete="off" v-else :model="registerData" :rules="rules">
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码"
            v-model="registerData.password"></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？</el-link>
          </div>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space v-on:click="login">登录</el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = true" v-on:click="clearData">
            注册 →
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script setup>
import { User, Lock } from "@element-plus/icons-vue";
import { ref } from "vue";
import { ElMessage } from "element-plus";
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false);

//定义数据模型
const registerData = ref({
  username: "",
  password: "",
  rePassword: "",
});

//清除数据模型中的数据
const clearData = () => {
  registerData.value = {
    username: "",
    password: "",
    rePassword: "",
  };
}

//调用后台接口，完成注册
import { userRegisterService, userLoginService } from '../api/user.js'

//注册函数
const register = async () => {
  if (registerData.value.password !== registerData.value.rePassword) {
    ElMessage.error("两次输入的密码不一致！")
    return; //一定要return ，否则会继续执行下面的代码
  }
  let result = await userRegisterService(registerData.value)
  ElMessage.success(result.message ? result.message : "注册成功！")
  clearData()
  //注册成功后，显示登录表单
  isRegister.value = false;
}
//登录函数

//导入token状态
import { useTokenStore } from '@/stores/token.js'
import router from '@/router/index.js'
//调用useTokenStore得到状态
const tokenStore = useTokenStore();

const login = async () => {
  let result = await userLoginService(registerData.value)
  //保存token到状态中
  tokenStore.setToken(result.data)
  //登录成功后，跳转到首页
  ElMessage.success(result.message ? result.message : "登录成功！")
  router.push("/")
  clearData()
}


//自定义校验函数校验函数
const checkRePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error("请输入确认密码!"))
  }
  if (value !== registerData.value.password) {
    callback(new Error("确认密码错误！"))

  }
  callback()

}
//定义校验规则
const rules = {
  username: [
    { required: true, message: "请输入正确的用户名！", trigger: 'blur' },
    { min: 5, max: 16, message: "5-16位非空字符！", trigger: 'blur' }
  ],
  password: [
    { required: true, message: "请输入密码！", trigger: 'blur' },
    { min: 5, max: 16, message: "5-16位非空字符！", trigger: 'blur' }
  ],
  rePassword: [
    //自定义校验规则,checkRePassword为校验函数
    { validator: checkRePassword, trigger: 'blur' }
  ]
}
</script>

<style scoped>
/* 样式 */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url("@/assets/logo2.png") no-repeat 60% center / 240px auto,
      url("@/assets/login_bg.jpg") no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>
