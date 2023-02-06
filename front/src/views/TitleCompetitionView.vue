<template>
  <div>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <div class="flex flex-col items-center relative">
      <div class="w-full">
        <!-- 오늘의 제목학원 header -->
        <header class="relative w-full flex flex-col items-center">
          <div>
            <span class="text-xs font-medium">{{ open_date }}</span>
            <h1 class="text-xl font-bold">오늘의 제목학원</h1>
          </div>
          <div class="absolute right-0 top-0 flex flex-row items-center bg-zz-p p-1 rounded-md">
            <button class="text-xs text-white">역대 제목학원</button>
            <font-awesome-icon icon="fa-solid fa-chevron-right " class="text-xs text-white" />
          </div>
          <!-- 짤 -->
          <!-- <div ref="scrollTest" class="h-48"><img src="../assets/logo.png" alt="짤" /></div> -->
          <div :class="{ 'big-image': !isScrolled.value, 'small-image': isScrolled.value }">
            <img class="title-image" :src="zzal_url" alt="짤" />
          </div>
          <!-- 댓글 스크롤 했을 때 짤fixed
          <div v-if="isFixed" class="zzal_fixed">
            <img src="../assets/logo.png" alt="짤" />
          </div> -->
        </header>

        <!-- TOP 5 -->
        <!-- 댓글 네브 -->
        <nav ref="scrollTest" class="flex justify-between">
          <div class="flex">
            <h2 class="text-xl text-zz-p">댓글</h2>
            <span class="text-base text-zz-p">{{ total_comment_cnt }}</span>
          </div>
          <div>
            <button class="sort-text">인기순</button>
            <button class="sort-text">최신순</button>
            <button class="sort-text">과거순</button>
          </div>
        </nav>
        <!-- 댓글 리스트 -->
        <comment-list class="mb-10"></comment-list>
      </div>
      <!-- 댓글 input -->
      <comment-input></comment-input>
    </div>
    <main-bottom-nav />
  </div>
</template>

<script>
import OnlySmallLogoTopNav from '@/components/Common/NavBar/OnlySmallLogoTopNav.vue';
import { useStore } from 'vuex';
import { onBeforeUnmount, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import CommentList from '@/components/TitleCompetition/CommentList.vue';
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';
import CommentInput from '@/components/TitleCompetition/CommentInput.vue';

export default {
  components: { OnlySmallLogoTopNav, CommentList, MainBottomNav, CommentInput },
  name: 'TitleCompetitionView',
  setup() {
    const store = useStore();
    const route = useRoute();
    const open_date = route.params.open_date; // 제목학원 날짜
    const scrollTest = ref(null);
    // 날짜를 통해서 제목학원 정보를 store에 저장한다

    store.dispatch('titleCompetitionStore/getTitleCompetition', open_date);

    const total_comment_cnt = store.state.titleCompetitionStore.total_comment_cnt; // 댓글 개수
    const zzal_url = store.state.titleCompetitionStore.zzal_url; // 짤
    console.log(zzal_url);

    store.dispatch('titleCompetitionStore/getCommentList', 3);
    onMounted(() => {
      // console.log(scrollTest);
      window.addEventListener('scroll', scroll);
    });
    onBeforeUnmount(() => {
      window.removeEventListener('scroll', scroll);
    });

    let isScrolled = ref(false);

    function scroll() {
      if (window.scrollY > scrollTest.value.offsetTop) {
        isScrolled.value = ref(true);
      } else {
        isScrolled.value = ref(false);
      }
      // console.log(window.scrollY);
      // console.log(isScrolled.value);
    }

    return {
      open_date,
      total_comment_cnt,
      zzal_url,
      isScrolled,
      scrollTest,
      scroll,
    };
  },
};
</script>

<style>
.sort-text {
  @apply text-xs text-zz-p mr-1;
}

.title-header {
  @apply relative w-full flex flex-col items-center;
}

.big-imange {
  @apply h-48;
}
.small-image {
  @apply h-20 fixed;
}

.title-image {
  @apply h-full w-full;
}
</style>
