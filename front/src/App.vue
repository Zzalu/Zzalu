<template>
  <div class="main-margin">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {},
  methods: {
  },
};
</script>

<style>
.main-margin{
  padding: 0px;
  margin-top : 3.5rem;
  margin-left : 1.25rem;
  margin-right : 1.25rem;
  /* margin-bottom : 3.5rem; */
}
</style>
