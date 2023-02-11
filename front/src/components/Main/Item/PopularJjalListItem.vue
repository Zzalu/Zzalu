<template>
  <div>
    <div v-if="img_select" class="select-jjal-box" @click="route">
      <div class="view-content">
        <font-awesome-icon class="view-icon" icon="fa-solid fa-eye" />
        <p class="view-count">{{ PopularJjal.visitedCount }}</p>
      </div>
      <div>
        <font-awesome-icon
          class="scrap-icon"
          icon="fa-regular fa-star"
          @click="open_list_modal"
          @click.stop="''"
        />
      </div>
      <div
        class="select-jjal-img"
        :style="`background-image:url(${this.gifPath})`"
      ></div>
    </div>
    <div
      v-else
      @click="route"
      class="popular-img"
      :style="`background-image:url(${this.gifPath})`"
      v-touch:longtap="long_click"
    ></div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "PopularJjalListItem",
  setup() {
    const store = useStore();

    const select_popular_jjal_num = computed(
      () => store.state.searchModalStore.select_popular_jjal_num
    );
    const open_list_modal = () => {
      store.commit("searchModalStore/open_list_modal");
    };
    const send_select_popular_gif_id_data = (e) => {
      store.commit("boardListStore/SELECT_POPULAR_GIF", e);
      store.commit("searchModalStore/default_recommend_num")
    };

    return {
      send_select_popular_gif_id_data,
      open_list_modal,
      select_popular_jjal_num,
    };
  },
  props: {
    PopularJjal: Object,
    i: Number,
  },
  data() {
    return {
      gifPath: this.PopularJjal.gifPath,
    };
  },
  computed: {
    img_select() {
      if (this.i == this.select_popular_jjal_num) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    route() {
      this.$router.push({
        name: "zzal",
        params: { zzal_id: this.PopularJjal.id },
        query: { gifpath: this.PopularJjal.gifPath, id: this.PopularJjal.id, tags: this.PopularJjal.tags, visitedcount:this.PopularJjal.visitedcount },
      });
    },
    long_click() {
      this.$emit("select_id", this.i);
      this.send_select_popular_gif_id_data(this.PopularJjal.id);
    },
  },
};
</script>

<style scoped lang="postcss">
.popular-img {
  @apply w-28 h-36 rounded-2xl mr-2 border bg-cover bg-no-repeat bg-center dark:border-zz-dark-div;
}

/* select */
.select-jjal-box {
  overflow: hidden;
  /* filter: opacity(0.5) drop-shadow(0 0 0 rgb(0, 0, 0)); */
  @apply w-28 h-36 mr-2 rounded-2xl flex items-center justify-center relative;
}
.select-jjal-img {
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(0, 0, 0));
  @apply h-full w-full rounded-2xl bg-cover bg-center;
}

.view-content {
  transform: translate(-2rem, -3.5rem);
  /* filter: opacity(1) drop-shadow(0 0 0 rgb(255, 255, 255)); */
  @apply absolute flex text-white z-10 mt-2;
}
.view-icon {
  @apply text-2xl ml-1;
}
.view-count {
  @apply text-sm ml-1;
}
.scrap-icon {
  transform: translate(0.25rem, 2rem);
  @apply absolute z-10 text-white ml-1 text-3xl;
}
</style>