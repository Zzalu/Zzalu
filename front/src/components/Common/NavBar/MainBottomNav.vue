<template>
  <!-- 로그인 후 nav -->
  <div
    class="
      h-nav-height
      fixed
      box-border
      inset-x-0
      bottom-0
      border-t-2 border-zz-light-div
      z-50
    "
  >
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
      <router-link to="/title-competition" class="nav_item">
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

export default {
  name: "MainBottomNavBar",
  setup() {
    const store = useStore();

    const open_modal = () => {
      store.commit("searchModalStore/open_search_modal");
    };
    return {
      open_modal,
    };
  },
};
</script>

<style scoped lang="postcss">
span {
  display: inline-block;
}
.nav_list {
  @apply flex flex-wrap h-nav-height content-center place-items-center bg-white dark:bg-black ;
}
.nav_item {
  @apply flex-1 cursor-pointer text-center dark:text-white;
}
.search {
  @apply h-12 w-12 bg-zz-p rounded-xl text-white p-3;
}
</style>
