<template>
  <!-- 로그인 후 nav -->

  <SearchView />
  <div
    class="
      h-nav-height
      fixed
      border
      inset-x-0
      bottom-0
      border-t-2 border-zz-light-div
      z-50
      dark:border-zz-dark-div
    "
  >
    <div
      class="close-modal"
      v-if="check_search_modal"
      @click="close_modal"
    ></div>
    <div
      class="close-modals"
      v-if="check_search_modal"
      @click="close_modal"
    ></div>

    <ul class="nav_list">
      <router-link to="/main" class="nav_item">
        <font-awesome-icon icon="fa-solid fa-house" />
      </router-link>

      <router-link to="/chat-list" class="nav_item">
        <font-awesome-icon icon="fa-regular fa-comment-dots" />
      </router-link>
      <li class="nav_item" @click="open_modal">
        <span class="search"
          ><font-awesome-icon icon="fa-solid fa-magnifying-glass"
        /></span>
      </li>
      <router-link to="/title-competition/1" class="nav_item">
        <font-awesome-icon icon="fa-regular fa-lightbulb" />
        <i class="fa-solid fa-chevron-left"></i
      ></router-link>

      <router-link to="/profile" class="nav_item"
        ><font-awesome-icon icon="fa-regular fa-user"
      /></router-link>
    </ul>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
import SearchView from "../../../views/SearchView";

export default {
  name: "MainBottomNavBar",
  components: {
    SearchView,
  },
  setup() {
    const store = useStore();

    const check_search_modal = computed(
      () => store.state.searchModalStore.open_search_modal
    );
    const close_search_modal = () => {
      store.commit("searchModalStore/open_search_modal");
    };
    const open_modal = () => {
      store.commit("searchModalStore/open_search_modal");
    };
    return {
      open_modal,
      close_search_modal,
      check_search_modal,
    };
  },
  methods: {
    close_modal() {
      if (this.check_search_modal == true) {
        this.close_search_modal();
      }
    },
  },
  watch: {
    check_search_modal: function (value) {
      value
        ? (document.body.style.overflow = "hidden")
        : document.body.style.removeProperty("overflow");
    },
  },
};
</script>

<style scoped lang="postcss">
.close-modal {
  height: 5rem;
  @apply fixed inset-x-0 top-0 z-40;
}
.close-modals {
  height: 3.2rem;
  @apply absolute inset-x-0 bottom-0 top-0 z-50;
}
span {
  display: inline-block;
}
.nav_list {
  @apply flex flex-wrap h-nav-height content-center place-items-center bg-white dark:bg-zz-bd;
}
.nav_item {
  @apply flex-1 cursor-pointer text-center dark:text-white;
}
.search {
  @apply h-12 w-12 bg-zz-p rounded-xl text-white p-3;
}
</style>
