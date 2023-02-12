<template>
  <div>
    <div class="bg-center">
      <img :src="`${gifpath}`" alt="" class="col-span-2 justify-center" />
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
      open_list_modal,
    };
  },
  props: {
    gif_id: Number,
  },
};
</script>

<style lang="postcss" scoped>
.view-count {
  @apply my-3 mx-2 text-zz-negative;
}
</style>