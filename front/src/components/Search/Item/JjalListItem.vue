<template>
  <div class="jjal-box">
    <img
      class="jjal-img"
      src="../../QuietChat/QuietChatList/assets/rmfoTrnsk.gif"
      alt=""
    />
  </div>
  <!-- <div class="jjal-box">
    <img
      class="jjal-img"
      src="../../QuietChat/QuietChatList/assets/9_16img.jpg"
      alt=""
    />
  </div> -->
</template>

<script>
export default {
  name: "JjalListItem",
};
</script>

<style scoped lang="postcss">
.jjal-box {
  overflow: hidden;
  @apply w-32 h-32 m-2 rounded-2xl flex items-center justify-center;
}

.jjal-img {
  @apply h-full w-full;
}
</style>