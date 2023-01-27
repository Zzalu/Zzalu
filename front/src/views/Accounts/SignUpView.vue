<template>
  <div>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <h1 class="page-title">Sign Up</h1>
    <input-user-info></input-user-info>
    <!-- <WelcomeToZzalu></WelcomeToZzalu> -->
  </div>
</template>

<script>
import InputUserInfo from '../../components/Accounts/SignUp/InputUserInfo.vue'
// import WelcomeToZzalu from '../../components/Accounts/SignUp/WelcomeToZzalu.vue'

export default {
  name: 'SignUpView',
  components: {
    InputUserInfo,
    // WelcomeToZzalu

  }
}
</script>

<style scoped>

</style>
