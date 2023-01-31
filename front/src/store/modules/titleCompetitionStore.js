import { getComment } from '../../api/titleCompetition';
const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    date: '23.01.30',
    comment_count: 0,
    comments: [],
  }),
  mutations: {
    SET_COMMENT_LIST(state, comments) {
      state.comments = comments;
    },
  },
  getters: {},
  actions: {
    // 댓글, 댓글들을 가져오는 함수
    async getCommentList({ commit }) {
      let param = {
        lastCommentId: Number.MAX_SAFE_INTEGER,
        titleHakwonId: 1,
        size: 1,
      };
      await getComment(
        param,
        ({ data }) => {
          console.log(data);
          commit('SET_COMMENT_LIST', data);
        },
        (error) => console.log(error),
      );
    },
    /* getCommentList: ({ commit }) => {
      let param = {
        lastCommentId: Number.MAX_SAFE_INTEGER,
        titleHakwonId: 1,
        size: 1,
      };
      getComment(
        param,
        ({ data }) => {
          commit('SET_COMMENT_LIST', data);
        },
        (error) => console.log(error),
      );
    }, */
  },
};

export default titleCompetitionStore;
