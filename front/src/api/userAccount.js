import { apiInstance } from './index.js';
const api = apiInstance();

const checkUsername = (payload) => {
    // console.log(payload)
    // console.log('데이터 이제 api 요청 보낸다')
    return api.get(`members/exists`, {params: payload})
}

const checkNickname = (payload) => {
    // console.log(payload)
    return api.get(`members/exists`, {params: payload})
}

const checkEmail = (data,res,err) => {
    // console.log('유저이메일 나와야됨',data)
    return api.post(`mail/signup`, data )
    .then(res).catch(err)
}

const requestRegister = (payload) => {
    return api.post(`members/signup`, payload)
}

const requestLogin = (payload, res, err) => {
    console.log('api요청 할거임',payload)
    return api.post(`members/login`, payload)
    .then(res).catch(err)
}

const requestUsername = (data,res,err) => {
    // console.log('유저이메일 나와야됨',data)
    return api.post(`mail/username`, data )
    .then(res).catch(err)
}

export { checkUsername, checkNickname, checkEmail, requestRegister, requestLogin, requestUsername }