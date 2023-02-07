<template>
  <div>
    <p class="focus-text">역대 제목학원 명예의 전당</p>
    <hr class="mb-3 border-0 h-1 bg-zz-light-input dark:bg-zz-dark-div"/>
    <div class="items">
      <div v-for="a in 10" :key="a">
        <AcademyListItem class="academy-list"/>
      </div>
    </div>
  </div>
</template>

<script>
import AcademyListItem from "../Main/Item/AcademyListItem"

export default {
  name:"AcademyList",
  components : {
    AcademyListItem
  }
};
</script>

<style scoped lang="postcss">
.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5 dark:text-white;
}
.items {
  @apply flex overflow-x-auto
}
.items::-webkit-scrollbar {
  display:none;
}
.academy-list {
  box-shadow:0 0 7px black;
  @apply mt-2
}
</style>