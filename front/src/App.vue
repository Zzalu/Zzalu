<template>
  <!-- <div class="flex flex-col"> -->
  <div>
    <only-go-back-top-nav></only-go-back-top-nav>
    <small-logo-top-nav></small-logo-top-nav>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <only-big-logo-top-nav></only-big-logo-top-nav>
    <kor-go-back-top-nav-bar></kor-go-back-top-nav-bar>
    <board-top-nav></board-top-nav>
    <chat-room-top-nav></chat-room-top-nav>
    <modify-profile-top-nav></modify-profile-top-nav>
    <follow-top-nav></follow-top-nav>
    <chat-room-top-nav></chat-room-top-nav>
    <chat-search-top-nav></chat-search-top-nav>
    <div class="main-margin">
      <div>
        <router-view></router-view>
      </div>
    </div>
    <main-bottom-nav></main-bottom-nav>
    <sign-up-bottom-nav></sign-up-bottom-nav>
  </div>
</template>

<script>
import MainBottomNav from '@/components/Common/NavBar/MainBottomNav.vue';
import OnlyGoBackTopNav from '@/components/Common/NavBar/OnlyGoBackTopNav.vue';
import SignUpBottomNav from './components/Common/NavBar/SignUpBottomNav.vue';
import SmallLogoTopNav from './components/Common/NavBar/SmallLogoTopNav.vue';
import OnlySmallLogoTopNav from './components/Common/NavBar/OnlySmallLogoTopNav.vue';
import OnlyBigLogoTopNav from './components/Common/NavBar/OnlyBigLogoTopNav.vue';
import KorGoBackTopNavBar from './components/Common/NavBar/KorGoBackTopNavBar.vue';
import BoardTopNav from './components/Common/NavBar/BoardTopNav.vue';
import ChatRoomTopNav from './components/Common/NavBar/ChatRoomTopNav.vue';
import ModifyProfileTopNav from './components/Common/NavBar/ModifyProfileTopNav.vue';
import FollowTopNav from './components/Common/NavBar/FollowTopNav.vue';
import ChatSearchTopNav from './components/Common/NavBar/ChatSearchTopNav.vue';

export default {
  name: 'App',
  components: {
    MainBottomNav,
    OnlyGoBackTopNav,
    SignUpBottomNav,
    SmallLogoTopNav,
    OnlySmallLogoTopNav,
    OnlyBigLogoTopNav,
    KorGoBackTopNavBar,
    BoardTopNav,
    ChatRoomTopNav,
    ModifyProfileTopNav,
    FollowTopNav,
    ChatSearchTopNav,
  },
  methods: {},
};
</script>

<style>
.main-margin {
  padding: 0 1.25rem;
  margin-bottom: 3.5rem;
  margin-top: 3.5rem;
}
</style>
