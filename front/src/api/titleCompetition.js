import { apiInstance } from '@/api/index.js';
const api = apiInstance();

// 댓글 가져오기
function getComments(params, success, fail) {
  api.get(`/comment`, { params: params }).then(success).catch(fail);
}
function test(param) {
  console.log(param);
}

export { getComments, test };
