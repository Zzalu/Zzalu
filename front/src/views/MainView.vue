<template>
  <div class="not-scroll">
    <div class="body" v-if="check_search_modal" @click="close_modal"></div>
    <div v-if="open_chat_info" class="bg-negative" @click="close_chat"></div>
    <OnlyBigLogoTopNav class="z-30" />
    <AcademyList />
    <RecommendedJjalList />
    <PopularJjalList />

    <HotChattingRoomList />

    <div v-if="open_chat_info">
      <ChatInfoModal :info_data="chat_data[open_chat_id]" class="z-50" />
    </div>
    <SearchView />
    <div class="h-5"></div>
    <MainBottomNav />
  </div>
</template>

<script>
import SearchView from "./SearchView";
import MainBottomNav from "../components/Common/NavBar/MainBottomNav";
import OnlyBigLogoTopNav from "../components/Common/NavBar/OnlyBigLogoTopNav";
import AcademyList from "../components/Main/AcademyList";
import RecommendedJjalList from "../components/Main/RecommendedJjalList";
import PopularJjalList from "../components/Main/PopularJjalList";
import HotChattingRoomList from "../components/Main/HotChattingRoomList";
import ChatInfoModal from "../components/QuietChat/QuietChatList/ChatInfoModal";
import HotCahttingRoomData from "../views/QuietChat/QuietChatListData.js";
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
import { onBeforeMount } from "@vue/runtime-core";

export default {
  name: "MainView",
  setup() {
    const store = useStore();

    const check_search_modal = computed(
      () => store.state.searchModalStore.open_search_modal
    );
    const close_search_modal = () => {
      store.commit("searchModalStore/open_search_modal");
    };
    const open_chat_info = computed(
      () => store.state.quietChatStore.open_chat_info
    );
    const open_chat_id = computed(
      () => store.state.quietChatStore.open_chat_id
    );
    const close_chat_info = () => {
      store.commit("quietChatStore/close_chat_info");
    };
    onBeforeMount(() => {
      store.dispatch("quietChatStore/getQuietList"),
        store.dispatch("quietChatStore/getGIFList");
    });
    return {
      check_search_modal,
      close_search_modal,
      open_chat_info,
      open_chat_id,
      close_chat_info,
    };
  },
  components: {
    SearchView,
    MainBottomNav,
    OnlyBigLogoTopNav,
    AcademyList,
    RecommendedJjalList,
    PopularJjalList,
    HotChattingRoomList,
    ChatInfoModal,
  },
  data() {
    return {
      chat_data: HotCahttingRoomData,
    };
  },
  unmounted() {
    this.close_chat_info();
  },
  methods: {
    close_modal() {
      if (this.check_search_modal == true) {
        this.close_search_modal();
      }
    },
    close_chat() {
      this.close_chat_info();
    },
  },
  watch: {
    // 외부 스크롤 막기
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
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0 z-40;
}
.body {
  height: 4rem;
  @apply fixed inset-x-0 top-0 z-40;
}

.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}
</style>
