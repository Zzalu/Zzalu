<template>
  <div>
    <font-awesome-icon icon="fa-solid fa-lock" class="view-count" />
    <div>{{visitedcount}}</div>
  </div>
  <div class="grid grid-cols-3 gap-4">
    <img :src="`${gifpath}`" alt="" class="col-span-2 justify-center">
    <div class="grid grid-rows-2 gap-2 ">
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
  data() {
    return {
      gifpath: this.$route.query.gifpath,
      // id: this.$route.query.id,
      // tags: this.$route.query.tags,
      visitedcount: this.$route.query.visitedcount,
    };
  },
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

<style lang="postcss" scoped>

.view-count {
  @apply my-3 mx-2 text-zz-negative
}
</style>