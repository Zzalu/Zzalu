<template>
  <!-- 채팅방 안 nav-->
  <div class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center">
    <span class="font-bold text-xl">
      {{ room_name }}
    </span>
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon icon="fa-solid fa-chevron-left" class="text-2xl" />
    </span>
    <span class="inline-block px-4 absolute right-0">
      <font-awesome-icon icon="fa-regular fa-heart" class="text-xl text-zz-s" />
    </span>
  </div>
</template>

<script>
export default {
  name: 'ChatRoomTopNav',
  data() {
    return {
      room_name: '무한도전 고독방',
    };
  },
};
</script>

<style></style>
