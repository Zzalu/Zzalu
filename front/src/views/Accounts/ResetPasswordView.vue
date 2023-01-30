<template>
  <div>
    <only-go-back-top-nav-bar></only-go-back-top-nav-bar>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <input-two-passcode></input-two-passcode>
  </div>
</template>

<script>
import OnlyGoBackTopNavBar from '../../components/Common/NavBar/OnlyGoBackTopNav.vue'
import InputTwoPasscode from '../../components/Accounts/ResetPassword/InputTwoPasscode.vue'
// import InputEmail from '../../components/Accounts/InputEmail.vue'
// import InputCodeForm from '../../components/Accounts/InputCodeForm.vue'
// import WelcomeToZzalu from '../../components/Accounts/SignUp/WelcomeToZzalu.vue'


export default {
  name: 'ResetPasswordView',
  components: {
    OnlyGoBackTopNavBar,
    InputTwoPasscode,
  }
}
</script>

<style>

</style>