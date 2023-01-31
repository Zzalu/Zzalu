import { getComment } from '../../api/titleCompetition';
const titleCompetitionStore = {
  namespaced: true,
  state: () => ({
    date: '23.01.30',
    comment_count: 0,
    comments: [
      {
        comment_id: '1',
        member_id: 'memberid',
        profile_image: 'profile.jpg',
        nickname: '내가 1등',
        content: '1등의 댓글이야',
        time: '13시간 전',
        reply_cnt: 1,
        like_cnt: 231,
        modified: false,
      },
      {
        comment_id: '2',
        profile_image: 'profile.jpg',
        nickname: '내가 2등',
        content: '2등의 댓글이야',
        time: '13시간 전',
        reply_cnt: 0,
        like_cnt: 110,
        modified: true,
      },
    ],
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
