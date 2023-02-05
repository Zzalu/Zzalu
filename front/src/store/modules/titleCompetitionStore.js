import { getComments, getNestedComments } from '@/api/titleCompetition';
const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    // 제목학원
    date: '23.01.30',
    comment_count: 0,
    title_competition_id: 0,
    // 댓글
    comments: [],
    last_comment_id: Number.MAX_SAFE_INTEGER,
    // 대댓글

    new_nested_comments: [],
  }),
  getters: {
    getDate: (state) => state.date,
    getCommentCount: (state) => state.comment_count,
    getComments: (state) => state.comments,
    getLastCommentId: (state) => state.last_comment_id,
  },
  mutations: {
    // 댓글
    ADD_COMMENTS(state, new_comments) {
      state.comments.push(...new_comments);
    },
    SET_LAST_COMMENT_ID(state, size) {
      state.last_comment_id = state.last_comment_id - size;
    },
    SET_TITLE_COMPETITION_ID(state, id) {
      state.title_competition_id = id;
    },
    SET_NEW_NESTED_COMMENTS(state, new_nested_comments) {
      state.new_nested_comments = new_nested_comments;
    },
    SET_NEW_NESTED_COMMENTS_EMPTY(state) {
      state.new_nested_comments.splice(0);
    },
  },
  actions: {
    // 댓글
    getCommentList({ commit, state }, size) {
      const param = {
        lastCommentId: state.last_comment_id,
        titleHakwonId: state.title_competition_id,
        size: size,
      };
      getComments(
        param,
        ({ data }) => {
          commit('ADD_COMMENTS', data);
        },
        (error) => console.log(error),
      );
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
  },
};

export default titleCompetitionStore;
