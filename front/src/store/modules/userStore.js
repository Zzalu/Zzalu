import { checkUsername, checkNickname, checkEmail, requestRegister, requestLogin, requestUsername, requestDelete } from "@/api/userAccount";

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
    accessToken: "",
    refreshToken: "",
    // isLogin: false,
  }),
  mutations: {
    SAVE_USER_TEMP(state, credentialsData) {
      state.temp.username = credentialsData.username
      state.temp.nickname = credentialsData.nickname
      state.temp.password = credentialsData.password
      state.temp.passwordCheck = credentialsData.passwordCheck
    },
    SAVE_EMAIL_TEMP(state, credentialsEmailCode) {
      state.temp.email = credentialsEmailCode.email
      state.temp.code = credentialsEmailCode.code
      window.localStorage.setItem("temp_email", credentialsEmailCode.email)
    },
    SAVE_CURRENT_USER(state, loginData ) {
      console.log('지금 접속한 사람 저장')
      console.log(loginData)
      console.log(loginData.data)
      state.user = loginData.data.username
      state.accessToken = loginData.data.accessToken
      state.refreshToken = loginData.data.refreshToken
      localStorage.setItem('id', loginData.data.username)
      localStorage.setItem('token', loginData.data.accessToken)
      console.log('지금 접속한 사람 출력', loginData.data.username)
      console.log('지금 접속한 사람 출력', state.user)
    },
    DELETE_TEMP_USER(state) {
      state.temp.username = ''
      state.temp.nickname = ''
      state.temp.password = ''
      state.temp.passwordCheck = ''
      state.temp.email = ''
      state.temp.code = ''
    },
    LOGOUT (state) {
      localStorage.removeItem('id')
      localStorage.removeItem('token')
      state.user = null
      state.accessToken = ''
      state.refreshToken = ''
      state.isLogin = false
    },
  },
  getters: {
    // signupTempInfoGet(state) {
    //   console.log(state.temp)
    //   return state.temp
    
    // },
    signupTempInfoGet: (state) => state.temp,
    // return signupTempInfoGet
  },
  actions: {
    // -------------------------------------------------------------------
    // 회원가입
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
      
      const data = JSON.stringify({"userEmail": email})
      const response = await checkEmail(
        data,
        (res) => {
          console.log("삭제잘됨?")
          return res
        },
        (err) => {
          console.log("비번 틀렷거나..")
          return err.response
      }
        );
      // console.log("이안에 코드있음",response)
      return response
    },
    // 회원정보 잠시 세이브
    signupFirstAction(context, credentialsData) {
      // console.log(credentialsData)
      context.commit('SAVE_USER_TEMP', credentialsData)
      return true
    },
    // 회원정보 세이브 2
    signupSecondAction: async (context, credentialsEmailCode) => {
      console.log(credentialsEmailCode)
      context.commit('SAVE_EMAIL_TEMP', credentialsEmailCode)
      
      return true
    },
    signupFinalAction: async (context, signupUser ) => {
        console.log(signupUser)
        const response = await requestRegister(
          signupUser,
          (res) => {
            return res
          },
          (err) => {
            return err.response
        })
        return response
    },
    signupInfoDelete: (context) => {
      context.commit('DELETE_USER_TEMP')
    },
    // -----------------------------------------------------------
    // 로그인
    loginAction: async (context, loginData ) => {
      // console.log("store잘 들어옴", loginData)
      const response = await requestLogin(loginData)
      // console.log("store 다시 잘 들어옴", response)
      context.commit('SAVE_CURRENT_USER', response)
      console.log(response.data)
      // localStorage.setItem('id', response.data.username)
      // localStorage.setItem('token', response.data.accessToken)
      console.log("지금 접속유저 저장 잘 됨", response)
      return response
    },

    // 로그아웃
    logoutAction: async (context) => {
      // console.log("store잘 들어옴", loginData)
      // const response = await requestLogin(loginData)
      // console.log("store 다시 잘 들어옴", response)
      context.commit('LOGOUT')
      // console.log(response.data)
      // localStorage.setItem('id', response.data.username)
      // localStorage.setItem('token', response.data.accessToken)
      // console.log("지금 접속유저 저장 잘 됨", response)
      // return response


    },
    // ----------------------------------------------------------
    // 아이디찾기
    sendUsernameAction: async (commit, email) => {
      console.log(email);
      const data = JSON.stringify({"userEmail": email})
      const response = await requestUsername(data);
      // console.log("이안에 코드있음",response)
      return response
    },
  // --------------------------------------------------------------
  // 회원탈퇴
    userDeleteAction: async (context, pwd) => {
      console.log('여기도 들어옴?')
      const response = requestDelete(
        pwd,
        (res) => {
          window.localStorage.clear()
          console.log("삭제 잘 되었다는 뜻",res);
          return res
        },
        (err) => {
          console.log(err.response.status)
          return 400
        })
      return response
    }
  },

};

export default userStore;
