<template>
  <div>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <div class="flex flex-col items-center relative">
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

          <div v-on="zzal_url" ref="zzalComponent" class="h-48">
            <img :src="zzal_url" alt="짤" />
            <div v-if="isScrolled" class="zzal_fixed">
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
            <span class="text-base text-zz-p">{{ total_comment_cnt }}</span>
          </div>
          <div>
            <button class="sort-text">인기순</button>
            <button class="sort-text">최신순</button>
            <button class="sort-text">과거순</button>
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
import { computed, ref } from 'vue';
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
    let total_comment_cnt = null; // 댓글 개수
    // let zzal_url = computed(() => store.state.titleCompetitionStore.zzal_url);
    let zzal_url = computed(() => store.state.titleCompetitionStore.zzal_url);

    store.dispatch('titleCompetitionStore/init', { open_date: open_date, size: 4 });
    // store.dispatch('titleCompetitionStore/getNewestComments', 4);
    function scroll(e) {
      console.log('scroll 이벤트');
      const { scrollHeight, scrollTop, clientHeight } = e.target;
      const isAtTheBottom = scrollHeight === scrollTop + clientHeight;
      if (isAtTheBottom) {
        setTimeout(() => {
          loadMoreComments();
        }, 1000);
      }
      // if (window.scrollY > zzalComponent.value.offsetTop) {
      //   isScrolled.value = ref(true);
      // } else {
      //   isScrolled.value = ref(false);
      // }
    }
    /*     onMounted(() => {
      console.log(zzal_url);
      // loadMoreComments();
    }); */

    const loadMoreComments = () => {
      store.dispatch('titleCompetitionStore/getNewestComments', 4);
    };

    //! 소켓 관련
    let options = { debug: false, protocols: Stomp.VERSIONS.supportedProtocols() };
    let sock = new SockJS('http://localhost:8080/ws-stomp');
    let ws = Stomp.over(sock, options);
    /*  function reciveMessage(recv) {
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == 'ENTER' ? '[알림]' : recv.sender,
        message: recv.message,
      });
    } */
    function connect() {
      console.log('connect 시작');
      let localWs = ws;
      let localSock = sock;
      // let localReciveMessage = reciveMessage;
      localWs.connect(
        {},
        function () {
          localWs.subscribe('/sub/title-hakwon/comments/', function (message) {
            console.log(message);
            // let recv = JSON.parse(message.body);
            // localReciveMessage(recv);
          });
          localWs.subscribe('/sub/title-hakwon/comments/like', function (message) {
            console.log(message);
            // let recv = JSON.parse(message.body);
            // localReciveMessage(recv);
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
      // reciveMessage,
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
