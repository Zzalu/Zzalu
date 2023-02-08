import { authApiInstance } from './index.js';
// import { apiInstance } from './index.js';

// 로그인
const authapi = authApiInstance();
// const api = apiInstance();

function getStoreList(params,data,res,err) {
    authapi.get(`/boards?gifId=${params}`)
    .then(res).catch(err)
}

function createBoard(params,datas,res,err) {
    // console.log(params,'dd',datas);
    authapi.post(`/boards`,datas)
    .then(res).catch(err)
}


export { getStoreList, createBoard }