import request from '../utils/request'

//提供调用接口的函数

const userRegisterService = (registerData) => {
    //借助UrlSearchParams来完成传递
    const params = new URLSearchParams()
    for (const key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post('/user/register', params)

}

export default userRegisterService






