<template>
  <!-- 로고있는 위쪽 nav -->
  <div class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center">
    <!-- 로고랑 제목 -->
    <div class="w-20">
      <img alt="ZZalu Light logo" class="logo" src="../../../assets/zzalu_logo_light.png" />
    </div>
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon icon="fa-solid fa-chevron-left" class="text-2xl" />
    </span>
  </div>
</template>

<script>
export default {
  name: 'LogoTopNavBar',
};
</script>

<style></style>
