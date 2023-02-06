import { apiInstance } from './index.js';
const api = apiInstance();

const checkUsername = (payload) => {
    console.log(payload)
    return api.get(`members/exists`, {params: payload})
}

const checkNickname = (payload) => {
    console.log(payload)
    return api.get(`members/exists`, {params: payload})
}

const checkEmail = (data,res,err) => {
    // console.log('유저이메일 나와야됨',data)
    // const headers =  { 
    //     "Content-Type" : "application/json; charset=utf-8"
    // } // [요청 헤더]
    return api.post(`mail/signup`, data )
    .then(res).catch(err)
}

export { checkUsername, checkNickname, checkEmail }