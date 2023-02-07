<template>
  <div>
    <only-go-back-top-nav-bar></only-go-back-top-nav-bar>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <input-email></input-email>
    <!-- <check-email></check-email> -->
  </div>
</template>

<script>
import OnlyGoBackTopNavBar from '../../components/Common/NavBar/OnlyGoBackTopNav.vue'
import InputEmail from "../../components/Accounts/InputEmail.vue"
// import CheckEmail from "../../components/Accounts/FindId/CheckEmail.vue"

export default {
  name: 'FindIdView',
  components: {
    OnlyGoBackTopNavBar,
    InputEmail,
    // CheckEmail,

  }
}
</script>

<style>

</style>