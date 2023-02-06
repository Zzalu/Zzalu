import { checkUsername, checkNickname, checkEmail } from "@/api/userAccount";

const userStore = {
  namespaced: true,
  // state: () => ({
  //   isLogin: false,
  // }),
  state: {
    temp: {}
  },
  mutations: {
    SAVE_USER_TEMP(state, credentialsData) {
      state.temp = credentialsData
    },
    SAVE_EMAIL_TEMP(state, credentialsEmailCode) {
      state.temp.email = credentialsEmailCode.email
      state.temp.code = credentialsEmailCode.code
    }
  },
  getters: {},
  actions: {
    // 아이디 중복확인
    uniqueUsernameAction: async (commit, username) => {
        // console.log(username);
        const response = await checkUsername({"username": username});
        // console.log(response)
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
      console.log("거의끝났어",response)
      return response
    },
    // 회원정보 잠시 세이브
    signupFirstAction(context, credentialsData) {
      context.commit('SAVE_USER_TEMP', credentialsData)
    },
    // 회원정보 세이브 2
    signupSecondAction(context, credentialsEmailCode) {
      console.log("두번째 요청까지는 잘 들어옴")
      context.commit('SAVE_EMAIL_TEMP', credentialsEmailCode)
    }


    
  },
};

export default userStore;
