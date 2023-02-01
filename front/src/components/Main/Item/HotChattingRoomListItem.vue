<template>
  <div class="quiet-chat-card-contain" @click="open_chat_info = true">
    <!-- 고독방 정보 -->
    <div
      v-if="open_chat_info"
      class="bg-negative"
      @click="open_chat_info = false"
    ></div>

    <div class="quiet-chat-like-contain">
      <font-awesome-icon
        icon="fa-solid fa-heart"
        class="quiet-chat-like-icon"
      />
      <p class="quiet-chat-like-text">{{ room_data.like }}</p>
    </div>
    <div class="quiet-chat-box">
      <div class="quiet-chat-title">{{ room_data.name }}</div>
    </div>
    <ChatInfoModal
      v-if="open_chat_info"
      :info_data="room_data"
      @close-modal="open_chat_info = $event"
    />
  </div>
</template>

<script>
import ChatInfoModal from "../../QuietChat/QuietChatList/ChatInfoModal";

export default {
  name: "HotChattingRoomListItem",
  props: {
    room_data: Object,
    b: Number,
  },
  components: {
    ChatInfoModal,
  },
  data() {
    return {
      open_chat_info: false,
      open_chat_info_id: null,
    };
  },
};
</script>

<style scoped lang="postcss">
.bg-negative {
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0;
}
.quiet-chat-card-contain {
  background-image: url("../../QuietChat/QuietChatList/assets/Infinite_Challenge.jpg");
  @apply border h-48 w-36 bg-center bg-cover rounded-2xl relative ml-5;
}

.quiet-chat-like-contain {
  @apply border flex rounded-2xl bg-zz-p items-center px-1 ml-3 mt-2 mr-1 border-white w-12 h-5;
}

.quiet-chat-like-icon {
  font-size: 0.6rem;
  @apply mr-1 text-zz-s;
}

.quiet-chat-like-text {
  font-size: 0.4rem;
  @apply text-white font-spoq;
}
.quiet-chat-box {
  height: 3rem;
  word-break: keep-all;
  @apply border border-white absolute bottom-0 inset-x-0 flex items-center justify-center bg-zz-p rounded-b-xl;
}

.quiet-chat-title {
  @apply text-white text-sm font-bhs line-clamp-2;
}
</style>