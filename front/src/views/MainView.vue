<template>
  <!-- <div :class="check_search_modal ? 'not-scroll' : null">-->
    <div class="not-scroll">
    <div class="body" v-if="check_search_modal" @click="close_modal"></div>
    <OnlyBigLogoTopNav class="z-30" />
    <div>
      <p class="focus-text">역대 제목학원 명예의 전당</p>
      <hr class="mb-5" />
      <!-- 포문돌릴곳 -->
      <div class="flex overflow-x-auto">
        <div v-for="a in 10" :key="a">
          <div class="title-competition-card-container">
            <div class="title-competiton-img-container">
              <!-- 아이콘 날짜 사진 -->
              <div class="absolute z-10">
                <p class="title-competiton-icon-text1">Jan</p>
                <p class="title-competiton-icon-text2">1</p>
              </div>
              <div class="absolute">
                <font-awesome-icon
                  icon="fa-solid fa-bookmark"
                  class="date-icon"
                />
              </div>
              <img
                src="../components/QuietChat/QuietChatList/assets/9_16img.jpg"
                class="title-competiton-img"
                alt=""
              />
            </div>
            <div class="flex">
              <div>
                <!-- 컨텐트 -->
                <div class="title-competition-content-profile">
                  <img
                    class="title-competiton-content-img"
                    src="../components/QuietChat/QuietChatList/assets/Newjeans.jpg"
                  />
                  <p class="title-competiton-content-text">
                    이름이최대열글자라니
                  </p>
                </div>
              </div>
              <!-- 좋아요 -->
              <div class="title-competiton-button-contain">
                <font-awesome-icon
                  icon="fa-solid fa-heart"
                  class="title-competiton-button-icon"
                />
                <p class="title-competiton-button-text">999</p>
              </div>
            </div>
            <!-- 내용 -->
            <p class="title-competiton-content">
              잠깐만요. 불공평한거 같은데요. 이 펜싱 대회 만약 더 길다면
            </p>
          </div>
        </div>
      </div>
    </div>
    <div>
      <p class="focus-text">user.id님을 위한 추천 짤</p>
      <hr class="mb-5" />
      <div class="flex overflow-x-auto">
        <div v-for="b in 30" :key="b">
          <div class="recommend-img"></div>
        </div>
      </div>
    </div>
    <div>
      <p class="focus-text">오늘의 인기 짤</p>
      <hr class="mb-5" />
      <div class="flex overflow-x-auto">
        <div v-for="b in 30" :key="b">
          <div class="popular-img"></div>
        </div>
      </div>
    </div>
    <div>
      <p class="focus-text">요새 핫한 고독방</p>
      <hr class="mb-5" />
      <div class="flex overflow-x-auto">
        <div v-for="b in 5" :key="b">
          <div class="quiet-chat-card-contain">
            <div class="quiet-chat-like-contain">
              <font-awesome-icon
                icon="fa-solid fa-heart"
                class="quiet-chat-like-icon"
              />
              <p class="quiet-chat-like-text">999</p>
            </div>
            <div class="quiet-chat-box">
              <div class="quiet-chat-title">무한도전 고독방</div>
            </div>
          </div>
        </div>
      </div>
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
  },
  methods: {
    close_modal() {
      if (this.check_search_modal == true) {
        this.close_search_modal();
      }
    },
  },
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
.title-competition-card-container {
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
}

/* 추천 이미지 */

.recommend-img {
  background-image: url(../components/QuietChat/QuietChatList/assets/goodgood.gif);
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center;
}

.popular-img {
  background-image: url(../components/QuietChat/QuietChatList/assets/rmfoTrnsk.gif);
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center;
}

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
