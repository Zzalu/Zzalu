<template>
  <!-- <modify-profile-top-nav></modify-profile-top-nav> -->
  <div
    class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center dark:bg-zz-bd"
  >
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon
        icon="fa-solid fa-chevron-left"
        class="text-2xl dark:text-white"
      />
    </span>
    <span
      class="inline-block px-4 absolute right-0 text-zz-s dark:text-white"
      @click="saveEditInfo"
    >
      완료
    </span>
  </div>
  <div class="text-center-container">
    <!-- <div class="bg-zz-light-p rounded-full h-28 w-28 mt-10"></div> -->
    <img class="bg-zz-light-p rounded-full h-28 w-28 mt-10"
      src="@/assets/happy_celebrate.gif"
    >
    <div class="flex" for="file_input">
      <div
        class="flex mt-10 mb-20 items-center justify-center bg-grey-lighter"
      >
        <label
          class="px-4 flex flex-col items-center bg-white dark:bg-gray-500 rounded-lg shadow-lg tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue hover:text-white"
        >
        <div class="flex">
          <font-awesome-icon icon="fa-solid fa-image" class="my-auto mr-2 dark:text-white" />
          <span class="text-base leading-normal dark:text-white">사진 수정</span>
          <input type="file" class="hidden" />
          </div>
        </label>
      </div>
    </div>
  </div>
  <div class="my-2">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      v-bind:placeholder="this.profile_user_data.username"
    />
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
      v-bind:placeholder="this.profile_user_data.profileMessage"
    />
  </div>
  <div class="delete-account">탈퇴하기</div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
// import ModifyProfileTopNav from "../../components/Common/NavBar/ModifyProfileTopNav.vue";
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue";
export default {
  name: "EditProfileView",
  components: {
    // ModifyProfileTopNav,
    MainBottomNav,
  },
  setup() {
    const store = useStore();
    const profile_user_data = computed(
      () => store.state.userStore.profile_user
    );

    const saveEditInfo = {};
    return {
      profile_user_data,
      saveEditInfo,
    };
  },
};
</script>

<style lang="postcss" scoped>
.intro-input {
  @apply bg-zz-light-input rounded-sm w-full h-10 px-8 text-sm truncate dark:bg-zz-dark-input;
}

.delete-account {
  @apply fixed inset-x-0 flex flex-wrap justify-end bottom-14 text-zz-error font-spoq text-xs pr-3;
}
</style>
