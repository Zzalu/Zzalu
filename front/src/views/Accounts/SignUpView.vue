<template>
  <div>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <!-- <input-user-info></input-user-info> -->
    <!-- <input-email></input-email> -->
    <input-code-form></input-code-form>
    <!-- <welcome-to-zzalu></welcome-to-zzalu> -->


  </div>
</template>

<script>
// import InputUserInfo from '../../components/Accounts/SignUp/InputUserInfo.vue'
// import InputEmail from '../../components/Accounts/InputEmail.vue'
import InputCodeForm from '../../components/Accounts/InputCodeForm.vue'
// import WelcomeToZzalu from '../../components/Accounts/SignUp/WelcomeToZzalu.vue'


export default {
  name: 'SignUpView',
  components: {
    // InputUserInfo,
    // InputEmail,
    InputCodeForm,
    // WelcomeToZzalu
  }
}
</script>

<style scoped>

</style>
