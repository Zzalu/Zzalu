<template>
  <div>
    <div class="menu-one">
      <button v-if="filter1 == 0" class="filter-one-select" @click="filter1 = 0">
        전체 고독방
      </button>
      <button v-else class="filter-one-not-select" @click="filter1 = 0">
        전체 고독방
      </button>
      <button v-if="filter1 == 1" class="filter-one-select" @click="filter1 = 1">
        내가 개설한
      </button>
      <button v-else class="filter-one-not-select" @click="filter1 = 1">
        내가 개설한
      </button>
      <button v-if="filter1 == 2" class="filter-one-select" @click="filter1 = 2">
        즐겨찾기
      </button>
      <button v-else class="filter-one-not-select" @click="filter1 = 2">
        즐겨찾기
      </button>
    </div>
    <div class="menu-two">
      <button v-if="filter2 == 0" class="filter-two-select" @click="filter2 = 0">
        최신 대화순
      </button>
      <button v-else class="filter-two-not-select" @click="filter2 = 0">
        최신 대화순
      </button>
      <button v-if="filter2 == 1" class="filter-two-select" @click="filter2 = 1">
        좋아요 순
      </button>
      <button v-else class="filter-two-not-select" @click="filter2 = 1">
        좋아요 순
      </button>
    </div>
    <div class="create-chat-button">
      <router-link to="/make-chat" class="create-chat">
        새로운 고독방 개설하기
      </router-link>
    </div>

    <div class="card-container" v-for="(datas, j) in data" :key="j">
      <div class="card-img-contanier"></div>
      <div class="card-content">
        <p class="master-p">🎖️{{ datas.master }}</p>
        <p class="title-p">{{ datas.name }}</p>
        <p class="content-p">{{ datas.content }}</p>
        <div class="hashtag-div">
          <p v-for="(hashtags, i) in datas.hashtag" :key="i" class="hashtag-p">
            {{ hashtags }}
          </p>
        </div>
        <div class="last-lane">
          <p class="updated-p">최근대화시간 : {{ datas.updatedAt }}</p>
          <p class="like-p">❤️{{ datas.like }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import QuietChatData from "./QuietChatListData.js";

export default {
  name: "ChatListView",
  data() {
    return {
      data: QuietChatData,
      filter1: 0,
      filter2: 0,
    };
  },
};
</script>

<style scoped lang="postcss">
/* 필터 버튼  */
.filter-one-select {
  @apply text-zz-p border-b-4 border-zz-p;
}
.filter-one-not-select {
  @apply text-black border-b-4;
}
.filter-two-select {
  @apply text-zz-p border-t-4 border-zz-p;
}
.filter-two-not-select {
  @apply text-black border-t-4;
}
.menu-one {
  @apply grid grid-cols-3 text-center;
}
.menu-two {
  @apply grid grid-cols-2 text-center mb-4;
}

/* 고독방 생성하기 버튼 */
.create-chat-button {
  @apply bg-zz-p rounded-lg w-9/12 h-10 text-white mx-auto text-center py-2;
}

/* 고독방 리스트 카드 */
.card-container {
  @apply grid grid-cols-12 text-white mt-5 h-32;
}
.card-img-contanier {
  background-image: url(./assets/Dragon.jpg);
  @apply col-span-4 border-2 bg-contain bg-center bg-no-repeat rounded-l-lg;
}
.card-content {
  @apply col-span-8 bg-zz-p rounded-r-lg pl-2 flex flex-col;
}
.master-p {
  font-size: 0.25rem;
  @apply pt-2;
}
.title-p {
  @apply text-xl font-bold line-clamp-1;
}

.content-p {
  @apply text-xs line-clamp-2 h-8;
}

.hashtag-div {
  @apply flex flex-row text-xs text-white flex-wrap line-clamp-2 h-8;
}

.hashtag-p {
  font-size: 0.5rem;
  @apply ml-1;
}
.like-p {
  @apply ml-auto mr-2;
}
.updated-p {
  @apply mr-auto;
}
.last-lane {
  font-size: 0.5rem;
  @apply flex mt-auto pb-2;
}
</style>
