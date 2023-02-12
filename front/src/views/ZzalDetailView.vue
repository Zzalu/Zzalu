<template>
  <!-- 에디트 모드 -->
  <div v-if="edit_mode">
    <zzal-list-item :gif_id="jjal_detail_data.id"
    :jjal_detail_data="jjal_detail_data"
    ></zzal-list-item>
    <zzal-info :zzal_origin_content="zzal_origin_content"
    :jjal_detail_data="jjal_detail_data"
    ></zzal-info>
    <zzal-source-video></zzal-source-video>
    <div class="flex justify-center dark:text-white">
      <button
        class="
          text-center
          border-2
          w-9/12
          text-white
          bg-zz-s
          rounded-lg
          h-8
          mx-auto
          cursor-pointer
          font-spoq
          dark:border-zz-dark-div
        "
        @click="짤수정요청함수"
      >
        저장하기
      </button>
    </div>

    <DetailKorGoBackTopNavBar @notEditMode="notEditMode" />
  </div>
  <div v-else>
    <only-go-back-top-nav></only-go-back-top-nav>
  </div>
  <div>
    <div class="mt-6">
      <div v-if="edit_mode"></div>

      <!-- 뷰 모드 -->
      <div v-else>
        <!-- 헤더 -->
        <div v-if="jjal_detail_data">
          <div class="flex items-center">
            <font-awesome-icon icon="fa-solid fa-eye" class="view-count" />
            <div class="ml-2 dark:text-white">{{ visitedCount }}</div>
          </div>
          <div class="grid grid-cols-3 gap-4">
            <img :src="`${gif_path}`" alt="" class="col-span-2 justify-center" />
            <div class="grid grid-rows-2 gap-2">
              <div
                class="flex place-items-end"
                @click="this.open_list_modal(gif_id)"
              >
                <font-awesome-icon
                  icon="fa-solid fa-star"
                  class="my-1 text-zz-s"
                />
                <div class="mx-1 text-sm text-zz-s line-clamp-1 font-spoq">
                  스크랩하기
                </div>
              </div>
              <div class="flex">
                <font-awesome-icon
                  icon="fa-solid fa-download"
                  class="my-1 text-zz-s"
                />
                <div class="mx-1 text-zz-s line-clamp-1 font-spoq">
                  저장하기
                </div>
              </div>
            </div>
          </div>
          <!-- 바디 -->
          <button
            class="edit-button text-zz-edit flex float-right font-spoq"
            @click="EditMode"
          >
            [편집하기]
          </button>
          <div class="mt-6">
            <div class="last-edited">마지막으로 수정한 사람: {{ 누구 }}</div>
            <div class="last-edited">마지막으로 수정한 날짜: {{ 날짜 }}</div>
          </div>

          <div class="hashtag-contain">
            <div class="hashtag-div">
              <div v-for="(hashtag, i) in tags" :key="i" class="relative">
                <div class="hashtag-text">{{ hashtag }}</div>
              </div>
            </div>
          </div>

          <div class="mt-6 mb-2 font-bold font-spoq text-zz-p">
            이 짤의 유래는?
          </div>
          <div class="zzal-origin-edit">
            <div v-if="zzal_origin_content" class="font-spoq">
              {{ zzal_origin_content }}
            </div>
            <div v-else class="dark:text-white font-spoq">
              현재 등록된 짤 유래가 없습니다.
              <p>해당 게시글의 짤 유래를 아신다면 직접 등록해주세요 !</p>
            </div>
          </div>

          <!-- 푸터 -->
          <div class="edit-original-vid">
            <font-awesome-icon icon="fa-brands fa-youtube" class="yt-icon" />
            <input
              type="text"
              class="edit-original-link"
              placeholder="https://www.youtube.com/watch?v=sey8rFdvq6M"
            />
            <font-awesome-icon
              icon="fa-solid fa-square-plus"
              class="plus-icon"
            />
          </div>
        </div>
      </div>
    </div>
    <div class="pb-12"></div>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
// import KorGoBackTopNavBar from '../components/Common/NavBar/KorGoBackTopNavBar.vue'
import OnlyGoBackTopNav from "../components/Common/NavBar/OnlyGoBackTopNav.vue";
import DetailKorGoBackTopNavBar from "../components/Common/NavBar/DetailKorGoBackTopNavBar";
import MainBottomNav from "../components/Common/NavBar/MainBottomNav.vue";
import ZzalListItem from "../components/ZzalDetail/ZzalListItem.vue";
import ZzalInfo from "@/components/ZzalDetail/ZzalInfo.vue";
import ZzalSourceVideo from "../components/ZzalDetail/ZzalSourceVideo.vue";
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "ZzalDetailView",
  setup() {
    const store = useStore();

    const open_list_modal = (e) => {
      store.commit("boardListStore/SELECT_GIF", e);
      store.commit("searchModalStore/open_list_modal");
    };
    const jjal_detail_data = computed(
      () => store.state.zzalListStore.jjal_data
    );
    const get_detail_data = (gif_id) => {
      console.log('실행?',gif_id);
      store.dispatch("zzalListStore/getDetailData",gif_id);
    };
    return {
      open_list_modal,
      get_detail_data,
      jjal_detail_data,
    };
  },
  components: {
    ZzalListItem,
    ZzalInfo,
    ZzalSourceVideo,
    MainBottomNav,
    OnlyGoBackTopNav,
    DetailKorGoBackTopNavBar,
  },
  data() {
    return {
      edit_mode: false,
      zzal_origin_content: "",
      gif_id : this.$store.state.zzalListStore.jjal_data
    };
  },
  // created() {
  //   console.log('created', this.$router.params.id );
  //   this.get_detail_data(this.$router.params.id)
  // },
  computed: {
    tags() {
      return this.jjal_detail_data.tags.split(",");
    },
    visitedCount() {
      return this.jjal_detail_data.visitedCount;
    },
    gif_path() {
      return this.jjal_detail_data.gifPath
    }
    // gif_id() {
    //   localStorage.setItem("now_gif_id",this.gif_id)
    //   this.get_detail_data(this.$router.params.id)
    //   return this.$router.params.id
    // }
  },
  mounted() {
    this.get_detail_data(this.$route.params.zzal_id)
  },
  methods: {
    EditMode() {
      this.edit_mode = true;
    },
    notEditMode() {
      this.edit_mode = false;
    },
    짤수정요청함수() {
      // 여기서 입력된 데이터 가지고 api 요청하면됨
    },
  },
};
</script>

<style scoped lang="postcss">
.view-count {
  @apply my-3 mx-2 text-zz-negative;
}

.hashtag-contain {
  @apply flex flex-wrap mt-2;
}
.hashtag-div {
  @apply flex text-white flex-wrap;
}
.hashtag-text {
  @apply border rounded-lg bg-zz-p px-2 py-1 mr-2 mb-2 font-spoq dark:border-zz-dark-div;
}
.last-edited {
  @apply text-zz-negative text-xs;
}

.zzal-origin {
  @apply bg-zz-light-input;
}

.zzal-origin-edit {
  @apply border-2 border-zz-s;
}

.original-vid {
  @apply mt-6 flex justify-center;
}

.edit-original-vid {
  @apply mt-6 flex mb-20;
}

.edit-original-link {
  @apply flex-col border-2 border-zz-s rounded-sm w-4/5 px-1;
}

.yt-icon {
  @apply my-auto mr-3 text-zz-error;
}

.plus-icon {
  @apply my-auto ml-3 text-zz-s;
}
</style>
