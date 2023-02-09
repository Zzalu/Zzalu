<template>
  <div class="grid grid-cols-3 mt-1 mb-6">
    <div class="medal-board">
      <font-awesome-icon icon="fa-solid fa-medal" class="text-zz-gold h-8" />
      <div class="medal-title">금상</div>
      <div>23.01.17</div>
    </div>
    <div class="medal-board">
      <font-awesome-icon icon="fa-solid fa-medal" class="text-zz-silver h-8" />
      <div class="medal-title">은상</div>
      <div>23.01.16</div>
    </div>
    <div class="medal-board">
      <font-awesome-icon icon="fa-solid fa-medal" class="text-zz-bronze h-8" />
      <div class="medal-title">동상</div>
      <div>23.01.14</div>
    </div>
    <div class="medal-board">
      <font-awesome-icon icon="fa-solid fa-medal" class="text-zz-bronze h-8" />
      <div class="medal-title">동상</div>
      <div>23.01.11</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AwardRecordList',
};
</script>

<style>
.medal-board {
  @apply bg-zz-light-input m-2 text-center rounded-lg py-2;
}

.medal-title {
  @apply text-xs;
}
</style>
