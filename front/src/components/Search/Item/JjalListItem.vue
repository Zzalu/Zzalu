<template>
  <div :class="img_select ? `select-jjal-box` : `jjal-box`">
    <img
      class="jjal-img"
      src="../../QuietChat/QuietChatList/assets/4ekffk.gif"
      @mousedown="LongClickStart"
      @mouseup="LongClickEnd"
    />
  </div>
</template>

<script>
export default {
  name: "JjalListItem",
  data() {
    return {
      start_time: null,
      img_select: false
    };
  },
  methods: {
    LongClickStart() {
      this.start_time = Date.now();
    },
    LongClickEnd() {
      let result = Date.now() - this.start_time
      if (result > 300) {
        this.img_select = true
      }
    },
  },
};
</script>

<style scoped lang="postcss">

.select-jjal-box {
  overflow: hidden;
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(0, 0, 0));
  @apply w-32 h-32 m-2 rounded-2xl flex items-center justify-center;
}

.jjal-box {
  overflow: hidden;
  @apply w-32 h-32 m-2 rounded-2xl flex items-center justify-center;
}

.jjal-img {
  @apply h-full w-full;
}
</style>