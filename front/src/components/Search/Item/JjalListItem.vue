<template>
  <div>
    <!-- v-touch:press="LongClickStart" v-touch:release="LongClickEnd" -->
    <div v-if="img_select" class="select-jjal-box" @click="route">
      <div
        class="select-jjal-img"
        :style="`background-image:url(${this.gifPath})`"
      >
        <div class="view-content">
          <font-awesome-icon class="view-icon" icon="fa-solid fa-eye" />
          <p class="view-count">{{ zzal_info.visitedCount }}</p>
        </div>
        <div>
          <font-awesome-icon
            class="scrap-icon"
            icon="fa-regular fa-star"
            @click="open_list_modal"
            @click.stop="''"
          />
        </div>
      </div>
    </div>
    <div v-else class="jjal-box">
      <div
        v-touch:longtap="long_click"
        @click="route"
        class="jjal-img"
        :style="`background-image:url(${this.gifPath})`"
      ></div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "JjalListItem",
  setup() {
    const store = useStore();

    const open_list_modal = () => {
      store.commit("searchModalStore/open_list_modal");
    };
    const close_search_modal = () => {
      store.commit("searchModalStore/open_search_modal")
      store.dispatch("zzalListStore/getFirstRandomGIFLIST")
    };
    const send_select_gif_id_data = (data) => {
      store.commit("boardListStore/SELECT_GIF",data)
    }
    const select_jjal_num = computed(
      () => store.state.searchModalStore.select_jjal_num
    );
    return {
      open_list_modal,
      close_search_modal,
      send_select_gif_id_data,
      select_jjal_num,
    };
  },
  data() {
    return {
      start_time: null,
      gifPath: this.zzal_info.gifPath,
    };
  },
  computed: {
    img_select() {
      if (this.i == this.select_jjal_num) {
        return true;
      } else {
        return false;
      }
    },
  },
  props: {
    i: Number,
    zzal_info: Object,
  },
  methods: {
    route() {
      this.$router.push(`/zzal/${this.zzal_info.id}`);
      this.close_search_modal();
    },
    long_click() {
      this.$emit("select_id", this.i);
      this.send_select_gif_id_data(this.zzal_info.id);
    },
  },
};
</script>

<style scoped lang="postcss">
.view-content {
  /* filter: opacity(1) drop-shadow(0 0 0 rgb(255, 255, 255)); */
  @apply absolute flex text-white z-20 mt-2;
}
.view-icon {
  @apply text-2xl ml-1;
}
.view-count {
  @apply text-sm ml-1;
}
.scrap-icon {
  @apply absolute z-20 text-white mt-16 ml-1 text-3xl;
}
.select-jjal-box {
  overflow: hidden;
  /* filter: opacity(0.5) drop-shadow(0 0 0 rgb(0, 0, 0)); */
  @apply w-32 h-24 m-2 rounded-2xl flex items-center justify-center;
}

.jjal-box {
  overflow: hidden;

  @apply w-32 h-24 m-2 rounded-2xl flex items-center justify-center;
}
.select-jjal-img {
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(0, 0, 0));
  @apply h-full w-full rounded-2xl bg-cover;
}
.jjal-img {
  @apply h-full w-full rounded-2xl bg-cover;
}
</style>