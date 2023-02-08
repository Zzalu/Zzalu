<template>
  <!-- 내 보드 수정중 nav-->
  <div class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center">
    <div class="flex flex-col">
      <input type="text" class="text-center focus:outline-none font-bold" value="무도짤" />
      <span class="w-full h-divider-height bg-black"></span>
    </div>
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon icon="fa-solid fa-chevron-left" class="text-2xl" />
    </span>
    <span class="inline-block px-4 absolute right-0 text-zz-s dark:text-white"> 완료 </span>
  </div>
</template>

<script>
export default {
  name: 'BoardTopNav',
  data() {
    return {
      board_title: '무도짤',
    };
  },
};
</script>

<style></style>
