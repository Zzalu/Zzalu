import { apiInstance } from './index.js';
const api = apiInstance();

function getQuiet(res, err) {
    api.get(`/chat/rooms`)
        .then(res).catch(err)
}

function createQuietChat(params, datas, res, err) {
    console.log(datas, 'datas'),
        api.post(`/chat/room`, datas)
            .then(res).catch(err);
}

function getHotQuietList(res, err) {
    api.get('/chat/rooms-top10')
        .then(res).catch(err)
}

// 필터

function getOnlySearch(data, res, err) {
    api.get(`/chat/search?keyword=${data}`)
    .then(res).catch(err)
}

export { getQuiet, createQuietChat, getHotQuietList, getOnlySearch }