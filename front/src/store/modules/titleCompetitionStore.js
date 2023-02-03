import { getComments, getNestedComments } from '../../api/titleCompetition';
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
    nested_comments: {},
    last_nested_comment_id: Number.MAX_SAFE_INTEGER,
    parent_comment_id: 0,
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
    // 대댓글
    ADD_NESTED_COMMENT_ID(state, parent_comment_id, new_nested_comments) {
      // 댓글 창을 눌렀던 상태
      if (parent_comment_id in state.nested_comments) {
        console.log('hi');
      } else {
        console.log('bye');
      }
      console.log(new_nested_comments);
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
    // 대댓글
    getNestedCommentList({ commit, state }, size) {
      const param = {
        lastCommentId: state.last_nested_comment_id,
        parentCommentId: state.parent_comment_id,
        size: size,
      };
      console.log(param);
      getNestedComments(
        param,
        ({ data }) => {
          console.log(data);
          commit('ADD_NESTED_COMMENTS', data);
        },
        (error) => console.log(error),
      );
    },
  },
};

export default titleCompetitionStore;
