<template>
  <div>
    <div class="menu1">
      <div>전체 고독방</div>
      <div>내가 개설한</div>
      <div>즐겨찾기</div>
    </div>
    <div class="menu2">
      <div>최신 대화순</div>
      <div>좋아요 순</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatListView",
};
</script>

<style scoped lang="postcss">
.menu1 {
  @apply grid grid-cols-3 text-center
}
.menu2 {
  @apply grid grid-cols-2 text-center
}
</style>
