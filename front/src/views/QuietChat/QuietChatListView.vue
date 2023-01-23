<template>
  <div>
    <div class="menu1">
      <button>전체 고독방</button>
      <button>내가 개설한</button>
      <button>즐겨찾기</button>
    </div>
    <div class="menu2">
      <button>최신 대화순</button>
      <button>좋아요 순</button>
    </div>

    <div class="createchat">
      <div class="testtext">새로운 고독방 개설하기</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuietChatListView",
};
</script>

<style scoped lang="postcss">
.menu1 {
  @apply grid grid-cols-3 text-center border-b-4
}
.menu2 {
  @apply grid grid-cols-2 text-center border-t-4
}
.createchat{
  @apply bg-zz-p rounded-lg w-80 h-10 text-center text-white 
}
.testtext {
  @apply inline-block align-middle
}
</style>
