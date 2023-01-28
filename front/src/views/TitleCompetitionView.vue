<template>
  <div>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <div>
      <!-- 오늘의 제목학원 header -->
      <div>
        <div>
          <span>{{ date }}</span>
          <h1>오늘의 제목학원</h1>
          <span>{{ isLogin }}</span>
        </div>
        <button>역대 제목학원</button>
        <!-- 짤 -->
        <div><img src="../assets/logo.png" alt="짤" /></div>
      </div>

      <!-- TOP 5 -->
      <div>
        <ol>
          <li>
            <button>
              <span> 1위입니다 </span>
            </button>
            <font-awesome-icon icon="fa-regular fa-heart" />
          </li>
        </ol>
      </div>
      <!-- 댓글 -->
      <div></div>
    </div>
  </div>
</template>

<script>
import OnlySmallLogoTopNav from '@/components/Common/NavBar/OnlySmallLogoTopNav.vue';
import { useStore } from 'vuex';
import { computed } from '@vue/runtime-core';
export default {
  components: { OnlySmallLogoTopNav },
  name: 'TitleCompetitionView',
  setup() {
    const store = useStore();
    // const isLogin = store.state.useStore.isLogin;
    // state는 moduleName으로 쪼개서 들어간다.
    const date = computed(() => store.state.titleCompetitionStore.date);
    const isLogin = computed(() => store.state.userStore.isLogin);
    return {
      date,
      isLogin,
    };
  },
};
</script>

<style></style>
