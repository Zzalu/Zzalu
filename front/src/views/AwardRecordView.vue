<template>
  <div class="flex justify-center">
    <h2 class="font-bold">수상내역</h2>
  </div>
  <award-record-list></award-record-list>
</template>

<script>
import AwardRecordList from '@/components/TitleCompetition/AwardRecordList.vue';
export default {
  components: { AwardRecordList },
  name: 'UserAward',
};
</script>

<style lang="postcss" scoped></style>
