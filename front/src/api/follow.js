import { apiInstance } from './index.js';
const api = apiInstance();

// 팔로우 요청 (헤더 필요)
function follow(params, your_id, success, fail) {
    api.put(`/follow/${your_id}`).then(success).catch(fail);
}

// 언팔로우 요청 (헤더 필요)
function unfollow(params, your_id, success, fail) {
    api.delete(`/follow/${your_id}`).then(success).catch(fail);
}

// 팔로잉 리스트 보기 (헤더 필요)
function getFollowingList(member_id, success, fail) {
    api.get(`/following/${member_id}`).then(success).catch(fail);
}

// 팔로워 리스트 보기 (헤더 필요)
function getFollowerList(member_id, success, fail) {
    api.get(`/follower/${member_id}`).then(success).catch(fail);
}

// 팔로우 상태 확인 (헤더 필요)
function checkFollowState(your_id, success, fail) {
    api.get(`/follow-state/${your_id}`).then(success).catch(fail);
}

export { follow, unfollow, getFollowingList, getFollowerList, checkFollowState };