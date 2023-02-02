<template>
  <div>
    <only-go-back-top-nav-bar></only-go-back-top-nav-bar>
    <div class="text-center-container ">
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <div class="account-title mb-10">Reset Password</div>
  </div>
      <!-- <input-id-email></input-id-email> -->
      <!-- <input-code-form></input-code-form> -->
      <change-password></change-password>
      <reset-complete></reset-complete>
  </div>
</template>

<script>
import OnlyGoBackTopNavBar from '../../components/Common/NavBar/OnlyGoBackTopNav.vue'
// import InputCodeForm from '../../components/Accounts/InputCodeForm.vue'
// import ChangePassword from '../../components/Accounts/ChangePassword.vue'
import ResetComplete from '../../components/Accounts/ResetPassword/ResetComplete.vue'





export default {
  name: 'ResetPasswordView',
  components: {
    OnlyGoBackTopNavBar,
    // InputCodeForm,
    // ChangePassword,
    ResetComplete,
  }
}
</script>

<style>

</style>