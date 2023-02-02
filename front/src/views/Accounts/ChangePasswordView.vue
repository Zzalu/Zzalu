<template>
  <only-go-back-top-nav-bar></only-go-back-top-nav-bar>
  <div class="text-center-container ">
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <div class="account-title mb-10">ChangePassword</div>
  </div>
  <input-current-password></input-current-password>
  <change-password></change-password>
  <reset-complete></reset-complete>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import OnlyGoBackTopNavBar from '../../components/Common/NavBar/OnlyGoBackTopNav.vue'
import InputCurrentPassword from '../../components/Accounts/ChangePassword/InputCurrentPassword.vue'
// import ChangePassword from '../../components/Accounts/ChangePassword.vue'
// import ResetComplete from '../../components/Accounts/ResetPassword/ResetComplete.vue'
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue";

export default {
  name: "ChangePasswordView",
  components: {
    OnlyGoBackTopNavBar,
    InputCurrentPassword,
    // ChangePassword,
    // ResetComplete,
    MainBottomNav,
  },
}
</script>

<style>

</style>