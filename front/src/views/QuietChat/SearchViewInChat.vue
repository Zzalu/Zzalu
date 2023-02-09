<template>
  <div>
    <div>
      <transition name="fade">
        <div v-if="open_search_modal">
          <!-- 즐겨찾기 + 30 -->
          <div v-if="view_list_full == true">
            <div class="dark:border-zz-dark-div">
              <div class="modal">
                <div
                  class="modal-items"
                  ref="notification-list"
                  @scroll="handleNotificationListScroll"
                >
                  <div
                    v-if="load_state"
                    id="loading"
                    class="fixed top-1/2"
                  ></div>
                  <SearchBar />

                  <!-- 내보드 -->
                  <div class="my-board-contain" @click="view_board">
                    <div class="text-white absolute z-20 font-spoq">
                      내 보드
                    </div>
                    <div class="my-board">
                      <div
                        class="my-board-thumb"
                        :style="`background-image:url(${user_store_list.boards[0].thumbnailPath})`"
                      ></div>
                    </div>
                  </div>
                  <p class="fixed text-white"></p>

                  <!-- 기본적으로 보이는 짤들 내 보드 들어갔을 시 안보임 -->

                  <div v-for="(zzal_info, i) in random_gif_data" :key="i">
                    <JjalListItemInChat
                      :zzal_info="zzal_info"
                      :i="i"
                      @select_id="select_id"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 보드 목록 -->
          <div v-if="view_list_board == true">
            <div class="dark:border-zz-dark-div">
              <div class="modal">
                <div
                  class="modal-items"
                  ref="notification-list"
                  @scroll="handleNotificationListScroll"
                >
                  <div
                    v-if="load_state"
                    id="loading"
                    class="fixed top-1/2"
                  ></div>

                  <!-- 보드 내브바 -->
                  <div
                    class="
                      h-nav-height
                      fixed
                      inset-x-0
                      top-24
                      flex
                      items-center
                      justify-center
                    "
                  >
                    <font-awesome-icon
                      icon="fa-solid fa-chevron-left"
                      class="
                        text-2xl
                        absolute
                        left-8
                        text-black
                        dark:text-white
                      "
                      @click="view_full"
                    />
                    <div class="text-black dark:text-white font-spoq text-2xl">
                      내 보드
                    </div>
                  </div>

                  <!-- 내보드 -->

                  <div
                    v-for="(board_list, i) in user_store_list.boards"
                    :key="i"
                  >
                    <div
                      class="my-board-contain"
                      @click="view_detail(board_list.id)"
                    >
                      <div class="text-white absolute z-20 font-spoq">
                        {{ board_list.boardName }}
                      </div>
                      <div class="my-board">
                        <div
                          class="my-board-thumb"
                          :style="`background-image:url(${board_list.thumbnailPath})`"
                        ></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 보드 디테일 -->
          <div v-if="view_list_detail == true">
            <div class="dark:border-zz-dark-div">
              <div class="modal">
                <div
                  class="modal-items"
                  ref="notification-list"
                  @scroll="handleNotificationListScroll"
                >
                  <div
                    v-if="load_state"
                    id="loading"
                    class="fixed top-1/2"
                  ></div>

                  <div
                    v-for="(board_list, i) in user_detail_list.gifs"
                    :key="i"
                  >
                    <div class="text-white absolute z-20 font-spoq">
                      {{ board_list.boardName }}
                    </div>
                    <div class="my-board">
                      <div
                        class="my-board-thumb"
                        :style="`background-image:url(${board_list.thumbnailPath})`"
                      ></div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </transition>
      <!-- <transition name="slide-fade"> -->
    </div>
    <!-- </transition> -->
  </div>
</template>

