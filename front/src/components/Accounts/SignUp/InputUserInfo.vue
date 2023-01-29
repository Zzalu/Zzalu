<template>
  <h1 class="account-title mb-10">Sign Up</h1>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="아이디를 입력하세요"
    />
    <button class="button-in-input">중복확인</button>
    <!-- <div class="error-msg text-sm">아이디중복이세요;</div> -->
  </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="닉네임을 입력하세요"
    />
    <button class="button-in-input">중복확인</button>
  </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="비밀번호를 입력하세요"
    />
  </div>
  <div class="input-without-title">
    <font-awesome-icon icon="fa-solid fa-lock" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      placeholder="비밀번호를 다시 입력하세요"
    />
  </div>
  <div class="account-right">
    <div class="redir-accounts">
    <div>이미 계정이 있으신가요?</div>
    <div class="redir-accounts-click">로그인</div></div>
    <button class="go-next-button">다음</button>
  </div>



</template>

<script>
export default {
  name: "InputUserInfo",
};
</script>

<style lang="postcss" scoped>
/* 오른쪽정렬 바깥 컨테이너 */
.account-right {
  @apply grid grid-rows-2 justify-end
}
</style>