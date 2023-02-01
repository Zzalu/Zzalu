<template>
  <div>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <div class="flex flex-col items-center relative">
      <div class="w-full">
        <!-- 오늘의 제목학원 header -->
        <header class="relative w-full flex flex-col items-center">
          <div>
            <span class="text-xs font-medium">{{ date }}</span>
            <h1 class="text-xl font-bold">오늘의 제목학원</h1>
          </div>
          <div class="absolute right-0 top-0 flex flex-row items-center bg-zz-p p-1 rounded-md">
            <button class="text-xs text-white">역대 제목학원</button>
            <font-awesome-icon icon="fa-solid fa-chevron-right " class="text-xs text-white" />
          </div>
          <!-- 짤 -->
          <div ref="scrollTest" class="h-48"><img src="../assets/logo.png" alt="짤" /></div>
          <!-- 댓글 스크롤 했을 때 짤fixed -->
          <div v-if="isFixed" class="zzal_fixed">
            <img src="../assets/logo.png" alt="짤" />
          </div>
        </header>

        <!-- TOP 5 -->
        <!-- 댓글 네브 -->
        <nav class="flex justify-between">
          <div class="flex">
            <h2 class="text-xl text-zz-p">댓글</h2>
            <span class="text-base text-zz-p">({{ comment_count }})</span>
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
      <comment-input></comment-input>
    </div>
    <!-- 댓글 input -->
    <main-bottom-nav />
  </div>
</template>

<script>
import OnlySmallLogoTopNav from '@/components/Common/NavBar/OnlySmallLogoTopNav.vue';
import { useStore } from 'vuex';
import { onBeforeUnmount, onMounted, ref } from 'vue';
import CommentList from '../components/TitleCompetition/CommentList.vue';
import MainBottomNav from '../components/Common/NavBar/MainBottomNav.vue';
import CommentInput from '@/components/TitleCompetition/CommentInput.vue';

export default {
  components: { OnlySmallLogoTopNav, CommentList, MainBottomNav, CommentInput },
  name: 'TitleCompetitionView',
  setup() {
    const store = useStore();
    const date = store.state.titleCompetitionStore.date; // 제목학원 날짜
    const comment_count = store.state.titleCompetitionStore.comment_count; // 댓글 개수
    store.dispatch('titleCompetitionStore/getCommentList', {
      lastCommentId: Number.MAX_SAFE_INTEGER,
      titleHakwonId: 1,
      size: 5,
    });
    const scrollTest = ref(null);

    onMounted(() => {
      console.log(scrollTest);
      window.addEventListener('scroll', scroll);
    });
    onBeforeUnmount(() => {
      window.removeEventListener('scroll', scroll);
    });

    let isFixed = ref(false);

    function scroll() {
      // console.log('hi: ' + scrollTest.value.offsetTop);
      if (window.scrollY > scrollTest.value.offsetTop) {
        isFixed = true;
      } else {
        isFixed = false;
      }
      console.log(window.scrollY);
      console.log(isFixed);
    }

    return {
      date,
      comment_count,
      isFixed,
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

.title_header {
  @apply relative w-full flex flex-col items-center;
}

.zzal_fixed {
  @apply fixed top-0 left-auto z-10 h-20;
}
</style>
