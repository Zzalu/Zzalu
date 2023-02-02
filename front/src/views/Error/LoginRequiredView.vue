<template>
  <!-- 500 -->
  <div class="text-center-container">
    <div class="error-title-container">
      <div class="oops">OOPS</div>
      <div class="sad-face">:(</div>
      <div class="resting-face">:|</div>
    </div>
    <!-- 해당페이지는 로그인 후 이용하실 수 있어요 -->
    <div class="text-oneline">
        <div class="page-sub-title-black">해당 페이지는</div>
        <router-link to="/login" class="page-sub-title-primary">로그인</router-link>
        <div class="page-sub-title-black">후 이용하실 수 있어요</div>
    </div>
    <!-- 아직 회원이 아니신가요? 회원가입 -->
    <div class="redir-accounts">
      <div>
        아직 회원이 아니신가요? |
      </div>
      <router-link to="/signup" class="redir-accounts-click">
        회원가입
      </router-link>
    </div>
    <button class="go-back-button" @click="goBack">이전 페이지</button>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';

export default {
  name: "LoginRequiredView",
  components: {
    MainBottomNav,
  },
  methods: {
    goBack () {
      this.$router.go(-1)
    }
  },


}
</script>

<style scoped lang='postcss'>

  .oops {
    @apply mt-16 text-8xl font-carter text-zz-s
  }
  .sad-face {
    transform: rotate(90deg);
    right: 6rem;
    @apply relative mt-12 font-pop text-4xl
  }

  .resting-face {
    transform: rotate(90deg);
    right: 1rem;
    bottom: 1.5rem;
    @apply relative font-pop text-4xl
  }

  .page-sub-title-black {
    @apply mt-60 mx-1 font-spoq font-bold
  }

  .page-sub-title-primary {
    @apply mt-60 font-spoq font-bold text-zz-p
  }



</style>