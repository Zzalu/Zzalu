import { getComments, getBestComments, getNestedComments, getTitleCompetition } from '@/api/titleCompetition';

const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    // 제목학원
    open_date: '',
    total_comment_cnt: 0,
    title_competition_id: 0,
    zzal_url: '',
    state: '',

    // 댓글
    sort_type: 'POPULAR',
    comments: [],
    last_comment_id: Number.MAX_SAFE_INTEGER,

    // 대댓글
    new_nested_comments: [],

    // 대댓글 입력
    isNested: false,
    comment_writer: {
      comment_id: '',
      nickname: '',
    },

    // 소켓 관련
    is_top: true,
    socket_comment_cnt: 0,
    socket_comments: [],
  }),
  getters: {
    getDate: (state) => state.open_date,
    getCommentCount: (state) => state.total_comment_cnt,
    getComments: (state) => state.comments,
    getLastCommentId: (state) => state.last_comment_id,
  },
  mutations: {
    // 날짜 바꾸기
    SET_OPEN_DATE(state, open_date) {
      state.open_date = open_date;
    },
    // 제목학원
    SET_TITLE_COMPETITION(state, title_competition_data) {
      state.title_competition_id = title_competition_data.titleHakwonId;
      state.total_comment_cnt = title_competition_data.totalComment;
      state.zzal_url = title_competition_data.zzalUrl;
      state.state = title_competition_data.state;
    },

    // 댓글 sort 수정하기
    MODIFY_SORT_TYPE(state, sort_type) {
      state.sort_type = sort_type;
      if (sort_type == 'LATEST') {
        state.last_comment_id = Number.MAX_SAFE_INTEGER;
      } else if (sort_type == 'CHRONOLOGICAL') {
        state.last_comment_id = Number.MIN_SAFE_INTEGER;
      }
      state.comments = [];
    },

    // 위로 올라가기
    SET_IS_TOP(state) {
      state.is_top = !state.is_top;
    },

    PUSH_SOCKET_COMMENTS(state) {
      console.log('모인 댓글들 넣어요');
      state.comments.unshift(...state.socket_comments.reverse());
    },

    SET_SOCKET_DATA_INIT(state) {
      state.socket_comments.splice(0);
      state.socket_comment_cnt = 0;
    },

    // 소켓 관련 저장
    ADD_SOCKET_COMMENT_CNT(state) {
      state.socket_comment_cnt += 1;
    },
    ADD_SOCKET_COMMENT(state, comment) {
      state.socket_comments.push(comment);
    },

    // 댓글 추가하기
    ADD_COMMENTS(state, new_comments) {
      console.log('댓글 추가하기');
      state.comments.push(...new_comments);
    },

    // 마지막으로 읽어온 댓글
    SET_LAST_COMMENT_ID(state, last_comment_id) {
      state.last_comment_id = last_comment_id;
    },

    // 대댓글
    SET_NEW_NESTED_COMMENTS(state, new_nested_comments) {
      state.new_nested_comments = new_nested_comments;
    },

    SET_NEW_NESTED_COMMENTS_EMPTY(state) {
      state.new_nested_comments.splice(0);
    },

    // 작성관련
    // 댓글

    PUSH_COMMENT(state, comment) {
      console.log('댓글 넣어요');
      state.comments.unshift(comment);
    },

    // 대댓글 작성 관련
    SET_COMMENT_WRITER(state, comment_writer) {
      state.comment_writer.comment_id = comment_writer.comment_id;
      state.comment_writer.nickname = comment_writer.nickname;
      state.isNested = true;
    },

    DELETE_COMMENT_WRITER(state) {
      state.comment_writer.id = '';
      state.comment_writer.nickname = '';
      state.isNested = false;
    },
    DELETE_COMMENT(state, comment_index) {
      state.comments.splice(comment_index, 1);
    },
  },
  actions: {
    async init({ state, dispatch }, data) {
      // console.log(data);
      await dispatch('getTitleCompetition', data.open_date);
      await dispatch('getBestComments');
      await dispatch('setLastCommentId', state.comments[state.comments.length - 1].commentId);
    },
    // 제목학원 가져오기
    async getTitleCompetition({ commit }, open_date) {
      return new Promise((resolve, reject) => {
        commit('SET_OPEN_DATE', open_date);
        getTitleCompetition(
          open_date,
          ({ data }) => {
            commit('SET_TITLE_COMPETITION', data);
            resolve();
          },
          (error) => {
            console.log(error);
            reject();
          },
        );
      });
    },

    // 댓글 sort 수정하기
    async modifySortType({ commit, dispatch }, sort_type) {
      await commit('MODIFY_SORT_TYPE', sort_type);
      if (sort_type == 'POPULAR') {
        await dispatch('getBestComments');
      } else {
        await dispatch('getComments', 10);
      }
    },

    // 위로 올라가기
    setIsTop({ commit }) {
      commit('SET_IS_TOP');
    },
    // 댓글
    async getComments({ commit, state, dispatch }, size) {
      const params = {
        lastCid: state.last_comment_id,
        limit: size,
        sort: state.sort_type,
      };

      await getComments(
        state.title_competition_id,
        params,
        ({ data }) => {
          commit('ADD_COMMENTS', data);
        },
        (error) => {
          console.log(error);
        },
      );
      dispatch('setLastCommentId', state.comments[state.comments.length - 1].commentId);
    },
    async getBestComments({ commit, state }) {
      const params = {
        limit: 50,
        sort: state.sort_type,
      };
      await getBestComments(
        state.title_competition_id,
        params,
        ({ data }) => {
          commit('ADD_COMMENTS', data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    async setLastCommentId({ commit }, comment_id) {
      return new Promise((resolve) => {
        commit('SET_LAST_COMMENT_ID', comment_id);
        resolve();
      });
    },

    // 대댓글
    getNestedCommentList({ commit }, datas) {
      console.log(datas);
      const comment_id = datas.comment_id;
      const params = {
        lastCid: datas.lastCid,
        limit: datas.limit,
        sort: 'LATEST',
      };
      return new Promise((resolve, reject) => {
        getNestedComments(
          comment_id,
          params,
          ({ data }) => {
            commit('SET_NEW_NESTED_COMMENTS', data);
            resolve();
          },
          (error) => {
            console.log(error);
            reject();
          },
        );
      });
    },
    setNestedCommentList({ commit }) {
      return new Promise((resolve) => {
        commit('SET_NEW_NESTED_COMMENTS_EMPTY');
        resolve();
      });
    },

    // 작성 관련
    // 댓글
    pushComment({ commit }, comment) {
      commit('PUSH_COMMENT', comment);
    },
    // 대댓글
    pushNestedComment({ commit }, nested_comment) {
      commit('PUSH_NESTED_COMMENT', nested_comment);
    },

    // 대댓글 작성
    writeNestedComment({ commit }, comment_writer) {
      commit('SET_COMMENT_WRITER', comment_writer);
    },
    // 대댓글 작성 취소
    deleteCommentWriter({ commit }) {
      return new Promise((resolve) => {
        commit('DELETE_COMMENT_WRITER');
        resolve();
      });
    },
    // 삭제 관련
    deleteComment({ commit }, comment_index) {
      commit('DELETE_COMMENT', comment_index);
    },

    // 소켓 통신 관련

    // 새로운 댓글 추가
    addSocketCommentCnt({ commit }) {
      commit('ADD_SOCKET_COMMENT_CNT');
    },
    addSocketComment({ commit }, comment) {
      commit('ADD_SOCKET_COMMENT', comment);
    },
    setSocketDataInit({ commit }) {
      commit('SET_SOCKET_DATA_INIT');
    },
    pushSocketComments({ commit, dispatch }) {
      return new Promise(() => {
        commit('PUSH_SOCKET_COMMENTS');
      }).then(dispatch('setSocketDataInit'));
    },
  },
};

export default titleCompetitionStore;
