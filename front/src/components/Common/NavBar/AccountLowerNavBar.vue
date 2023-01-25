<template>
  <!-- 가입하기 nav -->
  <div
    class="fixed inset-x-0 bottom-0 flex flex-wrap items-center justify-between h-nav-height content-center bg-white border-t-2 border-zz-light-div"
  >
    <span class="inline-block ml-12 text-base">계정이 없으신가요?</span>
    <button class="mr-16 text-zz-p text-xl">가입하기</button>
  </div>
</template>

<script>
export default {
  name: 'AccountLowerNavBar',
};
</script>

<style>
span {
  display: inline-block;
}
</style>
