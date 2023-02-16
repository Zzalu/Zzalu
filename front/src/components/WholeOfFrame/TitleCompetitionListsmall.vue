<template>
  <div>
    <ol class="flex flex-wrap justify-center">
      <li v-for="title_competition in title_competitions" :key="title_competition.titleHakwonId">
        <TitleCompetitionListItemsmall
          class="academy-list"
          :title_competition="title_competition"
          @click="goToTitleCompetition(title_competition.openDate)"
        />
      </li>
    </ol>
    <div class="pb-10"></div>
  </div>
</template>

<script>
import TitleCompetitionListItemsmall from './item/TitleCompetitionListItemsmall.vue';
import { getFinishTitleCompetition } from '@/api/titleCompetition';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
// import { useStore } from 'vuex';
export default {
  name: 'TitleCompetitionListsmall',
  components: {
    TitleCompetitionListItemsmall,
  },

  setup() {
    const router = useRouter();
    let title_competitions = ref();
    getFinishTitleCompetition(
      (data) => {
        title_competitions.value = data.data;
      },
      (error) => {
        console.log(error);
      },
    );

    const goToTitleCompetition = (open_date) => {
      // console.log(open_date);
      router.push(`/title-competition/${open_date}`);
    };

    return {
      title_competitions,
      goToTitleCompetition,
    };
  },
};
</script>

<style scoped lang="postcss">
.academy-list {
  box-shadow: 0 0 7px black;
  @apply m-2;
}
</style>
