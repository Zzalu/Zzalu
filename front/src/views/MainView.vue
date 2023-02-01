<template>
  <div class="not-scroll">
    <div class="body" v-if="check_search_modal" @click="close_modal"></div>
    <OnlyBigLogoTopNav class="z-30" />
    <AcademyList />
    <RecommendedJjalList />
    <PopularJjalList />
    <HotChattingRoomList />

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
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

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
    return {
      check_search_modal,
      close_search_modal,
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
  },
  methods: {
    close_modal() {
      if (this.check_search_modal == true) {
        this.close_search_modal();
      }
    },
  },
  watch: {
    // 외부 스크롤 막기
    check_search_modal : function (value) {
      if (value == true) {
        document.body.style.overflow = 'hidden';
      } else {
        document.body.style.removeProperty('overflow');
      }
    } 
  }
};
</script>

<style scoped lang="postcss">
.not-scroll {
  overflow-y: hidden !important;
}
.body {
  height: 4rem;
  @apply fixed inset-x-0 top-0 z-40;
}

.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}
/* 역대 명예의 전당 전체 */
/* .title-competition-card-container {
  @apply border w-36 h-48 rounded-2xl mr-5;
}
.title-competiton-img-container {
  @apply relative;
}
.date-icon {
  @apply w-14 h-12 text-zz-p;
}

.title-competiton-icon-text1 {
  margin-left: 0.9rem;
  font-size: 0.8rem;
  height: 16px;
  @apply font-bhs text-white;
}
.title-competiton-icon-text2 {
  margin-left: 2rem;
  font-size: 0.8rem;
  @apply font-bhs text-white;
}
.title-competiton-img {
  @apply h-28 w-36 rounded-t-xl;
}
.title-competition-content-profile {
  @apply flex items-center mt-2;
}
.title-competiton-content-img {
  @apply rounded-full w-4 mx-1;
}
.title-competiton-content-text {
  font-size: 0.2rem;
  @apply font-spoq line-clamp-1;
}
.title-competiton-button-contain {
  @apply border flex rounded-2xl bg-zz-p items-center px-1 ml-1 mt-2 mr-1;
}
.title-competiton-button-icon {
  font-size: 0.6rem;
  @apply mr-1 text-zz-s;
}
.title-competiton-button-text {
  font-size: 0.4rem;
  @apply text-white;
}
.title-competiton-content {
  word-break: keep-all;
  @apply mt-2 text-xs line-clamp-2 font-spoq mx-1;
} */

/* 추천 이미지 */

/* .recommend-img {
  background-image: url(../components/QuietChat/QuietChatList/assets/goodgood.gif);
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center;
}

.popular-img {
  background-image: url(../components/QuietChat/QuietChatList/assets/rmfoTrnsk.gif);
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center;
} */

/* 고독방 카드 */

.quiet-chat-card-contain {
  background-image: url("../components/QuietChat/QuietChatList/assets/Infinite_Challenge.jpg");
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
  @apply border border-white absolute bottom-0 inset-x-0 flex items-center justify-center bg-zz-p rounded-b-xl;
}

.quiet-chat-title {
  @apply text-white text-sm font-bhs;
}
</style>
