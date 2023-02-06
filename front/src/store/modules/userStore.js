// import { postSignup } from "@/api/userSignup";4
import axios from 'axios'

const API_URL = 'http://127.0.0.1:3000'

const userStore = {
  namespaced: true,
  // state: () => ({
  //   isLogin: false,
  // }),
  state: {
    // 사용자 정보
    username: null,
    nickname: null,
    email: null,
    profile_image: '',
    introduction: null,
    following: [],
    followers: [],

    // 보드

    // 수상기록


  },
  mutations: {
    SIGN_UP(state, new_user) {
      state.new_user = new_user
    }
  },
  getters: {},
  actions: {
    signUp(context, credentials) {
      const username = credentials.username
      const nickname = credentials.nickname
      const password = credentials.password
      const passwordCheck = credentials.passwordCheck

      axios({
        method: 'post',
        url: `${API_URL}/members/signup`,
        data: {
          username, 
          nickname, 
          password, 
          passwordCheck
        }
      })
      .then(res => {
        context.commit('SIGN_UP', res.data)
      })
      .catch(err => console.log(err))
    }
  }
};

export default userStore;
