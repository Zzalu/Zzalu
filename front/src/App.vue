<template>
  <!-- <div class="flex flex-col"> -->
  <div>
    <!-- <div class="h-nav-height sticky inset-x-0 top-0 bg-white">top navbar</div> -->
    <go-back-top-nav-bar></go-back-top-nav-bar>
    <logo-top-nav-bar></logo-top-nav-bar>
    <div class="main-margin">
      <div>
        <router-view></router-view>
      </div>
    </div>
    <main-lower-nav-bar></main-lower-nav-bar>
    <account-lower-nav-bar></account-lower-nav-bar>
    <!-- <main-lower-nav-bar></main-lower-nav-bar> -->
    <!-- <main-lower-nav-bar class="fixed bottom-0 left-0 bg-blue-600"></main-lower-nav-bar> -->
  </div>
</template>

<script>
import MainLowerNavBar from '@/components/Common/NavBar/MainLowerNavBar.vue';
import GoBackTopNavBar from '@/components/Common/NavBar/GoBackTopNavBar.vue';
import AccountLowerNavBar from './components/Common/NavBar/AccountLowerNavBar.vue';
import LogoTopNavBar from './components/Common/NavBar/LogoTopNavBar.vue';
export default {
  name: 'App',
  components: {
    MainLowerNavBar,
    GoBackTopNavBar,
    AccountLowerNavBar,
    LogoTopNavBar,
  },
  methods: {},
};
</script>

<style>
.main-margin {
  padding: 1.25rem;
  margin-bottom: 3.5rem;
}
</style>
