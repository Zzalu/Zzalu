<template>
  <div>
    <div v-if="img_select" class="select-jjal-box">
      <div>
        <div class="view-content">
          <font-awesome-icon class="view-icon" icon="fa-solid fa-eye" />
          <p class="view-count">1100</p>
        </div>
        <div>
          <font-awesome-icon
            class="scrap-icon"
            icon="fa-regular fa-star"
            @click="open_list_modal"
          />
        </div>
        <img
          class="select-jjal-img"
          src="../../QuietChat/QuietChatList/assets/rmfoTrnsk.gif"
        />
      </div>
    </div>
    <div v-else class="jjal-box">
      <div v-touch:press="LongClickStart" v-touch:release="LongClickEnd"
      @click="route"
      >
        <img
          class="jjal-img"
          src="../../QuietChat/QuietChatList/assets/Infinite_Challenge.jpg"
        />
      </div>
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
    const select_jjal_num = computed(
      () => store.state.searchModalStore.select_jjal_num
    );
    return {
      open_list_modal,
      select_jjal_num,
    };
  },
  data() {
    return {
      start_time: null,
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
  },
  methods: {
    LongClickStart() {
      this.start_time = Date.now();
    },
    LongClickEnd() {
      let result = Date.now() - this.start_time;
      if (result > 500) {
        this.$emit("select_id", this.i);
      }
    },
    route() {
      this.$router.push(`/zzal/${this.i}`);
    }
  },
};
</script>

<style scoped lang="postcss">
.view-content {
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
  @apply absolute z-10 text-white mt-16 ml-1 text-3xl;
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
  @apply h-full w-full rounded-2xl;
}
.jjal-img {
  @apply h-full w-full rounded-2xl;
}
</style>