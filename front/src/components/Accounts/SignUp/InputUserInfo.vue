<template>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="아이디를 입력하세요"
      v-model="state.credentials.username.value"
    />
    <button class="button-in-input" @click="uniqueUsername">중복확인</button>
  </div>
  <div class="error" v-if="errors.username" > {{ errors.username }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="닉네임을 입력하세요"
      v-model="state.credentials.nickname.value"
    />
    <button class="button-in-input" @click="uniqueNickname" >중복확인</button>
  </div>
  <div class="error" v-if="errors.nickname" > {{ errors.nickname }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="password"
      class="account-input"
      placeholder="비밀번호를 입력하세요"
      v-model="state.credentials.password"
    />
  </div>
  <div class="error" v-if="errors.password" > {{ errors.password }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="password"
      class="account-input"
      placeholder="비밀번호를 다시 입력하세요"
      v-model="state.credentials.passwordCheck"
    />
  </div>
  <div class="error" v-if="errors.passwordCheck" > {{ errors.passwordCheck }} </div>
  <div class="account-right">
    <div class="redir-accounts">
      <div>이미 계정이 있으신가요?</div>
      <router-link to="/login" class="redir-accounts-click">로그인</router-link>
    </div>
    <button class="go-next-button" @click.prevent="[onSubmit(), sendSignupInfo()]">다음</button>
  </div>
</template>

<script>
import useVuelidate from "@vuelidate/core";
import { required } from "@vuelidate/validators";
import SignupValidations from '../../../services/SignupValidations'
import { useStore } from 'vuex';
import { reactive, watch } from 'vue'
// import { useRoute } from 'vue-router';
// import {mapActions} from 'vuex';


export default {
  name: "InputUserInfo",
  setup() {
    const store = useStore();

    const state = reactive({
      credentials: {
        password1: "",
        password2: "",
        username: { value: "", status: false },
        nickname: { value: "", status: false },
        email: "",
        code: '',
      },
    })
    // 아이디 닉네임 바꾸는지 확인
    watch(() => state.credentials.username.value, (newValue, oldValue) => {
      state.credentials.username.status = false
      console.log('변화 감지', {newValue, oldValue})
    })
    watch(() => state.credentials.nickname.value, (newValue, oldValue) => {
      state.credentials.nickname.status = false
      console.log('변화 감지', {newValue, oldValue})
    })
    // 아이디 중복확인
    const uniqueUsername = async function () {
      const result = await store.dispatch('userStore/uniqueUsernameAction', state.credentials.username )
      if (result.data.unique == true) {
        state.credentials.username.status = true
        console.log('중복이 아니군여')
      } else {
        console.log('중복이군여')
        state.credentials.username.status = false
        alert("아이디가 중복이에...")
      } 
    }
    // 닉네임 중복확인
    const uniqueNickname = async function () {
      const result = await store.dispatch('userStore/uniqueNicknameAction', state.credentials.nickname )
      if (result.data.unique==true) {
        state.credentials.nickname.status = true
        alert("닉 사용가능.")
      } else {
        state.credentials.nickname.status = false
        alert("이미 사용 중인 닉입니다.")
      }
    }

    const sendSignupInfo = function () {
          // 아이디 닉네임 중복확인 다 했는지 확인
      if (!state.credentials.username.status){
        alert("이메일 중복확인이 필요합니다.")
        return
      } else if (!state.credentials.nickname.status){
        alert("닉네임 중복확인이 필요합니다.")
        return
      } else {
        const credentialsData = {
          username: state.credentials.username.value,
          nickname: state.credentials.nickname.value,
          password: state.credentials.password,
          passwordCheck: state.credentials.passwordCheck,
          email: state.credentials.email,
          code: state.credentials.code,
        }
      // 회원가입 등록
      this.$store.dispatch('userStore/signupFirstAction', credentialsData)
      console.log("회원가입 요청 1 잘 갔음")
      this.$router.push({name: 'input-email'})
      }
    }
  
  return {
    state,
    uniqueUsername,
    uniqueNickname,
    sendSignupInfo,
  }
  },
  data() {
    return {
      v$: useVuelidate(),
      // 회원 정보
      username: { value: "", status: false },
      nickname: { value: "", status: false },
      password: "",
      passwordCheck: "",
      email: "",
      // 에러
      errors: [],
    };
  },
  computed: {

  },
  methods: {
    onSubmit() {
      this.v$.$touch();
      const validations = new SignupValidations(
        this.username.value, 
        this.nickname.value, 
        this.password,
        this.passwordCheck,
        );
      this.errors = validations.checkValidations();

      // 인풋값 다 입력했는지 확인
      if (!this.v$.$error) {
        console.log("모든 인풋값 입력 완료")
      } else {
        alert("모든 필드에 값을 입력 해주세요");
      }

      if ('username' in this.errors || 'nickname' in this.errors || 'password' in this.errors|| 'passwordCheck' in this.errors) {
        return false;
      }


    }
  
    

  },
  validations() {
    return {
      username: { required },
      nickname: { required },
      password: { required },
      passwordCheck: { required },
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
