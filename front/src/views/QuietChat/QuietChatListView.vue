<template>
  <div>
    <div class="Menu1">
      <button>전체 고독방</button>
      <button>내가 개설한</button>
      <button>즐겨찾기</button>
    </div>
    <div class="Menu2">
      <button>최신 대화순</button>
      <button>좋아요 순</button>
    </div>
    <div class="CreateChat">
      <button class="CreateChatButton">
        <p class="testtext">새로운 고독방 개설하기</p>
      </button>
    </div>

    <div class="CardContainer">
      <div class="CardImgContainer">
        <!-- <img class="CardImg" src="./assets/Infinite_Challenge.jpg" alt="" /> -->
      </div>
      <div class="CardContent">
        <p>{{ data[0].name }}</p>
        <p>{{ data[0].content }}</p>
        <p>{{ data[0].hashtag }}</p>
        <p>방장 : {{ data[0].master }}</p>
        <p>좋아요 : {{ data[0].like }}</p>
        <p>생성일 : {{ data[0].createdAt }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import QuietChatData from "./QuietChatListData.js";

export default {
  name: "QuietChatListView",
  data() {
    return {
      data: QuietChatData,
    };
  },
};
</script>

<style scoped lang="postcss">
.Menu1 {
  @apply grid grid-cols-3 text-center border-b-4;
}
.Menu2 {
  @apply grid grid-cols-2 text-center border-t-4;
}
.CreateChat {
  @apply flex items-center;
}
.CreateChatButton {
  @apply bg-zz-p rounded-lg w-9/12 h-10 text-white mt-4 mx-auto;
}
.CardContainer {
  @apply grid grid-cols-12 h-full;
}
.CardImg {
}
.CardImgContainer{
  background-image : url("assets/Infinite_Challenge.jpg");
  @apply col-span-4 border-2 object-scale-down object-center;
}
.CardContent {
  @apply col-span-8;
}
</style>
