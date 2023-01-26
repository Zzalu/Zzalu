<template>
  <!-- <div class="flex flex-col"> -->
  <div>
    <!-- <div class="h-nav-height sticky inset-x-0 top-0 bg-white">top navbar</div> -->
    <only-go-back-top-nav></only-go-back-top-nav>
    <small-logo-top-nav></small-logo-top-nav>
    <div class="main-margin">
      <div>
        <router-view></router-view>
      </div>
    </div>
    <main-bottom-nav></main-bottom-nav>
    <!-- <sign-up-bottom-nav></sign-up-bottom-nav> -->
  </div>
</template>

<script>
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';
import OnlyGoBackTopNav from '@/components/Common/NavBar/OnlyGoBackTopNav.vue';
// import SignUpBottomNav from './components/Common/NavBar/SignUpBottomNav.vue';
import SmallLogoTopNav from './components/Common/NavBar/SmallLogoTopNav.vue';

export default {
  name: 'App',
  components: {
    MainBottomNav,
    OnlyGoBackTopNav,
    // SignUpBottomNav,
    SmallLogoTopNav,
  },
  methods: {},
};
</script>

<style>
.main-margin {
  padding: 0 1.25rem;
  margin-bottom: 3.5rem;
  margin-top: 3.5rem;
}
</style>
