<template>
  <div>
    <div class="create-chat-button">
      <router-link to="/make-chat" class="create-chat">
        새로운 고독방 개설하기
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "MakeChatButton",
};
</script>

<style scoped lang="postcss">
.create-chat-button {
  @apply bg-zz-p rounded-lg w-9/12 h-10 text-white mx-auto text-center py-2;
}
.create-chat {
  @apply line-clamp-1
}
</style>