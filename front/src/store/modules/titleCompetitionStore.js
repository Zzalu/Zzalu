import { getComment } from '../../api/titleCompetition';
const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    date: '23.01.30',
    comment_count: 0,
    comments: [],
  }),
  getters: {},
  mutations: {
    ADD_COMMENTS(state, new_comments) {
      state.comments.push(...new_comments);
    },
  },
  actions: {
    // 댓글, 댓글들을 가져오는 함수
    getCommentList({ commit }, param) {
      getComment(
        param,
        ({ data }) => {
          console.log(data);
          commit('ADD_COMMENTS', data);
        },
        (error) => console.log(error),
      );
    },
  },
};

export default titleCompetitionStore;
