<template>
  <div>
    <OnlyBigLogoTopNav />
    <div v-if="board_jjal_list">
      <p> {{ this.$route.query.board_name }} </p>
      <div v-for="(jjal, i) in board_jjal_list.gifs" :key="i">
        <div 
        class="w-40 h-40 bg-center bg-cover"
        :style="`background-image:url(${jjal.gifPath})`"></div>
      </div>
    </div>
    <MainBottomNav />
  </div>
</template>

<script>
import OnlyBigLogoTopNav from "../components/Common/NavBar/OnlyBigLogoTopNav";
import MainBottomNav from "../components/Common/NavBar/MainBottomNav";
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "BoardDetailView",
  setup() {
    const store = useStore();

    const board_jjal_list = computed(
      () => store.state.boardListStore.board_detail_list
    );
    return {
      board_jjal_list,
    };
  },
  components: {
    OnlyBigLogoTopNav,
    MainBottomNav,
  },
};
</script>

<style>
</style>