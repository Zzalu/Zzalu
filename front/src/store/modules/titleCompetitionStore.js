import { getComments } from '../../api/titleCompetition';
const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    date: '23.01.30',
    comment_count: 0,
    comments: [],
    lastCommentId: Number.MAX_SAFE_INTEGER,
    titleCompetitionId: 0,
  }),
  getters: {
    getDate: (state) => state.date,
    getCommentCount: (state) => state.comment_count,
    getComments: (state) => state.comments,
    getLastCommentId: (state) => state.lastCommentId,
  },
  mutations: {
    ADD_COMMENTS(state, new_comments) {
      state.comments.push(...new_comments);
    },
    SET_LAST_COMMENT_ID(state, size) {
      state.lastCommentId = state.lastCommentId - size;
    },
    SET_TITLE_COMPETITION_ID(state, id) {
      state.titleCompetitionId = id;
    },
  },
  actions: {
    // 댓글, 댓글들을 가져오는 함수
    getCommentList({ commit, state }, size) {
      const param = {
        lastCommentId: state.lastCommentId,
        titleHakwonId: state.titleCompetitionId,
        size: size,
      };
      console.log(param);
      getComments(
        param,
        ({ data }) => {
          console.log(data);
          commit('ADD_COMMENTS', data);
        },
        (error) => console.log(error),
      );
    },
    setTitleCompetitionId({ commit }, id) {
      commit('SET_TITLE_COMPETITION_ID', id);
    },
    /* getCommentList({ commit }, param) {
      getComments(
        param,
        ({ data }) => {
          console.log(data);
          commit('ADD_COMMENTS', data);
        },
        (error) => console.log(error),
      );
    }, */
  },
};

export default titleCompetitionStore;
