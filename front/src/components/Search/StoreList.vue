<template>
  <div ref="List">
    <div class="list-title">
      <p class="list-title-content">저장하기</p>
      <p :class="creating ? 'hide-list-title-make' : 'list-title-make'">
        <font-awesome-icon icon="fa-solid fa-plus" /> 새보관함 만들기
      </p>
    </div>
    <div v-for="i in list_name" :key="i" class="list-items">
      <input type="checkbox" id="check1" class="mr-2" />
      <label for="check1">{{ i }}</label>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "StoreList",
  setup() {
    const store = useStore();

    const close_list_modal = () => {
      store.commit("searchModalStore/close_list_modal");
    };
    const open_list_modal = computed(
      () => store.state.searchModalStore.open_list_modal
    );
    return {
      close_list_modal,
      open_list_modal,
    };
  },
  data() {
    return {
      creating: false,
      list_name: [
        "즐겨찾기",
        "고양이",
        "무한도전",
        "리스트1",
        "리스트2",
        "리스트3",
        "리스트4",
        "리스트5",
      ],
    };
  },
  mounted() {
    if (this.open_list_modal) {
      setTimeout(() => {
        document.addEventListener("click", this.ListoutClick)
      },100);
    } else {
      document.removeEventListener("click", this.ListoutClick);
    }
  },
  methods: {
    ListoutClick(e) {
      if (this.$refs.List == null) {
        return
      } else if (!this.$refs.List.contains(e.target)) {
        this.close_list_modal();
      }
    },
  },
};
</script>

<style scoped lang="postcss">
.list-title {
  @apply mt-2 flex items-center;
}

.list-title-content {
  @apply mr-auto ml-2;
}

.list-title-make {
  @apply text-xs ml-auto pr-2 text-zz-p;
}
.hide-list-title-make {
  @apply hidden;
}

.list-items {
  @apply m-2;
}
</style>