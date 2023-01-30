<template>
  <only-go-back-top-nav></only-go-back-top-nav>
  <h1 class="account-title">Settings</h1>
  <div class="mt-6">
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-user" class='settings-icon-aligned-left'/>
      <button class="settings-button">내 정보 수정</button>
    </div >
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-lock" class='settings-icon-aligned-left'/>
      <button class="settings-button">비밀번호 변경</button>
    </div>
    <div class="flex">
      <font-awesome-icon icon="fa-regular fa-moon" class='settings-icon-aligned-left'/>
      <button class="settings-button">다크모드</button>
      <font-awesome-icon icon="fa-solid fa-toggle-off" class='settings-toggle-right'/>
      <div class="settings-right-letter-off">OFF</div>
    </div>
    <!-- <div class="flex">
      <font-awesome-icon icon="fa-regular fa-moon" class='settings-icon-aligned-left'/>
      <button class="settings-button">다크모드</button>
      <font-awesome-icon icon="fa-solid fa-toggle-on" class='settings-toggle-right'/>
      <div class="settings-right-letter-on">ON</div>
    </div> -->
  </div>
  <div class="logout">
    <router-link to="login">로그아웃하기</router-link>
  </div>
  
  <main-bottom-nav></main-bottom-nav>

</template>

<script>
import OnlyGoBackTopNav from "../../components/Common/NavBar/OnlyGoBackTopNav.vue"
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue"
export default {
  name: "UserSettingsView",
  components: {
    OnlyGoBackTopNav,
    MainBottomNav
  }
}
</script>

<style lang="postcss" scoped>
.logout {
  @apply fixed inset-x-0 flex flex-wrap bottom-16 border-t text-zz-light-p font-spoq text-xl pt-3
}
</style>