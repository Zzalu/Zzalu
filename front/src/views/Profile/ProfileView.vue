<template>
  <div class="h-screen">
    <my-profile-top-nav></my-profile-top-nav>
    <profile-info></profile-info>
    <user-stats></user-stats>
    <user-award></user-award>
    <user-board></user-board>
    <div class="h-6"></div>
    <main-bottom-nav></main-bottom-nav>
  </div>
</template>

<script>
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue";
import MyProfileTopNav from "../../components/Common/NavBar/MyProfileTopNav.vue";
import ProfileInfo from "../../components/Profile/ProfileInfo.vue";
import UserStats from "../../components/Profile/UserStats.vue";
import UserAward from "../../components/Profile/UserAward.vue";
import UserBoard from "../../components/Profile/UserBoard.vue";

export default {
  name: "ProfileView",
  components: {
    MainBottomNav,
    MyProfileTopNav,
    ProfileInfo,
    UserStats,
    UserAward,
    UserBoard,
  },
};
</script>

<style>
</style>
