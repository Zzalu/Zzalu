<template>
  <div>
    <div class="body" v-if="check_search_modal" @click="close_modal"></div>
    <OnlyBigLogoTopNav />
    <div>
      <p>역대 제목학원 명예의 전당</p>
      <hr class="mb-5" />
      <!-- 포문돌릴곳 -->
      <div class="flex overflow-x-auto">
        <div v-for="a in 10" :key="a">
          <div class="title-competition-card-container">
            <div class="title-competiton-img-container">
              <div>
                <!-- 아이콘 날짜 사진 -->
                <font-awesome-icon
                  icon="fa-solid fa-bookmark"
                  class="date-icon"
                />
                <p class="title-competiton-icon-text1">2월</p>
                <p class="title-competiton-icon-text2">1일</p>
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
                  <p class="title-competiton-content-text">Newjeans</p>
                </div>
              </div>
              <!-- 좋아요 -->
              <div class="title-competiton-button-contain">
                <font-awesome-icon
                  icon="fa-solid fa-heart"
                  class="title-competiton-button-icon"
                />
                <p class="title-competiton-button-text">1010</p>
              </div>
            </div>
            <!-- 내용 -->
            <p class="title-competiton-content">
              잠깐만요. 불공평한거 같은데요. 이 펜싱 대회
            </p>
          </div>
        </div>
      </div>
    </div>
    <div>
      <p>user.id님을 위한 추천 짤</p>
      <hr class="mb-5"/>
      <img src="../components/QuietChat/QuietChatList/assets/Dragon.jpg" alt="">
    </div>
    <div>
      <p>오늘의 인기 짤</p>
      <hr />
      <div>이미지</div>
    </div>
    <div>
      <p>요새 핫한 고독방</p>
      <hr />
      <div>카드</div>
    </div>
    <SearchView />
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
.body {
  height: 4rem;
  @apply fixed inset-x-0 top-0 z-10;
}

/* 역대 명예의 전당 전체 */
.title-competition-card-container {
  @apply border w-32 h-48 rounded-2xl mr-5;
}

.date-icon {
  @apply w-14 h-12 text-zz-p;
}

.title-competiton-icon-text1 {
  @apply text-xs ml-5 fixed text-white flex;
}
.title-competiton-icon-text2 {
  @apply text-xs ml-5 mt-4 fixed text-white flex;
}
.title-competiton-img {
  @apply h-28 w-32 rounded-t-xl;
}
.title-competition-content-profile {
  @apply flex items-center;
}
.title-competiton-content-img {
  @apply rounded-full w-4 mx-1;
}
.title-competiton-content-text {
  font-size: 0.2rem;
  /* @apply text-xs; */
}
.title-competiton-button-contain {
  @apply border flex rounded-2xl bg-zz-p items-center px-1 text-xs ml-1;
}
.title-competiton-button-icon {
  @apply mr-1 text-zz-s;
}
.title-competiton-button-text {
  @apply text-white;
}
.title-competiton-content {
  @apply mt-2 text-xs line-clamp-2;
}
</style>
