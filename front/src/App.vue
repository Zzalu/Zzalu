<template>
  <div>
    <div class="main-margin">
      <router-view></router-view>
    </div>
    <!-- <main-lower-nav-bar></main-lower-nav-bar> -->
    <!-- <font-awesome-icon icon="fa-solid fa-house" /> -->
  </div>
</template>

<script>
// import MainLowerNavBar from '@/components/Common/NavBar/MainLowerNavBar.vue';
export default {
  name: 'App',
  components: {
    // MainLowerNavBar,
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
