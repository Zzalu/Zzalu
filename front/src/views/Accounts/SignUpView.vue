<template>
  <div>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
      v-if="isDark==false"
    />
    <img
      alt="ZZalu Dark logo"
      class="logo"
      src="../../assets/zzalu_logo_dark.png"
      v-if="isDark==true"
    />
    <h1 class="account-title mb-10">Sign Up</h1>
    <router-view></router-view>
  </div>
</template>

<script> 



export default {
  name: 'SignUpView',
  components: {

  }
}
</script>

<style scoped>

</style>
