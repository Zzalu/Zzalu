<template>
  <div>
    <only-small-logo-top-nav class="z-30"></only-small-logo-top-nav>
    <div class="flex flex-col items-center">
      <div class="w-full dark:text-white">
        <!-- 오늘의 제목학원 header -->
        <!-- <header class="relative w-full flex flex-col items-center">` -->
        <header class="title-header">
          <div>
            <span class="text-xs font-medium">{{ open_date }}</span>
            <h1 class="text-xl font-bold">오늘의 제목학원</h1>
          </div>
          <div class="whole-of-frame-btn">
            <button class="text-xs text-white" @click="GoToWholeOfFrame">역대 제목학원</button>
            <font-awesome-icon icon="fa-solid fa-chevron-right " class="text-xs text-white" />
          </div>
          <!-- 짤 -->

          <div ref="zzalComponent" class="w-2/5 h-2/5">
            <img :src="zzal_url" alt="짤" class="w-full h-full" />
            <div v-if="isScrolled" class="zzal-fixed">
              <img :src="zzal_url" alt="짤" />
            </div>
          </div>
        </header>

        <!-- TOP 5 -->
        <!-- 댓글 네브 -->
        <!-- 댓글 main -->

        <div class="comment-main" @scroll="handleCommentListScroll">
          <nav class="flex justify-between">
            <div class="flex">
              <h2 class="text-xl text-zz-p">댓글</h2>
              <span class="text-base text-zz-p">({{ total_comment_cnt }})</span>
            </div>
            <div>
              <button
                :class="[sort_type == 'POPULAR' ? 'sort-text-active' : 'sort-text']"
                @click="clickSortBtn('POPULAR')"
              >
                인기순
              </button>
              <button
                :class="[sort_type == 'LATEST' ? 'sort-text-active' : 'sort-text']"
                @click="clickSortBtn('LATEST')"
              >
                최신순
              </button>
              <button
                :class="[sort_type == 'CHRONOLOGICAL' ? 'sort-text-active' : 'sort-text']"
                @click="clickSortBtn('CHRONOLOGICAL')"
              >
                과거순
              </button>
              <!-- <button class="sort-text" @click="clickSortBtn('POPULAR')">인기순</button>
            <button class="sort-text" @click="clickSortBtn('LATEST')">최신순</button>
            <button class="sort-text" @click="clickSortBtn('CHRONOLOGICAL')">과거순</button> -->
            </div>
          </nav>
          <!-- 댓글 리스트 -->
          <comment-list ref="commentListComponent" class="comment-list"></comment-list>
        </div>
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
// import { onBeforeUnmount, onMounted, ref } from 'vue';
import { computed, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import CommentList from '@/components/TitleCompetition/CommentList.vue';
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';
import CommentInput from '@/components/TitleCompetition/CommentInput.vue';

import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';

export default {
  components: { OnlySmallLogoTopNav, CommentList, MainBottomNav, CommentInput },
  name: 'TitleCompetitionView',
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const open_date = route.params.open_date; // 제목학원 날짜
    const isScrolled = ref(null);
    const zzalComponent = ref(null);
    // 날짜를 통해서 제목학원 정보를 store에 저장한다
    let total_comment_cnt = computed(() => store.state.titleCompetitionStore.total_comment_cnt); // 댓글 개수
    let zzal_url = computed(() => store.state.titleCompetitionStore.zzal_url);
    const GoToWholeOfFrame = () => {
      router.push(`/whole-of-frame`);
    };
    let sort_type = computed(() => store.state.titleCompetitionStore.sort_type);

    document.documentElement.scrollTop = 0;
    store.dispatch('titleCompetitionStore/init', { open_date: open_date, size: 10 });

    const clickSortBtn = (sort_type) => {
      store.dispatch('titleCompetitionStore/modifySortType', sort_type);
    };

    const loadMoreComments = () => {
      store.dispatch('titleCompetitionStore/getComments', 4);
    };
    const handleCommentListScroll = (e) => {
      const { scrollHeight, scrollTop, clientHeight } = e.target;
      if (scrollTop + clientHeight > scrollHeight - 1) {
        setTimeout(() => {
          loadMoreComments();
        }, 1000);
      }
    };

    //! 소켓 관련
    let options = { debug: false, protocols: Stomp.VERSIONS.supportedProtocols() };
    let sock = new SockJS('http://i8c109.p.ssafy.io:8080/ws-stomp');
    let ws = Stomp.over(sock, options);
    function connect() {
      console.log('connect 시작');
      let localWs = ws;
      let localSock = sock;
      localWs.connect(
        {},
        function () {
          localWs.subscribe('/sub/title-hakwon/comments/', function (message) {
            console.log(message);
          });
          localWs.subscribe('/sub/title-hakwon/comments/like', function (message) {
            console.log(message);
          });
        },
        function () {
          setTimeout(function () {
            console.log('connection reconnect');
            localSock = new SockJS('/ws-stomp');
            localWs = Stomp.over(localSock);
          }, 10 * 1000);
        },
      );
    }

    connect();

    return {
      sock,
      ws,
      connect,
      open_date,
      total_comment_cnt,
      zzal_url,
      isScrolled,
      scroll,
      zzalComponent,
      clickSortBtn,
      GoToWholeOfFrame,
      sort_type,
      handleCommentListScroll,
    };
  },
};
</script>

<style>
.title-header {
  @apply fixed w-full flex flex-col items-center justify-center;
}

.whole-of-frame-btn {
  @apply absolute right-10 top-0 flex flex-row items-center bg-zz-p p-1 rounded-md;
}
.sort-text {
  @apply text-xs text-zz-p mr-1 font-thin;
}
.sort-text-active {
  @apply text-xs text-zz-s mr-1 font-black;
}

.comment-main {
  @apply fixed bottom-0 w-11/12 mb-14 overflow-y-scroll h-1/2;
}
.comment-list {
  @apply w-full mb-10 overflow-y-scroll h-auto;
}

.comment-list ::-webkit-scrollbar {
  display: none;
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

.zzal-fixed {
  @apply fixed;
}
</style>
