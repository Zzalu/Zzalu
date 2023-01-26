<template>
  <div class="text-center-container">
    <div class="error-page-title">404</div>
    <div class="sad-face">:(</div>
    <div class="error-page-sub-title">요청하시는 페이지를 찾을 수 없습니다 !</div>
    <div class="error-page-content">
      찾으시려는 페이지의 주소를 잘못 입력 되었거나,
    </div>
    <div class="error-page-content">
      주소의 변경 혹은 삭제로인해 현재 사용하실 수 없습니다.
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
  name: "Error404View",
  components: {
    MainBottomNav,
  }
};
</script>


<style scoped lang="postcss">
@import "./errors.css";

.sad-face {
  transform: rotate(90deg);
  @apply absolute mt-28 font-pop text-4xl
}
</style>y