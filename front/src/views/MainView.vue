<template>
  <div class="dark:bg-zz-bd">
    <div v-if="open_chat_info" class="bg-negative" @click="close_chat"></div>
    <OnlyBigLogoTopNav class="z-30" />
    <AcademyList />
    <RecommendedJjalList />
    <PopularJjalList />

    <HotChattingRoomList />

    <div v-if="open_chat_info">
      <ChatInfoModal :info_data="chat_data[open_chat_id]" class="z-50" />
    </div>
    <div class="h-5"></div>
    <MainBottomNav />
  </div>
</template>

<script>
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
      // axios 요청
      store.dispatch("quietChatStore/getQuietList");
      if(open_chat_id==true) {
        store.dispatch("zzalListStore/getPopularGIFList");
        store.dispatch("zzalListStore/getRecommendGIFList")
      }
    });
    return {
      open_chat_info,
      open_chat_id,
      close_chat_info,
    };
  },
  components: {
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
    close_chat() {
      this.close_chat_info();
    },
  },
  get watch() {
    return this._watch;
  },
  set watch(value) {
    this._watch=value;
  },
};
</script>

<style scoped lang="postcss">

.bg-negative {
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0 z-40;
}

.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}
</style>
