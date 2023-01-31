<template>
  <div>
    <div v-if="open_search_modal">
      <div :class="open_list_modal ? 'hide-modal-items' : null"></div>

      <div class="modal">
        <div class="modal-items">
          <SearchBar />
          <div v-for="(a, i) in 120" :key="i">
            <JjalListItem :i="i" @select_id="select_id" />
          </div>
        </div>
      </div>
    </div>
    <div v-if="open_list_modal">
      <div class="list-view-bg"></div>
      <StoreList class="list-view" />
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/Search/SearchBar";
import JjalListItem from "../components/Search/Item/JjalListItem";
import StoreList from "../components/Search/StoreList";
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "SearchView",
  setup() {
    const store = useStore();

    const open_search_modal = computed(
      () => store.state.searchModalStore.open_search_modal
    );
    const open_list_modal = computed(
      () => store.state.searchModalStore.open_list_modal
    );

    const send_select_jjal_num = (e) => {
      store.commit("searchModalStore/send_select_jjal_num", e);
    };
    return {
      open_search_modal,
      open_list_modal,
      send_select_jjal_num,
    };
  },
  components: {
    SearchBar,
    JjalListItem,
    StoreList,
  },
  methods: {
    select_id(e) {
      this.send_select_jjal_num(e);
    },
  },
};
</script>

<style scoped lang="postcss">
/* 보관함 모달창 전체 */
.list-view {
  margin: 0;
  @apply fixed inset-0 z-50 h-80 w-52 bg-white m-auto rounded-2xl border;
}
.modal {
  @apply fixed top-20 inset-x-0 border bg-white h-screen border-t-2 rounded-t-2xl;
}

/* 보관함 모달창 외부 클릭범위 */
.list-view-bg {
  opacity: 0.5;
  @apply fixed inset-0 z-10;
}
/* 짤 리스트 */
.modal-items {
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full;
}
/* 보관함 선택시 어두워지는 범위 */
.hide-modal-items {
  opacity: 0.3;
  @apply flex flex-wrap fixed inset-x-0 top-20 h-full bg-black z-10 rounded-t-2xl;
}
</style>
