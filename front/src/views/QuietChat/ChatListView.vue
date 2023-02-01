<template>
  <div>
    <ChatSearchTopNav />
    <div v-if="open_chat_info" class="bg-negative" @click="close_chat"></div>
    <ChatFilter />
    <MakeChatButton />
    <div v-for="(datas, i) in data" :key="i">
      <QuietChatList :datas="datas" @click="chat_data(i)" />
    </div>
    <div v-if="open_chat_info">
      <ChatInfoModal :info_data="data[open_chat_id]" />
    </div>
    <div class="h-4"></div>
    <div v-if="tmpisLogin">
      <MainBottomNav />
    </div>
  </div>
</template>

<script>
import ChatFilter from "../../components/QuietChat/QuietChatList/ChatFilter";
import MakeChatButton from "../../components/QuietChat/QuietChatList/MakeChatButton";
import QuietChatList from "../../components/QuietChat/QuietChatList/QuietChatList.vue";
import ChatInfoModal from "../../components/QuietChat/QuietChatList/ChatInfoModal";
import QuietChatData from "./QuietChatListData.js";
import ChatSearchTopNav from "../../components/Common/NavBar/ChatSearchTopNav";
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav";
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "ChatListView",
  setup() {
    const store = useStore();

    const open_chat_info = computed(
      () => store.state.quietChatStore.open_chat_info
    );
    const open_chat_id = computed(
      () => store.state.quietChatStore.open_chat_id
    );
    const send_chat_data = (e) => {
      store.commit("quietChatStore/open_chat_info");
      store.commit("quietChatStore/open_chat_id", e);
    };
    const close_chat_info = () => {
      store.commit("quietChatStore/close_chat_info");
    };
    return {
      open_chat_info,
      open_chat_id,
      send_chat_data,
      close_chat_info,
    };
  },
  components: {
    ChatFilter,
    MakeChatButton,
    QuietChatList,
    ChatInfoModal,
    ChatSearchTopNav,
    MainBottomNav,
  },
  data() {
    return {
      data: QuietChatData,
      tmpisLogin: true,
    };
  },
  methods: {
    chat_data(i) {
      this.send_chat_data(i);
    },
    close_chat() {
      this.close_chat_info();
    },
  },
  watch: {
    open_chat_info: function (value) {
      if (value == true) {
        document.body.style.overflow = "hidden";
      } else {
        document.body.style.removeProperty("overflow");
      }
    },
  },
};
</script>

<style scoped lang="postcss">
.bg-negative {
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0;
}
</style>
