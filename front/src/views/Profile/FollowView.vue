<template>
  <follow-top-nav></follow-top-nav>
  <div class="grid grid-cols-2 text-center">
    <div class="py-2 border-b-2">팔로워</div>
    <div class="py-2 border-b-2">팔로잉</div>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import FollowTopNav from "../../components/Common/NavBar/FollowTopNav.vue"
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue"

export default {
  name: "FollowView",
  components: {
    FollowTopNav,
    MainBottomNav
  }
}
</script>

<style>

</style>