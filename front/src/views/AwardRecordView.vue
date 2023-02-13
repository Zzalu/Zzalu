<template>
  <div>
    <only-go-back-top-nav></only-go-back-top-nav>
    <div class="flex justify-center">
      <h2 class="font-bold dark:text-white">수상내역</h2>
    </div>
    <!-- sort -->
    <nav class="flex justify-end">
      <div>
        <button :class="[sort == 'LATEST' ? 'sort-text-active' : 'sort-text']" @click="clickSortBtn('LATEST')">
          최신순
        </button>
        <button
          :class="[sort == 'CHRONOLOGICAL' ? 'sort-text-active' : 'sort-text']"
          @click="clickSortBtn('CHRONOLOGICAL')"
        >
          과거순
        </button>
      </div>
    </nav>
    <award-record-list :member_id="member_id" :sort="sort"></award-record-list>
    <main-bottom-nav></main-bottom-nav>
  </div>
</template>

<script>
import AwardRecordList from '@/components/TitleCompetition/AwardRecordList.vue';
import OnlyGoBackTopNav from '@/components/Common/NavBar/OnlyGoBackTopNav.vue';
import MainBottomNav from '../components/Common/NavBar/MainBottomNav.vue';
import { useRoute } from 'vue-router';
import { ref } from 'vue';

export default {
  components: { AwardRecordList, OnlyGoBackTopNav, MainBottomNav },
  name: 'UserAward',
  setup() {
    const route = useRoute();
    const member_id = route.params.member_id;
    let sort = ref('LATEST');

    const clickSortBtn = (type) => {
      sort.value = type;
    };

    return { member_id, sort, clickSortBtn };
  },
};
</script>

<style lang="postcss" scoped>
.sort-text {
  @apply text-xs text-zz-p mr-1 font-thin;
}
.sort-text-active {
  @apply text-xs text-zz-s mr-1 font-black;
}
</style>
