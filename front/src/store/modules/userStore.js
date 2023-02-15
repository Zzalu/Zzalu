import { checkUsername, checkNickname, checkEmail, requestRegister, requestLogin, requestUsername, requestChangeInfo, requestDelete, requestManager } from "@/api/userAccount";

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
    nickname: "",
    isManager: false,
    pk: null
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
      state.user = loginData.data.username
      state.accessToken = loginData.data.accessToken
      state.refreshToken = loginData.data.refreshToken
      state.nickname = loginData.data.nickname
      state.isManager = loginData.data.isManager
      state.pk = loginData.data.id
      localStorage.setItem('current_userid', loginData.data.username)
      localStorage.setItem('current_nickname', loginData.data.nickname)
      localStorage.setItem('current_pk', loginData.data.id)
      localStorage.setItem('token', loginData.data.accessToken)
      localStorage.setItem('isManager', loginData.data.isManager)
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
    // PATCH_USER_INFO (state, changedData) {
    //   if (changedData.profileIntro) {
    //     console.log('아이고')
    //     state.myIntro = changedData.profileIntro
    //   }
    //   if (changedData.profileImg) {state.myImg = changedData.profileImg}
    //   if (changedData.newNickname) {
    //     state.nickname = changedData.newNickname
    //     localStorage.setItem('current_nickname', changedData.newNickname)
    //   }
    //   // state.myImg = changedData.profileImg,
    //   // state.nickname = changedData.newNickname
    //   console.log('데이터 바꾼거', changedData)
    //   console.log(state.nickname)
    //   console.log(state.myImg)
    // }
    SAVE_MANAGER_STATE (state) {
      state.isManager = true
    }
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
        console.log(nickname);
        const response = await checkNickname({"nickname": nickname});
        console.log(response)
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
      const response = await requestLogin(
        loginData,
        (res) => {
          console.log(res)
          context.commit('SAVE_CURRENT_USER', res)
          return res
        },
        (error) => {
          console.log(error.response, "에러에러");
          
          return error.response
        }
        )
      // console.log("store 다시 잘 들어옴", response)
      

      // localStorage.setItem('id', response.data.username)
      // localStorage.setItem('token', response.data.accessToken)
      return response

    },

    // 로그아웃
    logoutAction: async (context) => {
      context.commit('LOGOUT')
      localStorage.removeItem('current_userid')
      localStorage.removeItem('current_nickname')
      localStorage.removeItem('stat_title')
      localStorage.removeItem('temp_email')
      localStorage.removeItem('profile_id')
      localStorage.removeItem('profile_user')
      localStorage.removeItem('isManager')
      localStorage.removeItem('stat_num')
    },
    // ----------------------------------------------------------
    // 아이디찾기
    sendUsernameAction: async (commit, email) => {
      localStorage.setItem('temp_email', email)
      const data = JSON.stringify({"userEmail": email})
      const response = await requestUsername(
        data,
        (res) => {
          console.log(res)
          return res
        },
        (error) => {
          console.log(error.response);
          return error.response
        }
      );
      return response
      // console.log("이안에 코드있음",response)
    },
    // ----------------------------------------------------------
    // 회원정보수정
    patchUserInfoAction: (params, form) => {
      console.log(form)
      requestChangeInfo(
        params,
        form,
        ({data}) => {
          localStorage.removeItem('current_nickname')
          console.log(data, "성공입니다")
          localStorage.setItem('current_nickname', data.nickname)
        },
        (err) => {
          console.log(err, "실패입니다")
          alert(err.response.data.message);
      })
    },
    // ----------------------------------------------------------
    // 매니저
    managerApplyAction: async (context) => {
      console.log("여기는 언제들어와")
      // let test = null
      return new Promise((resolve, reject) => {
        requestManager(
          (res) => {
            context.commit('SAVE_MANAGER_STATE')
            console.log(res)
            resolve(200)
          },
          (error) => {
            console.log("여기는 언제들어와2")
            console.log(error.response, '에러');
            reject(500)
          }
        );
        // setTimeout(() => {        
        //   console.log('리턴값', test)
        //   return
        // }, 100);
      }
      )

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