<script>
import SearchBar from "../../components/Search/SearchBar";
import JjalListItemInChat from "../../components/Search/Item/JjalListItemInChat";
import { useStore } from "vuex";
import { computed, onBeforeMount } from "@vue/runtime-core";
export default {
  name: "SearchViewInChat",
  setup() {
    const store = useStore();
    const user_id = window.localStorage.getItem("id");

    const open_search_modal = computed(
      () => store.state.searchModalStore.open_search_modal
    );
    const open_list_modal = computed(
      () => store.state.searchModalStore.open_list_modal
    );
    const random_gif_data = computed(
      () => store.state.zzalListStore.random_gif_data
    );
    const user_store_list = computed(
      () => store.state.boardListStore.user_board_list
    );
    const user_detail_list = computed(
      () => store.state.boardListStore.board_detail_list
    );

    const send_select_jjal_num = (e) => {
      store.commit("searchModalStore/send_select_jjal_num", e);
    };

    onBeforeMount(() => {
      store.dispatch("zzalListStore/getFirstRandomGIFList");
      store.dispatch("boardListStore/getUserBoardList", user_id);
    });

    function ViewBoardDetail(board_id) {
      console.log("실행됨?");
      store.dispatch("boardListStore/getBoardData", board_id);
    }

    function MoreRandomGIF(gif_data) {
      store.dispatch("zzalListStore/getMoreRandomGIFLIST", [...gif_data]);
    }

    return {
      open_search_modal,
      open_list_modal,
      random_gif_data,
      user_store_list,
      user_detail_list,
      send_select_jjal_num,
      MoreRandomGIF,
      ViewBoardDetail,
    };
  },
  components: {
    SearchBar,
    JjalListItemInChat,
  },
  data() {
    return {
      gif_data: [],
      load_state: false,
      view_list_full: true,
      view_list_board: false,
      view_list_detail: false,
    };
  },
  methods: {
    select_id(a) {
      this.send_select_jjal_num(a);
    },
    handleNotificationListScroll(e) {
      const { scrollHeight, scrollTop, clientHeight } = e.target;
      const isAtTheBottom = scrollHeight === scrollTop + clientHeight;
      // 일정 한도 밑으로 내려오면 함수 실행
      if (isAtTheBottom) {
        this.load_state = true;
        setTimeout(() => {
          this.MoreRandomGIF(this.gif_data);
          this.load_state = false;
        }, 1000);
      }
    },
    view_board() {
      this.view_list_full = false;
      this.view_list_board = true;
      this.view_list_detail = false;
    },
    view_full() {
      this.view_list_full = true;
      this.view_list_board = false;
      this.view_list_detail = false;
    },
    view_detail(id) {
      this.view_list_full = false;
      this.view_list_board = false;
      this.view_list_detail = true;
      this.ViewBoardDetail(id);
    },
  },
  watch: {
    random_gif_data(nv) {
      let gif_id = [];
      for (let i = 0; i < nv.length; i++) {
        gif_id.push(parseInt(nv[i].id));
      }
      this.gif_data = gif_id;
    },
  },
};
</script>

<style scoped lang="postcss">
/* 로딩 애니메이션 */
@import url(https://fonts.googleapis.com/css?family=Roboto:100);

.my-board-contain {
  @apply w-32 h-24 m-2 rounded-2xl flex items-center justify-center;
}
.my-board {
  filter: opacity(0.1) drop-shadow(0 0 0 rgb(221, 218, 218));
  @apply h-full w-full rounded-2xl bg-cover;
}
.my-board-thumb {
  @apply w-32 h-24 rounded-2xl flex items-center justify-center bg-cover bg-center;
}
body {
  margin-top: 100px;
  background-color: #137b85;
  color: #fff;
  text-align: center;
}

h1 {
  font: 2em "Roboto", sans-serif;
  margin-bottom: 40px;
}

#loading {
  display: inline-block;
  width: 50px;
  height: 50px;
  border: 3px solid rgba(255, 255, 255, 0.3);
  border-radius: 50%;
  border-top-color: #fff;
  animation: spin 1s ease-in-out infinite;
  -webkit-animation: spin 1s ease-in-out infinite;
}

@keyframes spin {
  to {
    -webkit-transform: rotate(360deg);
  }
}
@-webkit-keyframes spin {
  to {
    -webkit-transform: rotate(360deg);
  }
}
/* 모달창 애니메이션 */
.fade-enter-active {
  transform: translateY(90vh);
  position: fixed;
  opacity: 0;
  transition: all 0.2s ease-in;
  @apply inset-x-0 top-0;
}
.fade-leave-active {
  transform: translateY(90vh);
  position: fixed;
  transition: all 0.2s ease-out;
  @apply inset-0;
}
.fade-leave-to {
  opacity: 0;
}
.fade-enter-to {
  transform: translateY(0px);
  position: fixed;
  opacity: 1;
}
/* 보관함 모달창 외부 클릭범위 */
.list-view-bg {
  opacity: 0.5;
  @apply fixed inset-0 z-30;
}
/* 짤 리스트 */
.modal-items {
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full pb-24;
}

.modal-items::-webkit-scrollbar {
  display: none;
}
</style>
