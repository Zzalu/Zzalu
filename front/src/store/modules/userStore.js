import { checkUsername, checkNickname, checkEmail, requestRegister } from "@/api/userAccount";

const userStore = {
  namespaced: true,
  state:()=> ({
    temp: {
      username: '',
      nickname: '',
      password: '',
      passwordCheck: '',
      email: '',
      code: '',
    },
    user: null,
    isLogin: false,
  }),
  mutations: {
    SAVE_USER_TEMP(state, credentialsData) {
      state.temp.username = credentialsData.username
      state.temp.nickname = credentialsData.username
      state.temp.password = credentialsData.password
      state.temp.passwordCheck = credentialsData.passwordCheck
      console.log(state.temp.username)
    },
    SAVE_EMAIL_TEMP(state, credentialsEmailCode) {
      state.temp.email = credentialsEmailCode.email
      state.temp.code = credentialsEmailCode.code
    }
  },
  getters: {
    // signupTempInfoGet(state) {
    //   console.log(state.temp)
    //   return state.temp
    
    // },
    signupTempInfoGet: (state) => state.temp
  },
  actions: {
    // 아이디 중복확인
    uniqueUsernameAction: async (commit, username) => {
        // console.log(username);
        // console.log('디스패치가 잘 옴. 이제 api에 요청')
        const response = await checkUsername({"username": username});
        // console.log(response)
        // console.log('api 요청 잘 옴')
        return response
    },
    // 닉네임 중복확인
    uniqueNicknameAction: async (commit, nickname) => {
        // console.log(nickname);
        const response = await checkNickname({"nickname": nickname});
        // console.log(response)
        return response
    },
    // 이메일 중복확인
    sendEmailAction: async (commit, email) => {
      console.log(email);
      const data = JSON.stringify({"userEmail": email})
      const response = await checkEmail(data);
      console.log("이안에 코드있음",response)
      return response
    },
    // 회원정보 잠시 세이브
    signupFirstAction(context, credentialsData) {
      console.log(credentialsData)
      context.commit('SAVE_USER_TEMP', credentialsData)
      return true
    },
    // 회원정보 세이브 2
    signupSecondAction: async (context, credentialsEmailCode) => {
      console.log("두번째 요청까지는 잘 들어옴")
      context.commit('SAVE_EMAIL_TEMP', credentialsEmailCode)
      return true
    },
    signupFinalAction: async (context, signupUser ) => {
        console.log(signupUser)
        const response = await requestRegister(signupUser)
        return response
    }


    
  },
};

export default userStore;
