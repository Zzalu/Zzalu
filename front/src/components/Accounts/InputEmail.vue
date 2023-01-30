<template>
  <h1 class="account-title mb-10" v-if="$route.name == 'signup'">Sign Up</h1>
  <div class="mt-20">
    <h2 class="find-id-input-title" v-if="$route.name == 'find-id'">아이디 찾기</h2>
    <font-awesome-icon icon="fa-solid fa-envelope" class='icon-aligned-left'/>
      <input
        type="text"
        class="account-input"
        placeholder="이메일을 입력하세요"
      />
      <!-- <div class="error-msg text-sm">아이디중복이세요;</div> -->
    <div class="flex float-right mt-10">
      <button class="go-next-button">다음</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'InputEmail'
}
</script>

<style lang="postcss" scoped>
  .find-id-input-title {
    @apply font-spoq text-lg text-zz-darkgray mt-3 mb-1 mx-1
  }
</style>