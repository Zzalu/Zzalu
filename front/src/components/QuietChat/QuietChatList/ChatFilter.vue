<template>
  <div>
    <div class="menu-one">
      <button v-if="filter1 == 0" class="filter-one-select">
        <p class="filter-text">전체 고독방</p>
      </button>
      <button v-else class="filter-one-not-select" @click="AllViewRoom">
        <p class="filter-text">전체 고독방</p>
      </button>
      <button v-if="filter1 == 1" class="filter-one-select">
        <p class="filter-text">내가 개설한</p>
      </button>
      <button v-else class="filter-one-not-select" @click="CreatedByMe">
        <p class="filter-text">내가 개설한</p>
      </button>
      <button v-if="filter1 == 2" class="filter-one-select">
        <p class="filter-text">즐겨찾기</p>
      </button>
      <button v-else class="filter-one-not-select" @click="ILoveItRoom">
        <p class="filter-text">즐겨찾기</p>
      </button>
    </div>

    <div class="h-1 w-full bg-zz-light-input relative">
      <div v-if="ov == null" class="move1-0"></div>
      <div v-if="filter1 == 0">
        <div v-if="ov == 1" class="move1-0"></div>
        <div v-if="ov == 2" class="move2-0"></div>
      </div>
      <div v-if="filter1 == 1">
        <div v-if="ov == 0" class="move0-1"></div>
        <div v-if="ov == 2" class="move2-1"></div>
      </div>
      <div v-if="filter1 == 2" class="test testing2">
        <div v-if="ov == 0" class="move0-2"></div>
        <div v-if="ov == 1" class="move1-2"></div>
      </div>
    </div>

    <div class="h-1 w-full bg-zz-light-input relative">
      <div v-if="filter2 == 0" class="f1-0"></div>
      <div v-if="filter2 == 1" class="f0-1"></div>
    </div>

    <div class="menu-two">
      <button v-if="filter2 == 0" class="filter-two-select">
        <p class="filter-text">최신 대화순</p>
      </button>
      <button v-else class="filter-two-not-select" @click="RecentSort">
        <p class="filter-text">최신 대화순</p>
      </button>
      <button v-if="filter2 == 1" class="filter-two-select">
        <p class="filter-text">좋아요 순</p>
      </button>
      <button v-else class="filter-two-not-select" @click="LikeSort">
        <p class="filter-text">좋아요 순</p>
      </button>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
export default {
  name: "ChatFilter",
  setup() {
    const store = useStore();
    const user_id = window.localStorage.getItem("id");

    const get_all_rooms = () => {
      store.dispatch("quietChatStore/getQuietList");
    };
    const only_search_room = (nv) => {
      store.dispatch("quietChatStore/onlySearchRoom", nv);
    };
    const nosearch_created_recent = () => {
      store.dispatch("quietChatStore/noSearchCreatedRecent", user_id);
    }

    return {
      only_search_room,
      get_all_rooms,
      nosearch_created_recent
    };
  },
  data() {
    return {
      filter1: 0,
      filter2: 0,
      ov: null,
      input_data : null,
    };
  },
  props: {
    search_data: String,
  },
  watch: {
    search_data(nv) {
      if (nv == "") {
        this.get_all_rooms();
        this.filter1 = 0;
        this.filter2 = 0;
      } else {
        (this.filter1 = 0), (this.filter2 = 0), this.only_search_room(nv);
        console.log("검색으로만 필터링", nv);
      }
      this.input_data = nv
    },
    filter1(newvalue, oldvalue) {
      if (oldvalue == 0) {
        this.ov = 0;
      } else if (oldvalue == 1) {
        this.ov = 1;
      } else if (oldvalue == 2) {
        this.ov = 2;
      }
    },
  },
  methods: {
    AllViewRoom() {
      this.filter1 = 0;
      if (this.filter2 == 0) {
        console.log("전체 고독 + 최신 대화");
      } else if (this.filter2 == 1) {
        console.log("전체 고독 + 좋아요순");
      }
    },
    CreatedByMe() {
      this.filter1 = 1;
      if (this.filter2 == 0) {
        if (this.input_data == null) {
          console.log("내가 개설 + 최신 대화");
        } else {
          console.log("검색필터 + 내가 개설 + 최신 대화");
        }
      } else if (this.filter2 == 1) {
        console.log("내가 개설 + 좋아요순");
      }
    },
    ILoveItRoom() {
      this.filter1 = 2;
      if (this.filter2 == 0) {
        console.log("즐겨찾기 + 최신 대화순");
      } else if (this.filter2 == 1) {
        console.log("즐겨찾기 + 좋아요 순");
      }
    },
    RecentSort() {
      this.filter2 = 0;
      if (this.filter1 == 0) {
        this.AllViewRoom();
      } else if (this.filter1 == 1) {
        this.CreatedByMe();
      } else if (this.filter1 == 2) {
        this.ILoveItRoom();
      }
    },
    LikeSort() {
      this.filter2 = 1;
      if (this.filter1 == 0) {
        this.AllViewRoom();
      } else if (this.filter1 == 1) {
        this.CreatedByMe();
      } else if (this.filter1 == 2) {
        this.ILoveItRoom();
      }
    },
  },
};
</script>

