<template>
  <div class="medal-board">
    <font-awesome-icon icon="fa-solid fa-medal" class="text-zz-bronze h-8" />
    <div class="medal-title">동상</div>
    <div>0회</div>
  </div>
</template>

<script>
export default {
  name: 'BronzeItem',
};
</script>

<style lang="postcss" scoped>
.medal-board {
  @apply bg-zz-light-input mx-2 text-center rounded-lg py-2;
}

.medal-title {
  @apply text-xs;
}
</style>
