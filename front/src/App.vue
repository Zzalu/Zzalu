<template>
  <div>
    <div class="my-nav-height px-5">
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
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
