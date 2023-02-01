<template>
  <div>
    <p class="focus-text">역대 제목학원 명예의 전당</p>
    <hr class="mb-5"/>
    <div class="flex overflow-x-auto">
      <div v-for="a in 10" :key="a">
        <AcademyListItem/>
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
  @apply font-bhs text-2xl line-clamp-1 mt-5;
}

</style>