<template>
  <div class="flex justify-center">
    <ol class="grid grid-cols-2 mt-1 mb-6">
      <li v-for="title_competition in title_competitions" :key="title_competition.titleHakwonId">
        <title-competition-list-item class="academy-list" :title_competition="title_competition" />
      </li>
    </ol>
  </div>
</template>

<script>
import TitleCompetitionListItem from './item/TitleCompetitionListItem.vue';
// import { useStore } from 'vuex';
export default {
  name: 'TitleCompetitionList',
  components: {
    TitleCompetitionListItem,
  },

  setup() {
    // const store = useStore();
    // let title_competitions = store.state.titleCompetitionStore.title_competitions;
    let title_competitions = [
      {
        titleHakwonId: 1,
        openData: '2023-02-09',
        zzalurl:
          'https://zzalu-buket.s3.ap-northeast-2.amazonaws.com/TitleHakwon/10a8bccb-9202-40a5-b9cd-b2adf0431929-9279-apex.png',
        state: 'DONE',
      },
      {
        titleHakwonId: 2,
        openData: '2023-02-09',
        zzalurl:
          'https://zzalu-buket.s3.ap-northeast-2.amazonaws.com/TitleHakwon/10a8bccb-9202-40a5-b9cd-b2adf0431929-9279-apex.png',
        state: 'DONE',
      },
      {
        titleHakwonId: 3,
        openData: '2023-01-09',
        zzalurl:
          'https://zzalu-buket.s3.ap-northeast-2.amazonaws.com/TitleHakwon/10a8bccb-9202-40a5-b9cd-b2adf0431929-9279-apex.png',
        state: 'DONE',
      },
    ];
    return {
      title_competitions,
    };
  },
};
</script>

<style scoped lang="postcss">
.academy-list {
  box-shadow: 0 0 7px black;
  @apply mt-2;
}
</style>
