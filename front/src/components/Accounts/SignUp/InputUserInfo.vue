<template>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="아이디를 입력하세요"
      v-model="state.credentials.username"
    />
    <button class="button-in-input" @click="uniqueUsername">중복확인</button>
  </div>
  <div class="error" v-if="errorMsgs.err.username" > {{ errorMsgs.err.username }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="닉네임을 입력하세요"
      v-model="state.credentials.nickname"
    />
    <button class="button-in-input" @click="uniqueNickname" >중복확인</button>
  </div>
  <div class="error" v-if="errorMsgs.err.nickname" > {{ errorMsgs.err.nickname }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="password"
      class="account-input"
      placeholder="비밀번호를 입력하세요"
      v-model="state.credentials.password"
    />
  </div>
  <div class="error" v-if="errorMsgs.err.password" > {{ errorMsgs.err.password }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="password"
      class="account-input"
      placeholder="비밀번호를 다시 입력하세요"
      v-model="state.credentials.passwordCheck"
    />
  </div>
  <div class="error" v-if="errorMsgs.err.passwordCheck" > {{ errorMsgs.err.passwordCheck }} </div>
  <div class="account-right">
    <div class="redir-accounts">
      <div>이미 계정이 있으신가요?</div>
      <router-link to="/login" class="redir-accounts-click">로그인</router-link>
    </div>
    <button class="go-next-button" @click.prevent="[submitRules(), sendSignupInfo()]">다음</button>
  </div>
</template>




<script>
// import useVuelidate from "@vuelidate/core";
// import { required } from "@vuelidate/validators";
import SignupInfoValidations from '../../../services/SignupValidations'
import { useStore } from 'vuex';
import { reactive, watch } from 'vue'
import { useRouter } from 'vue-router';
// import {mapActions} from 'vuex';


export default {
  name: "InputUserInfo",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      credentials: {
        password: "",
        passwordCheck: "",
        username: '',
        nickname: '',
        email: "",
        code: '',
      },
      nicknameState: false,
      usernameState: false,
    })
    const errorMsgs = reactive({
      err: {
        password: "",
        passwordCheck: "",
        username: '',
        nickname: '',
        email: "",
        code: '',
      }
    })
    // 아이디 닉네임 바꾸는지 확인
    watch(() => state.credentials.username, (newValue, oldValue) => {
      if (newValue != oldValue) {state.usernameState = false}
      // console.log('변화 감지', {newValue, oldValue})
    })
    watch(() => state.credentials.nickname, (newValue, oldValue) => {
      if (newValue != oldValue) {state.nicknameState = false}
      // console.log('변화 감지', {newValue, oldValue})
    })
    // 아이디 중복확인
    const uniqueUsername = async function () {
      // console.log('아이디 중복확인 함수 잘 들어옴, 이제 스토어에 디스패치 간다')
      const result = await store.dispatch('userStore/uniqueUsernameAction', state.credentials.username )
      if (result.data.unique == true) {
        state.usernameState = true
        // console.log('중복이 아니군여')
      } else {
        // console.log('중복이군여')
        state.usernameState = false
        alert("사용 중인 아이디입니다. |n 다른 아이디를 등록해주세요.")
      } 
    }
    // 닉네임 중복확인
    const uniqueNickname = async function () {
      const result = await store.dispatch('userStore/uniqueNicknameAction', state.credentials.nickname )
      if (result.data.unique==true) {
        state.nicknameState = true
        alert("닉 사용가능.")
      } else {
        state.nicknameState = false
        alert("이미 사용 중인 닉입니다.")
      }
    }

    // 네이밍규칙
    const submitRules = function () {
      const validations = new SignupInfoValidations(
        state.credentials.username, 
        state.credentials.nickname, 
        state.credentials.password,
        state.credentials.passwordCheck,
        );
      // console.log(validations.checkValidations())
      const errors = validations.checkValidations();
      
      if ('username' in errors) {
        errorMsgs.err.username = errors['username']
      } 
      if ('nickname' in errors) {
        errorMsgs.err.nickname = errors['nickname']
      }
      if ('password' in errors) {
        errorMsgs.err.password = errors['password']
      }
      if ('passwordCheck' in errors) {
        errorMsgs.err.passwordCheck = errors['passwordCheck']
      }
    }
    


    // 가입요청1
    const sendSignupInfo = async function () {
          // 아이디 닉네임 중복확인 다 했는지 확인
      if (!state.usernameState){
        alert("아이디 중복확인이 필요합니다.")
        return
      } else if (!state.nicknameState){
        alert("닉네임 중복확인이 필요합니다.")
        return
      } else {
        const credentialsData = {
          username: state.credentials.username,
          nickname: state.credentials.nickname,
          password: state.credentials.password,
          passwordCheck: state.credentials.passwordCheck,
          email: state.credentials.email,
          code: state.credentials.code,
        }
      // 회원가입 등록
      const res = await store.dispatch('userStore/signupFirstAction', credentialsData)
      if (res) {
        console.log("회원가입 요청 1 잘 갔음")
        router.push({name: 'input-email'})
      }
      }
    }
  
  return {
    
    state,
    errorMsgs,
    uniqueUsername,
    uniqueNickname,
    submitRules,
    sendSignupInfo,
  }
  },
  data() {
    return {
      // 에러
      errors: [],
    };
  },
};
</script>

<style lang="postcss" scoped>
/* 오른쪽정렬 바깥 컨테이너 */
.account-right {
  @apply grid grid-rows-2 justify-end;
}
</style>
