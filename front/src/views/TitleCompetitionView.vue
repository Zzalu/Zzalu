<template>
  <div>
    <only-small-logo-top-nav class="z-30"></only-small-logo-top-nav>
    <div class="flex flex-col items-center">
      <div class="w-full dark:text-white">
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

          <div ref="zzalComponent" class="h-48">
            <img :src="zzal_url" alt="짤" class="w-full h-full" />
            <div v-if="isScrolled" class="zzal-fixed">
              <img :src="zzal_url" alt="짤" />
            </div>
          </div>
          <!-- 댓글 스크롤 했을 때 짤fixed -->

          <!-- <div :class="{ 'big-image': !isScrolled.value, 'small-image': isScrolled.value }">
            <img class="title-image" :src="zzal_url" alt="짤" />
          </div> -->
        </header>

        <!-- TOP 5 -->
        <!-- 댓글 네브 -->
        <nav class="flex justify-between">
          <div class="flex">
            <h2 class="text-xl text-zz-p">댓글</h2>
            <span class="text-base text-zz-p">({{ total_comment_cnt }})</span>
          </div>
          <div>
            <button class="sort-text" @click="clickSortBtn('POPULAR')">인기순</button>
            <button class="sort-text" @click="clickSortBtn('LATEST')">최신순</button>
            <button class="sort-text" @click="clickSortBtn('CHRONOLOGICAL')">과거순</button>
          </div>
        </nav>
        <!-- 댓글 리스트 -->
        <comment-list ref="commentListComponent" class="mb-10"></comment-list>
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
import { computed, onMounted, onBeforeUnmount, ref } from 'vue';
import { useRoute } from 'vue-router';
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
    const open_date = route.params.open_date; // 제목학원 날짜
    const isScrolled = ref(null);
    const zzalComponent = ref(null);
    // 날짜를 통해서 제목학원 정보를 store에 저장한다
    let total_comment_cnt = computed(() => store.state.titleCompetitionStore.total_comment_cnt); // 댓글 개수
    let zzal_url = computed(() => store.state.titleCompetitionStore.zzal_url);

    store.dispatch('titleCompetitionStore/init', { open_date: open_date, size: 4 });
    // store.dispatch('titleCompetitionStore/getComments', 4);

    const clickSortBtn = (sort_type) => {
      store.dispatch('titleCompetitionStore/modifySortType', sort_type);
    };

    function infinityScroll() {
      let scrollLocation = document.documentElement.scrollTop; // 현재 스크롤바 위치
      let windowHeight = window.innerHeight; // 스크린 창
      let fullHeight = document.body.scrollHeight; // margin 값은 포함 x
      if (store.state.titleCompetitionStore.comments.length >= total_comment_cnt.value) {
        return;
      } else {
        if (scrollLocation + windowHeight >= fullHeight) {
          setTimeout(() => {
            document.documentElement.scrollTop = scrollLocation - 100;
            loadMoreComments();
          }, 2000);
        }
      }
    }

    onMounted(() => {
      window.addEventListener('scroll', infinityScroll);
    });

    onBeforeUnmount(() => {
      window.removeEventListener('scroll', infinityScroll);
    });

    const loadMoreComments = () => {
      setTimeout(() => {
        store.dispatch('titleCompetitionStore/getComments', 4);
      }, 2000);
      console.log('불러옵니다');
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

.zzal-fixed {
  @apply fixed;
}
</style>
