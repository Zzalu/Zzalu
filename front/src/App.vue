<template>
  <div>
    <div class="main-margin">
      <router-view></router-view>
    </div>
    <main-lower-nav-bar class="fixed inset-x-0 bottom-0"></main-lower-nav-bar>
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
  padding: 0px;
  margin-top: 3.5rem;
  margin-left: 1.25rem;
  margin-right: 1.25rem;
}
</style>
