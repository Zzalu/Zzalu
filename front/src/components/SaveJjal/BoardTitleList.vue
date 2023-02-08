<template>
  <div>
    <p> {{ user_id }}님의 보드</p>
    <div v-if="user_board_list"
    class="flex flex-wrap"
    >
      <div v-for="(board_list, i) in user_board_list.boards" :key="i">
          <BoardTitleListItem :board_list="board_list" />
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, onBeforeMount } from "@vue/runtime-core";
import BoardTitleListItem from "./Item/BoardTitleListItem";

export default {
  name: "BoardTitleList",
  setup() {
    const store = useStore();
    const user_id = window.localStorage.getItem("id");

    const user_board_list = computed(
      () => store.state.boardListStore.user_board_list
    );

    onBeforeMount(() => {
      store.dispatch("boardListStore/getUserBoardList", user_id);
    });
    return {
      user_board_list,
      user_id
    };
  },
  components: {
    BoardTitleListItem,
  },
};
</script>

<style>
</style>