<template>
  <div class="grid grid-cols-3 gap-4">
    <img
      src="https://media4.giphy.com/media/KztT2c4u8mYYUiMKdJ/giphy.gif?cid=99fefb40794ob3uvhnn0gdrcpw577jgnr6nkwc7msn64urk8&rid=giphy.gif&ct=g"
      class="col-span-2"
      alt=""
    />
    <div class="grid grid-rows-2 gap-2">
      <div class="flex place-items-end"
      @click="this.open_list_modal(gif_id)"
      >
        <font-awesome-icon icon="fa-solid fa-star" class="my-1 text-zz-s"/>
        <div class="mx-1 text-sm text-zz-s">스크랩하기</div>
      </div>
      <div class="flex">
        <font-awesome-icon icon="fa-solid fa-download" class="my-1 text-zz-s"/>
        <div class="mx-1 text-zz-s">저장하기</div>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";

export default {
  name: "ZzalListItem",
  setup() {
    const store = useStore();

    const open_list_modal = (e) => {
      store.commit("boardListStore/SELECT_GIF", e);
      store.commit("searchModalStore/open_list_modal");
    };

    return {
      open_list_modal
    }
  },
  props: {
    gif_id : Number,
  }
};
</script>

<style>
</style>