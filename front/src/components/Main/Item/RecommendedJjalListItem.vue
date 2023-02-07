<template>
  <div class="recommend-img" :style="`background-image:url(${this.gifPath})`"></div>
</template>

<script>

export default {
  name : "RecommendedJjalListItem",
  props : {
    RecommendJjal : Object,
  },
  data() {
    return {
      gifPath : this.RecommendJjal.gifPath
    }
  }
}
</script>

<style scoped lang="postcss">
  .recommend-img {
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center border-zz-dark-div;
}
</style>