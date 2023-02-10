import { apiInstance } from './index.js';
import { authApiInstance } from './index.js';

const authapi = authApiInstance();
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

function noSearchCreatedRecentroom(data, res, err) {
    authapi.get(`/chat/member-order-lastactivation?memberId=${data}`)
        .then(res).catch(err)
}

function noSearchCreatedLikeroom(data, res, err) {
    console.log(data, 'dd')
    authapi.get(`/chat/member-order-likecount?memberId=${data}`)
        .then(res).catch(err)
}

function SearchAllLikeroom(data, res, err) {
    api.get(`/chat/search-order-likecount?keyword=${data}`)
        .then(res).catch(err)
}

function SearchCreatedLikeroom(data, res, err) {
    console.log('내가만든검색좋아요',data);
    authapi.get(`/chat/search-member-order-likecount?keyword&memberId=${data}`)
        .then(res).catch(err)
}

export { getQuiet, createQuietChat, getHotQuietList, getOnlySearch, noSearchCreatedRecentroom, noSearchCreatedLikeroom, SearchAllLikeroom, SearchCreatedLikeroom }