<template>
  <div>
    <p class="focus-text">오늘의 인기 짤</p>
    <hr class="mb-5" />
    <div class="flex overflow-x-auto">
      <div v-for="b in 30" :key="b">
        <PopularJjalListItem/>
      </div>
    </div>
  </div>
</template>

<script>
import PopularJjalListItem from "../Main/Item/PopularJjalListItem"

export default {
  name : "PopularJjalList",
  components : {
    PopularJjalListItem
  }
}
</script>

<style scoped lang="postcss">

.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}

.popular-img {
  background-image:  url(../QuietChat/QuietChatList/assets/rmfoTrnsk.gif);
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center;
}

</style>