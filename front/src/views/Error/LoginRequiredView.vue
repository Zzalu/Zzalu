<template>
  <div class="text-center-container">
    <div class="oops">OOPS</div>
    <div class="sad-face">:(</div>
    <div class="resting-face">:|</div>
    <div class="flex">
        <div class="page-sub-title-black">해당 페이지는</div>
        <div class="page-sub-title-primary">로그인</div>
        <div class="page-sub-title-black">후 이용하실 수 있어요</div>
    </div>

    <div class="find-id-and-pwd">
      <div>
        아직 회원이 아니신가요? |
      </div>
      <div class="find-id">
        회원가입
      </div>
    </div>
    <button class="go-back-button">이전 페이지</button>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';

export default {
  name: "LoginRequiredView",
  components: {
    MainBottomNav,
  }
}
</script>

<style scoped lang='postcss'>
  .oops {
    @apply mt-16 text-8xl font-carter text-zz-s
  }
  .sad-face {
    transform: rotate(90deg);
    right: 11.5rem;
    @apply absolute mt-24 font-pop text-4xl
  }

  .resting-face {
    transform: rotate(90deg);
    right: 16.5rem;
    top: 9.8rem;
    @apply absolute font-pop text-4xl
  }

  .page-sub-title-black {
    @apply my-6 mx-1 font-spoq font-bold
  }

  .page-sub-title-primary {
    @apply my-6 font-spoq font-bold text-zz-p
  }

  .find-id-and-pwd {
    @apply flex float-right text-zz-darkgray text-xs mt-1;
  }

  .find-id {
    @apply mx-1 text-zz-p;
  }

</style>