<template>
  <div>
    <div v-if="open_search_modal">
      <div class="modal">
        <div :class="open_list_modal ? 'hide-modal-items' : 'modal-items'">
          <SearchBar />
          <div v-for="(a, i) in 50" :key="i">
            <JjalListItem :i="i" />
          </div>
        </div>
      </div>
    </div>
    <div v-if="open_list_modal" class="list-view">
      <StoreList />
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
    return {
      open_search_modal,
      open_list_modal,
    };
  },
  components: {
    SearchBar,
    JjalListItem,
    StoreList,
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
.hide-modal {
  @apply fixed inset-x-0 top-20 bg-black border bg-white h-screen border-t-2 rounded-t-2xl;
}
.modal-items {
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full;
}

.hide-modal-items {
  @apply flex flex-wrap justify-center fixed top-40 h-full bg-white;
}
.modal-hidden {
  opacity: 0.4;
  @apply fixed top-full inset-x-0 border bg-zz-p h-screen border-t-2 rounded-t-2xl;
}
</style>
