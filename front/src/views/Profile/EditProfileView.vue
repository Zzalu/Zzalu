<template>
  <modify-profile-top-nav></modify-profile-top-nav>
  <div class="text-center-container">
    <div class="bg-zz-light-p rounded-full h-28 w-28 mt-10"></div>
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-image" class="mt-4 mx-2" />
      <div class="mt-3 mb-10">사진 수정</div>
    </div>
  </div>
  <div class="my-2">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input type="text" class="account-input" placeholder="지금 내 닉" />
    <button class="button-in-input">중복확인</button>
  </div>
  <div class="my-2">
    <font-awesome-icon
      icon="fa-solid fa-pen-to-square"
      class="icon-aligned-left"
    />
    <input
      type="text"
      class="intro-input"
      placeholder="지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?지금내소개글이완전길다면?"
    />
  </div>
  <div class="delete-account">탈퇴하기</div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import ModifyProfileTopNav from "../../components/Common/NavBar/ModifyProfileTopNav.vue";
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue";
export default {
  name: "EditProfileView",
  components: {
    ModifyProfileTopNav,
    MainBottomNav,
  },
};
</script>

<style lang="postcss" scoped>
.intro-input {
  @apply bg-zz-light-input rounded-sm w-full h-10 px-8 text-sm truncate;
}

.delete-account {
  @apply fixed inset-x-0 flex flex-wrap justify-end bottom-14 text-zz-error font-spoq text-xs pr-3
}
</style>