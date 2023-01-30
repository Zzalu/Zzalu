<template>
  <div>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <div class="flex flex-col items-center">
      <!-- 오늘의 제목학원 header -->
      <div class="w-full">
        <div class="relative w-full flex flex-col items-center">
          <div>
            <span class="text-xs font-medium">{{ date }}</span>
            <h1 class="text-xl font-bold">오늘의 제목학원</h1>
          </div>
          <div class="absolute right-0 top-0 flex flex-row items-center bg-zz-p p-1 rounded-md">
            <button class="text-xs text-white">역대 제목학원</button>
            <font-awesome-icon icon="fa-solid fa-chevron-right " class="text-xs text-white" />
          </div>
          <!-- 짤 -->
          <div class="h-48"><img src="../assets/logo.png" alt="짤" /></div>
        </div>

        <!-- TOP 5 -->
        <!-- 댓글 -->
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
        <comment-list></comment-list>
        <!-- 댓글 input -->
        <input type="text" class="w-full" />
      </div>
    </div>
    <main-bottom-nav />
  </div>
</template>

<script>
import OnlySmallLogoTopNav from '@/components/Common/NavBar/OnlySmallLogoTopNav.vue';
import { useStore } from 'vuex';
import CommentList from '../components/TitleCompetition/CommentList.vue';
import MainBottomNav from '../components/Common/NavBar/MainBottomNav.vue';

export default {
  components: { OnlySmallLogoTopNav, CommentList, MainBottomNav },
  name: 'TitleCompetitionView',
  setup() {
    const store = useStore();
    const date = store.state.titleCompetitionStore.date;
    const comment_count = store.state.titleCompetitionStore.comment_count;
    return {
      date,
      comment_count,
    };
  },
};
</script>

<style>
.sort-text {
  @apply text-xs text-zz-p mr-1;
}
</style>
