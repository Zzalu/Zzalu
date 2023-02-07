<template>
  <div>
    <only-go-back-top-nav-bar></only-go-back-top-nav-bar>
    <img
      alt="ZZalu Light logo"
      class="logo"
      src="../../assets/zzalu_logo_light.png"
    />
    <router-view></router-view>
  </div>
</template>

<script>
import OnlyGoBackTopNavBar from '../../components/Common/NavBar/OnlyGoBackTopNav.vue'

export default {
  name: 'FindIdView',
  components: {
    OnlyGoBackTopNavBar,
  }
}
</script>

<style>

</style>