<template>
  <div class="popular-img" :style="`background-image:url(${this.gifPath})`">
  </div>
</template>

<script>

export default {
  name: "PopularJjalListItem",
  props: {
    PopularJjal : Object,
  },
  data () {
    return {
      gifPath : this.PopularJjal.gifPath
    }
  },   
}   
</script>

<style scoped lang="postcss">
.popular-img {
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center dark:border-zz-dark-div;
}
</style>