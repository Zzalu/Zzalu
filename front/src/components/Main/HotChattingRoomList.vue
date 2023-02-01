<template>
  <div>
    <p class="focus-text">요새 핫한 고독방</p>
    <hr class="mb-5" />
    <div class="flex overflow-x-auto">
      <div v-for="(data,b) in room_data" :key="b">
        <HotChattingRoomListItem 
        :room_data="data"
        @click="chat_data(b)"
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
      store.commit("quietChatStore/open_chat_info")
      store.commit("quietChatStore/open_chat_id",e)
    };

    return {
      send_chat_data
    }
  },
  data() {
    return {
      room_data : HotCahttingRoomData
    }
  },
  components: {
    HotChattingRoomListItem,
  },
  methods : {
    chat_data(b) {
      this.send_chat_data(b)
    }
  }
};
</script>

<style scoped lang="postcss">
.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}
</style>