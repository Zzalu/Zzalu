<template>
  <div>
    <p class="focus-text">user.id님을 위한 추천 짤</p>
    <hr class="mb-5 border-0 h-1 bg-zz-light-input dark:bg-zz-dark-div" />
    <div class="items">
      <div v-for="(RecommendJjal,i) in RecommendJjalListData" :key="i">
        <RecommendedJjalListItem
        :RecommendJjal="RecommendJjal"
        />
      </div>
    </div>
  </div>
</template>

<script>
import RecommendedJjalListItem from "../Main/Item/RecommendedJjalListItem"
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "RecommendedJjalList",
  setup() {
    const store = useStore();

    const RecommendJjalListData = computed(
      () => store.state.zzalListStore.recommend_gif_data
    );
    return {
      RecommendJjalListData
    }
  },
  components : {
    RecommendedJjalListItem
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
.recommend-img {
  background-image: url(../QuietChat/QuietChatList/assets/goodgood.gif);
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center;
}
</style>