<template>
  <div>
    <div
      class="h-40 w-40 bg-cover bg-center border-2 rounded-2xl m-2"
      :style="`background-image:url(${board_list.thumbnailPath})`"
      @click="this.board_detail(board_list.id,board_list.boardName)"
    ></div>
    <p class="text-center font-spoq">{{ board_list.boardName }}</p>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRouter } from 'vue-router';

export default {
  name: "BoardTitleListItem",
  setup() {
    const store = useStore();
    const router = useRouter();

    const board_detail = (id,name) => {
      store.dispatch("boardListStore/getBoardData",id);
      router.push({name: "board", params: {board_id: id}, query: {board_name: name}});
    };

    return {
      board_detail
    }
  },
  props: {
    board_list: Object,
  },
};
</script>

<style>
</style>