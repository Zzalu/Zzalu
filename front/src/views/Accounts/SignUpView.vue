<template>
  <div>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <h1 class="account-title mb-10">Sign Up</h1>
    <router-view></router-view>
    <!-- <input-user-info></input-user-info> -->
    <!-- <input-email v-if="second"></input-email> -->
    <!-- <input-code-form v-if="three"></input-code-form> -->
    <!-- <welcome-to-zzalu v-if="four"></welcome-to-zzalu> -->


  </div>
</template>

<script> 
// import InputUserInfo from '../../components/Accounts/SignUp/InputUserInfo.vue'
// import InputEmail from '../../components/Accounts/InputEmail.vue'
// import InputCodeForm from '../../components/Accounts/InputCodeForm.vue'
// import WelcomeToZzalu from '../../components/Accounts/SignUp/WelcomeToZzalu.vue'


export default {
  name: 'SignUpView',
  components: {
    // InputUserInfo,
    // InputEmail,
    // InputCodeForm,
    // WelcomeToZzalu
  }
}
</script>

<style scoped>

</style>
