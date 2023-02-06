import { getComments, getNestedComments, getTitleCompetition } from '@/api/titleCompetition';
const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    // 제목학원
    open_date: '2023-02-06',
    total_comment_cnt: 0,
    title_competition_id: 0,
    zzal_url: '',

    // 댓글
    comments: [],
    last_comment_id: Number.MAX_SAFE_INTEGER,
    // 대댓글
    new_nested_comments: [],
    isNested: false,
    comment_writer: {
      id: '',
      nickname: '',
    },
  }),
  getters: {
    getDate: (state) => state.open_date,
    getCommentCount: (state) => state.total_comment_cnt,
    getComments: (state) => state.comments,
    getLastCommentId: (state) => state.last_comment_id,
  },
  mutations: {
    // 제목학원
    SET_TITLE_COMPETITION(state, title_competition_data) {
      state.title_competition_id = title_competition_data.titleHakwonId;
      state.total_comment_cnt = title_competition_data.totalComment;
      state.last_comment_id = title_competition_data.totalComment;
      state.zzal_url = title_competition_data.zzalUrl;
    },

    // 댓글
    ADD_COMMENTS(state, new_comments) {
      state.comments.push(...new_comments);
    },

    SET_LAST_COMMENT_ID(state, size) {
      state.last_comment_id = state.last_comment_id - size;
    },

    // 대댓글
    SET_NEW_NESTED_COMMENTS(state, new_nested_comments) {
      state.new_nested_comments = new_nested_comments;
    },

    SET_NEW_NESTED_COMMENTS_EMPTY(state) {
      state.new_nested_comments.splice(0);
    },

    // 대댓글 작성 관련
    SET_COMMENT_WRITER(state, comment_writer) {
      state.comment_writer.id = comment_writer.id;
      state.comment_writer.nickname = comment_writer.nickname;
      state.isNested = true;
    },

    DELETE_COMMENT_WRITER(state) {
      state.comment_writer.id = '';
      state.comment_writer.nickname = '';
      state.isNested = false;
    },
  },
  actions: {
    // 제목학원 가져오기
    getTitleCompetition({ commit }, open_date) {
      const param = {
        openDate: open_date,
      };
      console.log(param);
      getTitleCompetition(
        param,
        ({ data }) => {
          console.log(data);
          commit('SET_TITLE_COMPETITION', data);
        },
        (error) => console.log(error),
      );
    },
    // 댓글
    getCommentList({ commit, state }, size) {
      const param = {
        lastCommentId: state.last_comment_id,
        titleHakwonId: state.title_competition_id,
        size: size,
      };
      return new Promise((resolve, reject) => {
        getComments(
          param,
          ({ data }) => {
            commit('ADD_COMMENTS', data);
            commit('SET_LAST_COMMENT_ID', size);
            resolve();
          },
          (error) => {
            console.log(error), reject();
          },
        );
      });
    },
    setTitleCompetitionId({ commit }, id) {
      commit('SET_TITLE_COMPETITION_ID', id);
    },

    // 대댓글
    getNestedCommentList({ commit }, param) {
      return new Promise((resolve, reject) => {
        getNestedComments(
          param,
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

    // 대댓글 작성
    writeNestedComment({ commit }, comment_writer) {
      commit('SET_COMMENT_WRITER', comment_writer);
    },
    // 대댓글 작성 취소
    deleteCommentWriter({ commit }) {
      commit('DELETE_COMMENT_WRITER');
    },
  },
};

export default titleCompetitionStore;
