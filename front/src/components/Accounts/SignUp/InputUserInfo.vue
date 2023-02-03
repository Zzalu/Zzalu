<template>
  <!-- <h1 class="account-title mb-10">Sign Up</h1> -->
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="아이디를 입력하세요"
      v-model = "credentials.username"
    />
    <button class="button-in-input">중복확인</button>
  </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="닉네임을 입력하세요"
      v-model = "credentials.nickname"
    />
    <button class="button-in-input">중복확인</button>
  </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="비밀번호를 입력하세요"
      v-model = "credentials.password"
    />
  </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="비밀번호를 다시 입력하세요"
      v-model = "credentials.passwordCheck"
    />
  </div>
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
export default {
  name: "InputUserInfo",
  data() {
    return {
      v$: useVuelidate(),
      // 회원 정보
      credentials: {
        username: '',
        nickname: '',
        password: '',
        passwordCheck: '',
      }
    };
  },
  methods: {
    // clearFields() {
    //   this.credentials = {
    //     username: null,
    //     nickname: null,
    //     password: null,
    //     passwordCheck: null,
    //   };
    // },
    onSubmit() {
      this.v$.$touch();
      if (!this.v$.$error) {
        // if ANY fail validation
        alert('Form successfully submitted.')
      } else {
        alert('Form failed validation')
      }
    }
    },
  validations() {
    return {
      credentials: {
        username: { required },
        nickname: { required },
        password: { required },
        passwordCheck: { required },
      },
    }
  },
  // },
  // validators: {
    // username: function (value) {
    //     return Validator.value(value).required()
    // },
    // nickname: function (value) {
    //     return Validator.value(value).required()
    // },
    // password: function (value) {
    //     return Validator.value(value).required()
    // },
    // passwordCheck: function (value) {
    //     return Validator.value(value).required()
    // },
  // },
};
</script>

<style lang="postcss" scoped>
/* 오른쪽정렬 바깥 컨테이너 */
.account-right {
  @apply grid grid-rows-2 justify-end;
}
</style>
