<template>
  <div>
    <div v-if="open_modal">
      <div :class="ListView ? 'hide-modal' : 'modal'">
        <SearchBar />
        <div :class="ListView ? 'hide-modal-items' : 'modal-items'">
          <div v-for="(a, i) in 50" :key="i">
            <JjalListItem @now="refresh()" :i="i" />
          </div>
        </div>
      </div>
    </div>
    <div v-if="ListView"
    class="list-view"
    >
      <StoreList/>
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/Search/SearchBar";
import JjalListItem from "../components/Search/Item/JjalListItem";
import StoreList from "../components/Search/StoreList";
import { useStore } from "vuex";

export default {
  name: "SearchView",
  setup() {
    const store = useStore();
    // state는 namespaced 유무와 상관 없이 moduleName으로 쪼개서 들어간다.
    const open_modal = store.state.searchModalStore.open_search_modal;
    //const test= computed(() => store.state.xxxStore.test);
    return {
      open_modal,
    };
  },
  data() {
    return {
      ListView: true,
    };
  },
  methods: {
    refresh() {
      console.log("asdasd");
      this.now = false;
    },
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
  margin:0;
  @apply fixed inset-0 z-50 h-80 w-52 bg-white m-auto rounded-2xl
}
.modal {
  @apply fixed top-20 inset-x-0 border bg-white h-screen border-t-2 rounded-t-2xl;
}
.hide-modal{
  opacity: 0.5;
  @apply fixed inset-0 bg-black
}
.modal-items {
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full;
}

.hide-modal-items {
  opacity: 0.4;
  @apply flex flex-wrap justify-center fixed top-40 h-full;
}
.modal-hidden {
  transition: all 0.5s ease;
  @apply fixed top-full inset-x-0 border bg-zz-p h-screen border-t-2 rounded-t-2xl;
  .modal {
    @apply fixed top-20 inset-x-0 border bg-zz-p h-screen border-t-2 rounded-t-2xl;
  }
}

/* .modal-items {
  transition: all 0.5s ease;
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full;
} */
/* .modal-items:hover {
  transition: all 0.5s ease;
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-full h-full;
} */
</style>
