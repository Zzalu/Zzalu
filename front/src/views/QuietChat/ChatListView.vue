<template>
  <div>
    <div class="Menu1">
      <button v-if="filter1==0" class="Filter1Select" @click="filter1=0;">전체 고독방</button>
      <button v-else class="Filter1NotSelect" @click="filter1=0;">전체 고독방</button>
      <button v-if="filter1==1" class="Filter1Select" @click="filter1=1">내가 개설한</button>
      <button v-else class="Filter1NotSelect" @click="filter1=1">내가 개설한</button>
      <button v-if="filter1==2" class="Filter1Select" @click="filter1=2">즐겨찾기</button>
      <button v-else class="Filter1NotSelect" @click="filter1=2">즐겨찾기</button>
    </div>
    <div class="Menu2">
      <button v-if="filter2==0" class="Filter2Select" @click="filter2=0">최신 대화순</button>
      <button v-else class="Filter2NotSelect" @click="filter2=0">최신 대화순</button>
      <button v-if="filter2==1" class="Filter2Select" @click="filter2=1">좋아요 순</button>
      <button v-else class="Filter2NotSelect" @click="filter2=1">좋아요 순</button>
    </div>
    <div class="CreateChat">
      <button class="CreateChatButton">
        <p class="testtext">새로운 고독방 개설하기</p>
      </button>
    </div>

    <div class="CardContainer" v-for="(datas, j) in data" :key="j">
      <!-- <div class="CardImgContainer" :style="{backgroundImage : `url(${datas.Image})`}"></div> -->
      <!-- <div class="CardImgContainer" style="background-image:url('./assets/Infinite_Challenge.jpg')"></div> -->
      <div class="CardImgContainer"></div>
      <!-- <img class="CardImg" :src="`${datas.Image}`" alt="" /> -->
      <div class="CardContent">
        <p class="master_p">🎖️{{ datas.master }}</p>
        <p class="title_p">{{ datas.name }}</p>
        <p class="content_p">{{ datas.content }}</p>
        <div class="hashtag_div">
          <p v-for="(hashtags, i) in datas.hashtag" :key="i" class="hashtag_p">
            {{ hashtags }}
          </p>
        </div>
        <div class="lastlane">
          <p class="updated_p">최근대화시간 : {{ datas.updatedAt }}</p>
          <p class="like_p">❤️{{ datas.like }}</p>
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
      filter1 : 0,
      filter2 : 0,
    };
  },
};
</script>

<style scoped lang="postcss">
.Filter1Select {
  @apply text-zz-p border-b-4 border-zz-p
}
.Filter1NotSelect {
  @apply text-black border-b-4
}
.Filter2Select {
  @apply text-zz-p border-t-4 border-zz-p
}
.Filter2NotSelect {
  @apply text-black border-t-4;
}
.Menu1 {
  @apply grid grid-cols-3 text-center;
}
.Menu2 {
  @apply grid grid-cols-2 text-center;
}
.CreateChat {
  @apply flex items-center;
}
.CreateChatButton {
  @apply bg-zz-p rounded-lg w-9/12 h-10 text-white mt-4 mx-auto;
}
.CardContainer {
  @apply grid grid-cols-12 text-white mt-5 h-32;
}
.CardImgContainer {
  background-image: url(./assets/Dragon.jpg);
  @apply col-span-4 border-2 bg-contain bg-center bg-no-repeat rounded-l-lg;
}
.CardContent {
  @apply col-span-8 bg-zz-p rounded-r-lg pl-2 flex flex-col;
}
.master_p {
  font-size: 0.25rem;
  @apply pt-2;
}
.title_p {
  @apply text-xl font-bold;
}

.content_p {
  @apply text-sm;
}

.hashtag_div {
  @apply flex text-xs text-zz-silver flex-wrap;
}

.hashtag_p {
  font-size: 0.5rem;
  @apply ml-1 max-h-5 block;
}
.like_p {
  @apply ml-auto mr-2;
}
.updated_p {
  @apply  mr-auto;
}
.lastlane{
  font-size: 0.5rem;
  @apply flex mt-auto pb-2
}
</style>
