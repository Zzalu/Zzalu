import { apiInstance } from './index.js';
const api = apiInstance();

function getQuiet(res,err) {
    api.get(`/chat/rooms`)
    .then(res).catch(err)
}

function createQuietChat(params,datas,res,err) {
    console.log(datas,'datas'),
    api.post(`/chat/room`,datas)
    .then(res).catch(err);
}


export { getQuiet, createQuietChat }