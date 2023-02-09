<template>
  <!-- 로그인 후 nav -->
  <!-- <SearchView /> -->
  <div class="h-nav-height fixed border inset-x-0 bottom-0 border-t-2 border-zz-light-div z-50 dark:border-zz-dark-div">
    <div class="close-modal" v-if="check_search_modal" @click="close_modal"></div>
    <div class="close-modals" v-if="check_search_modal" @click="close_modal"></div>

    <ul class="nav_list">
      <button class="nav_item" @click="GoToMain">
        <font-awesome-icon icon="fa-solid fa-house" />
      </button>

      <button class="nav_item" @click="GoToChatList">
        <font-awesome-icon icon="fa-regular fa-comment-dots" />
      </button>
      <button class="nav_item" @click="open_modal">
        <span class="search"><font-awesome-icon icon="fa-solid fa-magnifying-glass" /></span>
      </button>
      <router-link to="/title-competition/2023-02-09" class="nav_item">
        <font-awesome-icon icon="fa-regular fa-lightbulb" />
        <i class="fa-solid fa-chevron-left"></i
      ></router-link>
      <!-- <div v-if="user_is_logged_in==true" class="nav_item">
        <router-link to="{ name: 'profile'}"><font-awesome-icon icon="fa-regular fa-user" /></router-link>
      </div> -->
      <div @click="GoToProfile" class="nav_item">
        <font-awesome-icon icon="fa-regular fa-user" />
      </div>
    </ul>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed } from '@vue/runtime-core';
// import { mapState } from 'vuex';
// import SearchView from '../../../views/SearchView';

export default {
  name: 'MainBottomNavBar',
  components: {
    // SearchView,
  },
  // computed:
  //   mapState({
  //     logged_in: state => state.userStore.isLogin,
  //   }),
  setup() {
    const store = useStore();
    const logged_in = window.localStorage.getItem('token');
    const current_user = window.localStorage.getItem('id');
    const open_chat_info = computed(() => store.state.quietChatStore.open_chat_info);
    const check_search_modal = computed(() => store.state.searchModalStore.open_search_modal);
    const close_search_modal = () => {
      store.commit('searchModalStore/open_search_modal');
      store.dispatch('zzalListStore/getFirstRandomGIFList');
    };
    const open_modal = () => {
      store.commit('searchModalStore/open_search_modal');
    };

    const today = new Date();
    const dateString =
      today.getFullYear + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2);

    return {
      dateString,
      open_modal,
      close_search_modal,
      check_search_modal,
      open_chat_info,
      logged_in,
      current_user,
    };
  },
  methods: {
    close_modal() {
      if (this.check_search_modal == true) {
        this.close_search_modal();
      }
    },
    GoToMain() {
      this.$router.push(`/main`);
    },
    GoToChatList() {
      this.$router.push(`/chat-list`);
    },

    GoToProfile() {
      if (this.logged_in != null) {
        console.log('지금 접속했어요');
        this.$router.push({ name: 'profile', params: { username: this.current_user } });
      } else {
        console.log('지금 접속 안했어요', this.logged_in);
        this.$router.push({ name: 'login-required' });
      }
    },
  },
  watch: {
    // 외부 스크롤 막기
    check_search_modal: function (value) {
      value ? (document.body.style.overflow = 'hidden') : document.body.style.removeProperty('overflow');
    },
    open_chat_info: function (value) {
      value ? (document.body.style.overflow = 'hidden') : document.body.style.removeProperty('overflow');
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
