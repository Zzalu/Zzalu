<template>
  <!-- <div class="flex flex-col"> -->
  <div>
    <div class="h-nav-height sticky inset-x-0 top-0 bg-white">top navbar</div>
    <div class="main-margin">
      <div>
        <router-view></router-view>
      </div>
    </div>
    <main-lower-nav-bar class="fixed inset-x-0 bottom-0"></main-lower-nav-bar>
    <!-- <main-lower-nav-bar></main-lower-nav-bar> -->
    <!-- <main-lower-nav-bar class="fixed bottom-0 left-0 bg-blue-600"></main-lower-nav-bar> -->
  </div>
</template>

<script>
import MainLowerNavBar from '@/components/Common/NavBar/MainLowerNavBar.vue';
export default {
  name: 'App',
  components: {
    MainLowerNavBar,
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
