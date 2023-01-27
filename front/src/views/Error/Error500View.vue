<template>
  <div class="text-center-container">
    <div class="error-page-title">500</div>
    <div class="sad-face">:(</div>
    <div class="resting-face">:|</div>
    <div class="error-page-sub-title">서비스 이용에 불편을 드려 죄송합니다.</div>
    <div class="error-age-content">
      시스템 에러가 발생하여 해당 페이지를 표시할 수 없습니다.
    </div>
    <div class="error-page-content">
      궁금한 점이 있으시면 고객센터를 통해 문의해주시기 바랍니다.
    </div>
    <button class="go-back-button">이전 페이지</button>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';

export default {
  name: "Error500View",
  components: {
    MainBottomNav,
  }
}
</script>

<style scoped lang='postcss'>
  @import "./errors.css";

  .sad-face {
    transform: rotate(90deg);
    @apply absolute right-28 mt-28 font-pop text-4xl
  }

  .resting-face {
    transform: rotate(90deg);
    top: 11rem;
    @apply absolute right-5 font-pop text-4xl
  }

</style>