<style scoped lang="postcss" >
@keyframes moving1-0 {
  0% {
    margin-left: 33.33%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
  100% {
    margin-left: 0%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
}
@keyframes moving2-0 {
  0% {
    margin-left: 66.66%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
  100% {
    margin-left: 0%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
}

.move1-0 {
  animation-name: moving1-0;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}
.move2-0 {
  animation-name: moving2-0;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}

@keyframes moving0-1 {
  0% {
    margin-left: 0%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
  100% {
    margin-left: 33.33%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
}
@keyframes moving2-1 {
  0% {
    margin-left: 66.66%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
  100% {
    margin-left: 33.33%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
}

.move0-1 {
  animation-name: moving0-1;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}
.move2-1 {
  animation-name: moving2-1;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}

@keyframes moving0-2 {
  0% {
    margin-left: 0%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
  100% {
    margin-left: 66.66%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
}
@keyframes moving1-2 {
  0% {
    margin-left: 33.33%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
  100% {
    margin-left: 66.66%;
    @apply absolute w-1/3 bg-zz-p h-1;
  }
}

.move0-2 {
  animation-name: moving0-2;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}
.move1-2 {
  animation-name: moving1-2;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}
/* .test2 {
  margin-left: 66.66%;
} */

/* 필터2 */
@keyframes filter1-0 {
  0% {
    margin-left: 50%;
    @apply absolute w-1/2 bg-zz-p h-1;
  }
  100% {
    margin-left: 0%;
    @apply absolute w-1/2 bg-zz-p h-1;
  }
}

@keyframes filter0-1 {
  0% {
    margin-left: 0%;
    @apply absolute w-1/2 bg-zz-p h-1;
  }
  100% {
    margin-left: 50%;
    @apply absolute w-1/2 bg-zz-p h-1;
  }
}
.f1-0 {
  animation-name: filter1-0;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}

.f0-1 {
  animation-name: filter0-1;
  animation-duration: 1s;
  animation-direction: normal;
  animation-fill-mode: both;
}

@keyframes move {
  0% {
    @apply text-black font-spoq dark:text-white;
  }
  100% {
    @apply text-zz-p border-zz-p font-spoq;
  }
}
@keyframes move2 {
  0% {
    @apply text-black font-spoq dark:text-white;
  }
  100% {
    @apply text-zz-p border-zz-p font-spoq;
  }
}
.filter-one-select {
  animation-name: move;
  animation-duration: 0.5s;
  @apply text-zz-p border-zz-p font-spoq;
}
.filter-one-not-select {
  @apply text-black font-spoq dark:text-white;
}
.filter-two-select {
  animation-name: move;
  animation-duration: 0.5s;
  @apply text-zz-p font-spoq;
}
.filter-two-not-select {
  @apply text-black font-spoq dark:text-white;
}
.menu-one {
  @apply grid grid-cols-3 text-center;
}
.menu-two {
  @apply grid grid-cols-2 text-center mb-4;
}
.filter-text {
  @apply line-clamp-1;
}
</style>