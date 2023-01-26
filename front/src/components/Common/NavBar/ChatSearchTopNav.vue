<template>
  <!-- 채팅방 검색 nav-->
  <div class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center">
    <div class="bg-zz-light-input w-80 h-9 flex items-center px-5 py-1 rounded-lg">
      <font-awesome-icon icon="fa-solid fa-magnifying-glass" class="text-zz-darkgray mr-2" />
      <input type="text" placeholder="채팅방 검색" class="bg-transparent text-zz-darkgray" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChatSearchTopNav',
};
</script>

<style></style>
