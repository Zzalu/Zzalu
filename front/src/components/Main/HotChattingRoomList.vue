<template>
  <div>
    <p class="focus-text">요새 핫한 고독방</p>
    <hr class="mb-5" />
    <div class="flex overflow-x-auto">
      <div v-for="(data,b) in room_data" :key="b">
        <HotChattingRoomListItem 
        :room_data="data"
        :b="b"
        />
      </div>
    </div>
  </div>
</template>

<script>
import HotChattingRoomListItem from "../Main/Item/HotChattingRoomListItem";
import HotCahttingRoomData from "../../views/QuietChat/QuietChatListData.js";

export default {
  name: "HotChattingRoomList",
  data() {
    return {
      room_data : HotCahttingRoomData
    }
  },
  components: {
    HotChattingRoomListItem,
  },
};
</script>

<style scoped lang="postcss">
.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}
</style>