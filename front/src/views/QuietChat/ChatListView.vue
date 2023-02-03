<template>
  <div class="test">
    <ChatSearchTopNav />
    <div v-if="open_chat_info" class="bg-negative" @click="close_chat"></div>
    <ChatFilter />
    <MakeChatButton />
    <!-- 고독방이 있다면 -->
    <div v-if="data.length >= 1">
      <div v-for="(datas, i) in data" :key="i">
        <QuietChatList :datas="datas" @click="chat_data(i)" />
      </div>
    </div>
    <!-- 필터로 걸러진 고독방이 없다면 -->
    <div v-else>
      <div class="text-center-container">
        <div class="error-title-container">
          <div class="oops">OOPS</div>
          <div class="sad-face">:(</div>
          <div class="resting-face">:|</div>
        </div>
        <div class="mt-52">
          <div class="error-content">
            찾으시려는 페이지의 주소를 잘못 입력 되었거나,
          </div>
          <div class="error-content">
            주소의 변경 혹은 삭제로인해 현재 사용하실 수 없습니다.
          </div>
        </div>
      </div>
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
    const check_search_modal = computed(
      () => store.state.searchModalStore.open_search_modal
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
      check_search_modal,
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
      value
        ? (document.body.style.overflow = "hidden")
        : document.body.style.removeProperty("overflow");
    },
    check_search_modal: function (value) {
      value
        ? (document.body.style.overflow = "hidden")
        : document.body.style.removeProperty("overflow");
    },
  },
};
</script>

<style scoped lang="postcss">
.bg-negative {
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0;
}
/* .test {
  min-height:84vh;
  @apply h-full
} */

/* OOPS */
.oops {
  @apply mt-16 text-8xl font-carter text-zz-s;
}
.sad-face {
  transform: rotate(90deg);
  right: 6rem;
  @apply relative mt-12 font-pop text-4xl dark:text-white;
}

.resting-face {
  transform: rotate(90deg);
  right: 1rem;
  bottom: 1.5rem;
  @apply relative font-pop text-4xl dark:text-white;
}
</style>
