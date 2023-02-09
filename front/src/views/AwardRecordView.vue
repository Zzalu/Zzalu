<template>
  <only-go-back-top-nav></only-go-back-top-nav>
  <div class="flex justify-center">
    <h2 class="font-bold dark:text-white">수상내역</h2>
  </div>
  <award-record-list></award-record-list>
</template>

<script>
import AwardRecordList from '@/components/TitleCompetition/AwardRecordList.vue';
import OnlyGoBackTopNav from '@/components/Common/NavBar/OnlyGoBackTopNav.vue';
export default {
  components: { AwardRecordList, OnlyGoBackTopNav },
  name: 'UserAward',
};
</script>

<style lang="postcss" scoped></style>
