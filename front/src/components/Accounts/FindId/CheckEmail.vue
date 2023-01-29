<template>
  <div class="text-center-container mt-10">
    <div class="almost-done">
      <div class="error-sub-title">RIGHTEMAILID@naver.com 으로</div>
      <div class="error-sub-title">아이디를 보냈어요.</div>
      <div class="error-sub-title">이메일로 전송된 아이디를 확인해주세요.</div>
    </div>
  </div>
  <div class="text-center-container mt-10">
    <div class="didnt-get-mail mb-6">
      <div class="error-content">메일을 받지 못하셨나요?</div>
      <button class="send-again-button">인증 메일 재전송</button>
    </div>
    <button class="submit-button">로그인 하러 가기</button>
  </div>

</template>

<script>
export default {
  name: 'CheckEmail',

}
</script>

<style>

</style>