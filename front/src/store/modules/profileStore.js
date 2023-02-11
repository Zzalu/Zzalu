import { getProfileUser } from "@/api/userAccount";
import { getProfileStat } from "@/api/profileAccount";
// import { reject } from "core-js/fn/promise";

const profileStore = {
  namespaced: true,
  state:()=> ({
    profile_user: {
      id: '',
      username: '',
      nickname: "",
      userEmail: "",
      enrollDate: "",
      profileMessage: null,
      profilePath: null,
      followingCnt: 0,
      followerCnt: 0,
      boardList: {
          boards: []
      },
      awardCount: {
        count1st: 0,
        count2nd: 0,
        count3nd: 0
      },
      stats: []
    },
  }),
  mutations: {
    // 프로필 유저
    SET_PROFILE_USER(state, data) {
      state.profile_user.id = data.id;
      localStorage.setItem('profile_id', data.id)
      state.profile_user.username = data.username;
      localStorage.setItem('profile_user', data.username)
      state.profile_user.nickname = data.nickname;
      state.profile_user.profileMessage = data.profileMessage;
      state.profile_user.profilePath = data.profilePath;
      state.profile_user.profileMessage = data.profileMessage;
      state.profile_user.followingCnt = data.followingCnt;
      state.profile_user.followerCnt = data.followerCnt;
      state.profile_user.boardList = data.boardList;
      state.profile_user.enrollDate = data.enrollDate;
      state.profile_user.awardCount = data.awardCount;
    },
    SET_PROFILE_STATS(state, data) {
      state.profile_user.stats = data
    }
  },
  getters: {
  },
  actions: {
    // 프로필 보기
    getProfileUser({ commit }, username) {
      getProfileUser(
        username,
        ({ data }) => {
          commit('SET_PROFILE_USER', data);
        },
        (error) => console.log(error),
      );
    },

    // 프로필 통계 보기
   getProfileStats({ commit }, user_id) {
    getProfileStat(
      user_id,
      (res) => {
        console.log('유저 보관함 요청 성공', res)
        commit('SET_PROFILE_STATS', res)
      },
      (err) => {
        console.log('유저 보관함 요청 실패', err);
      }
    )
  }, 
/*   getProfileStats({ commit }, user_id) {
   return new Promise((resolve, reject) => {
    getProfileStat(
      user_id,
      (res) => {
        console.log('유저 보관함 요청 성공', res)
        commit('GET_USER_STORE_LIST', res)
        resolve(res);
      },
      (err) => {
        console.log('유저 보관함 요청 실패', err);
        reject(err);
      },
    )
   });
  }, */
  }
};

export default profileStore;