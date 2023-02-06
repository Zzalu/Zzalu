import { apiInstance } from '@/api/index.js';
const api = apiInstance();

// 제목학원 가져오기
function getTitleCompetition(params, success, fail) {
  console.log(params);
  api.get(`/TitleHakwon`, { params: params }).then(success).catch(fail);
}
// 댓글 가져오기
function getComments(params, success, fail) {
  api.get(`/comment/comments`, { params: params }).then(success).catch(fail);
}
// 대댓글 가져오기
function getNestedComments(params, success, fail) {
  api.get(`/comment/reply`, { params: params }).then(success).catch(fail);
}

// 댓글 등록하기
function addComment(comment_data, success, fail) {
  api.post(`/comment`, comment_data).then(success).catch(fail);
}

// 대댓글 등록하기
function addNestedComment(nested_comment_data, success, fail) {
  api.post(`/comment/reply`, nested_comment_data).then(success).catch(fail);
}

// 좋아요 누르기
function plusLike(params, success, fail) {
  api.post(`/comment/plus/like`, { params: params }).then(success).catch(fail);
}

function minusLike(params, success, fail) {
  api.post(`/comment/minus/like`, { params: params }).then(success).catch(fail);
}

export {
  getComments,
  getNestedComments,
  getTitleCompetition,
  addComment,
  addNestedComment,
  plusLike,
  minusLike,
  deleteComment,
  deleteNestedComment,
};
