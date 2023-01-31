<template>
<div class="profile-title">스탯</div>
  <div class="user-stats">
    <div class="flex">
      <div class="stat-hashtag">#고양이들</div>
      <div class="graph">100%</div>
    </div>
    <div class="flex">
      <div class="stat-hashtag">#겁나긴해시태그</div>
      <div class="graph">10%</div>
    </div>
    <div class="flex">
      <div class="stat-hashtag">#hello~</div>
      <div class="graph">1%</div>
    </div>
    <div class="flex justify-end">
      <div class="result-stat">{{}}님은 00 입니다</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserStats",
}
</script>

<style lang="postcss" scoped>
.user-stats {
  @apply bg-zz-s py-2 px-1 rounded mb-6 mt-1
}

.graph {
  @apply bg-white my-2 px-1 rounded
}

.stat-hashtag {
  width: 5rem;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
  display:inline-block;
  @apply text-white font-spoq mr-2 text-xs px-3 my-2 py-1
}

.result-stat {
  @apply text-white font-spoq font-bold mx-4
}
</style>