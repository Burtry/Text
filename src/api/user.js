import request from '../utils/request'

//提供调用接口的函数
//注册
export const userRegisterService = (registerData) => {
    //借助UrlSearchParams来完成传递
    const params = new URLSearchParams()
    for (const key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/register', params)

}
//登录
export const userLoginService = (loginData) => {

    const params = new URLSearchParams()
    for (const key in loginData) {
        params.append(key, loginData[key])
    }
    return request.post('/user/login', params)

}
// export { userRegisterService, userLoginService }






