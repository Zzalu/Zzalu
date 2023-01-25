<template>
  <div>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <h1 class="page-title">Login</h1>
    <h2 class="input-title">아이디</h2>
    <input type="text" class="login-input" />
    <h2 class="input-title">비밀번호</h2>
    <input type="text" class="login-input" />
    <div class="find-id-and-pwd">
      <div class="find-id">아이디 |</div>
      <div>비밀번호 찾기</div>
    </div>
    <div class="divide-social">
      <hr class="div-line" />
      <div class="or-start-with">Or Start With</div>
    </div>
  </div>
  <div class='center-containers'>
    <img src="./assets/naver_icon.png" class='login-icon' alt="">
    <img src="./assets/kakao_icon.png" class='login-icon' alt="">
  </div>
  <div class='center-containers'>
    <button class='submit-button'>로그인</button>
  </div>
</template>

<script>
export default {
  name: "LoginView",
};
</script>

<style scoped>
/* 회원관련 css 모두 모은 파일 */
@import "./assets/accounts.css";

/* 아이디 비번찾기 */
.find-id-and-pwd {
  @apply flex float-right text-zz-darkgray text-xs mt-1;
}

.find-id {
  @apply mx-1;
}

/* 소셜로그인 */
.divide-social {
  @apply inline-flex items-center justify-center w-full;
}

.div-line {
  @apply w-64 h-0.5 my-6 bg-zz-p border-0 rounded;
}

.or-start-with {
  @apply absolute px-3 text-sm -translate-x-1/2 bg-white font-spoq text-zz-darkgray;
}

.login-icon {
  @apply w-12 mx-2 border-8 border-gray-100 bg-gray-100 rounded-full
}
</style>



