<template>
  <div class="modal">
    <SearchBar />
      <div class="flex flex-wrap justify-center">
        <div v-for="(a, i) in 20" :key="i">
          <JjalListItem />
        </div>
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/Search/SearchBar";
import JjalListItem from "../components/Search/Item/JjalListItem";

export default {
  name: "SearchView",
  data() {
    return {};
  },
  components: {
    SearchBar,
    JjalListItem,
  },
};
</script>

<style scoped lang="postcss">
.navbar-main {
  @apply h-nav-height fixed inset-x-0 top-24 bg-white flex items-center justify-center;
}
.navbar-input-box {
  @apply bg-zz-light-input w-10/12 h-9 flex items-center px-5 py-1 rounded-lg;
}
.navbar-icon {
  @apply text-zz-darkgray mr-2;
}
.navbar-input {
  @apply bg-transparent text-zz-darkgray;
}
.modal {
  @apply fixed inset-x-0 top-20 border bg-zz-light-input px-5 h-screen border-t-2 rounded-t-2xl pt-20;
}
</style>
