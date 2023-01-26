<template>
  <div>
    <div class="menu-one">
      <button
        v-if="filter1 == 0"
        class="filter-one-select"
        @click="filter1 = 0"
      >
        <p class="filter-text">전체 고독방</p>
      </button>
      <button v-else class="filter-one-not-select" @click="filter1 = 0">
        <p class="filter-text">전체 고독방</p>
      </button>
      <button
        v-if="filter1 == 1"
        class="filter-one-select"
        @click="filter1 = 1"
      >
        <p class="filter-text">내가 개설한</p>
      </button>
      <button v-else class="filter-one-not-select" @click="filter1 = 1">
        <p class="filter-text">내가 개설한</p>
      </button>
      <button
        v-if="filter1 == 2"
        class="filter-one-select"
        @click="filter1 = 2"
      >
        <p class="filter-text">즐겨찾기</p>
      </button>
      <button v-else class="filter-one-not-select" @click="filter1 = 2">
        <p class="filter-text">즐겨찾기</p>
      </button>
    </div>
    <div class="menu-two">
      <button
        v-if="filter2 == 0"
        class="filter-two-select"
        @click="filter2 = 0"
      >
        <p class="filter-text">최신 대화순</p>
      </button>
      <button v-else class="filter-two-not-select" @click="filter2 = 0">
        <p class="filter-text">최신 대화순</p>
      </button>
      <button
        v-if="filter2 == 1"
        class="filter-two-select"
        @click="filter2 = 1"
      >
        <p class="filter-text">좋아요 순</p>
      </button>
      <button v-else class="filter-two-not-select" @click="filter2 = 1">
        <p class="filter-text">좋아요 순</p>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatFilter",
  data() {
    return {
      filter1: 0,
      filter2: 0,
    };
  },
};
</script>

<style scoped lang="postcss">
.filter-one-select {
  @apply text-zz-p border-b-4 border-zz-p;
}
.filter-one-not-select {
  @apply text-black border-b-4;
}
.filter-two-select {
  @apply text-zz-p border-t-4 border-zz-p;
}
.filter-two-not-select {
  @apply text-black border-t-4;
}
.menu-one {
  @apply grid grid-cols-3 text-center;
}
.menu-two {
  @apply grid grid-cols-2 text-center mb-4;
}
.filter-text {
  @apply line-clamp-1;
}
</style>