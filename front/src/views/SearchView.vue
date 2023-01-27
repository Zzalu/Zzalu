<template>
  <div>
    <div v-if="open_modal">
      <div class="modal">
        <SearchBar />
        <div class="modal-items">
          <div v-for="(a, i) in 50" :key="i">
            <JjalListItem />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/Search/SearchBar";
import JjalListItem from "../components/Search/Item/JjalListItem";
import { mapState } from "vuex";

export default {
  name: "SearchView",
  data() {
    return {};
  },
  computed: {
    ...mapState({ open_modal: "open_search_modal" }),
  },
  components: {
    SearchBar,
    JjalListItem,
  },
};
</script>

<style scoped lang="postcss">
.modal {
  @apply fixed inset-x-0 top-20 border bg-white h-screen border-t-2 rounded-t-2xl;
}
.modal-items {
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full;
}
</style>
