<template>
  <div>
    <div
      v-if="open_chat_info"
      class="bg-negative"
      @click="open_chat_info = false"
    ></div>
    <ChatFilter />
    <MakeChatButton />
    <div v-for="(datas, i) in data" :key="i">
      <QuietChatList
        :datas="datas"
        @click="
          open_chat_info = true;
          open_chat_info_id = i;
        "
      />
      <div v-if="open_chat_info">
        <ChatInfoModal
          :info_data="data[open_chat_info_id]"
          @close-modal="open_chat_info = $event"
        />
      </div>
    </div>
    <div class="h-4"></div>
  </div>
</template>

<script>
import ChatFilter from "../../components/QuietChat/QuietChatList/ChatFilter";
import MakeChatButton from "../../components/QuietChat/QuietChatList/MakeChatButton";
import QuietChatList from "../../components/QuietChat/QuietChatList/QuietChatList.vue";
import ChatInfoModal from "../../components/QuietChat/QuietChatList/ChatInfoModal";
import QuietChatData from "./QuietChatListData.js";

export default {
  name: "ChatListView",
  data() {
    return {
      data: QuietChatData,
      open_chat_info: false,
      open_chat_info_id: null,
    };
  },
  components: {
    ChatFilter,
    MakeChatButton,
    QuietChatList,
    ChatInfoModal,
  },
};
</script>

<style scoped lang="postcss">
.bg-negative {
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0;
}
</style>
