<template>
  <h1 class="account-title mb-10">Sign Up</h1>
  <div class="text-center-container">
    <div class="almost-done">
      <div class="error-sub-title">거의 다 끝났습니다!</div>
      <div class="error-sub-title">이메일로 전송된 인증코드를 입력해주세요.</div>
      <div class="time-remain">3:00</div>
    </div>
  </div>
  <div class="grid grid-cols-4 place-items-center mx-6">
    <div class="input-code"></div>
    <div class="input-code"></div>
    <div class="input-code"></div>
    <div class="input-code"></div>
  </div>
  <div class="text-center-container">
    <div class="didnt-get-mail">
      <div class="error-content">메일을 받지 못하셨나요?</div>
      <button class="send-again-button">인증 메일 재전송</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'InputCodeForm',
}
</script>

<style>

</style>