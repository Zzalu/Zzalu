<template>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="아이디를 입력하세요"
      v-model="username"
    />
    <button class="button-in-input" @click="checkDuplicate">중복확인</button>
  </div>
  <div class="error" v-if="errors.username" > {{ errors.username }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="닉네임을 입력하세요"
      v-model="nickname"
    />
    <button class="button-in-input" >중복확인</button>
  </div>
  <div class="error" v-if="errors.nickname" > {{ errors.nickname }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="password"
      class="account-input"
      placeholder="비밀번호를 입력하세요"
      v-model="password"
    />
  </div>
  <div class="error" v-if="errors.password" > {{ errors.password }} </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="password"
      class="account-input"
      placeholder="비밀번호를 다시 입력하세요"
      v-model="passwordCheck"
    />
  </div>
  <div class="error" v-if="errors.passwordCheck" > {{ errors.passwordCheck }} </div>
  <div class="account-right">
    <div class="redir-accounts">
      <div>이미 계정이 있으신가요?</div>
      <router-link to="/login" class="redir-accounts-click">로그인</router-link>
    </div>
    <button class="go-next-button" @click.prevent="onSubmit">다음</button>
  </div>
</template>

<script>
import useVuelidate from "@vuelidate/core";
import { required } from "@vuelidate/validators";
import SignupValidations from '../../../services/SignupValidations'
// import { useStore } from 'vuex';
// import { useRoute } from 'vue-router';
// import {mapActions} from 'vuex';

export default {
  name: "InputUserInfo",
  // setup() {
  //   const store = useStore();
  // },
  data() {
    return {
      v$: useVuelidate(),
      // 회원 정보
      username: "",
      nickname: "",
      password: "",
      passwordCheck: "",
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
        this.username, 
        this.nickname, 
        this.password,
        this.passwordCheck,
        );
      this.errors = validations.checkValidations();

      const username = this.username
      const nickname = this.nickname
      const password = this.password
      const passwordCheck = this.passwordCheck
      const credentials = {
        username: username,
        nickname: nickname,
        password: password,
        passwordCheck: passwordCheck
      }

      if (!this.v$.$error) {
        console.log("모든 인풋값 입력 완료")
      } else {
        alert("모든 필드에 값을 입력 해주세요");
      }
      if ('username' in this.errors || 'nickname' in this.errors || 'password' in this.errors|| 'passwordCheck' in this.errors) {
        return false;
      }
      // 회원가입 등록
      this.$store.dispatch('signUp', credentials)
      this.$router.push({name: 'input-email'})
    },
    // usernameExist() {
    //   if (this.credentials.username) {
        
    //   }
    // }
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
