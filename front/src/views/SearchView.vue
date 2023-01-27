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
  @apply fixed top-20 inset-x-0 border bg-white h-screen border-t-2 rounded-t-2xl;
}
.modal-items {
  @apply flex flex-wrap justify-center fixed overflow-y-scroll top-40 h-full;
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
