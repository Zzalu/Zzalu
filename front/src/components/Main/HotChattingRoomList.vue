<template>
  <div>
    <p class="focus-text">요새 핫한 고독방</p>
    <hr class="mb-3 border-0 bg-zz-light-input h-1 dark:bg-zz-dark-div" />
    <div class="items">
      <div v-for="(data, b) in room_data" :key="b">
          <HotChattingRoomListItem
            :room_data="data"
            :b="b"
            @click="chat_data(b)"
            class="hot-chatting"
          />
      </div>
    </div>
  </div>
</template>

<script>
import HotChattingRoomListItem from "../Main/Item/HotChattingRoomListItem";
import HotCahttingRoomData from "../../views/QuietChat/QuietChatListData.js";
import { useStore } from "vuex";

export default {
  name: "HotChattingRoomList",
  setup() {
    const store = useStore();

    const send_chat_data = (e) => {
      store.commit("quietChatStore/open_chat_id", e);
      setTimeout(function() {
        store.commit("quietChatStore/open_chat_info");
      }, 200)
    };
    return {
      send_chat_data,
    };
  },
  data() {
    return {
      room_data: HotCahttingRoomData,
    };
  },
  components: {
    HotChattingRoomListItem,
  },
  methods: {
    chat_data(b) {
      this.send_chat_data(b);
    },
  },
};
</script>

<style scoped lang="postcss">

.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5 dark:text-white;
}

.items {
  @apply flex overflow-x-auto;
}
.items::-webkit-scrollbar {
  display: none;
}
.hot-chatting {
  box-shadow: 0 0 7px black;
  @apply mt-2;
}
</style>