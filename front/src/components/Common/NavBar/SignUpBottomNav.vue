<template>
  <!-- 가입하기 nav -->
  <div
    class="fixed inset-x-0 bottom-0 flex flex-wrap items-center justify-center h-nav-height content-center bg-white border-t-2 border-zz-light-div"
  >
    <div class="flex items-center">
      <span class="line-clamp-1">계정이 없으신가요?</span>
      <button class="text-zz-p text-xl ml-10"><p class="line-clamp-1">가입하기</p></button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SignUpBottomNav',
};
</script>

<style>
span {
  display: inline-block;
}
</style>
