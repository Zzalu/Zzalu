<template>
  <div>
    <ModifyMyBoardTopnav
    v-if="edit_mode"
    :board_name="this.$route.query.board_name"
    @EditMode="EditMode"
    />
    <MyBoardTopNav 
    v-else
    :board_name="this.$route.query.board_name"
    @EditMode="EditMode"
    />
    <div v-if="board_jjal_list">
      <div class="flex flex-wrap justify-center">
        <div v-for="(jjal, i) in board_jjal_list.gifs" :key="i">
          <div
            class="
              w-36
              h-36
              bg-center bg-cover
              m-2
              rounded-2xl
              border
              dark:border-zz-dark-input
            "
            :style="`background-image:url(${jjal.gifPath})`"
            @click="GoToJjalDetail(jjal)"
          ></div>
        </div>
      </div>
    </div>
    <MainBottomNav />
  </div>
</template>

<script>
import MyBoardTopNav from "../components/Common/NavBar/MyBoardTopNav";
import MainBottomNav from "../components/Common/NavBar/MainBottomNav";
import ModifyMyBoardTopnav from "../components/Common/NavBar/ModifyMyBoardTopNav"
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
    MyBoardTopNav,
    MainBottomNav,
    ModifyMyBoardTopnav
  },
  data() {
    return { 
      edit_mode : false
    }
  },
  methods: {
    GoToJjalDetail(jjal) {
      this.$router.push({ name: "zzal", params: { zzal_id: jjal.id } });
    },
    EditMode() {
      this.edit_mode = !this.edit_mode
    }
  },
};
</script>

<style>
</style>