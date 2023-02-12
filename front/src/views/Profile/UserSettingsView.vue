<template>
  <only-go-back-top-nav></only-go-back-top-nav>
  <h1 class="account-title">Settings</h1>
  <div class="mt-6 dark:bg-zz-bd">
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-user" class='settings-icon-aligned-left'/>
      <button class="settings-button" @click="goProfileEdit">
        <div class="text-zz-light-p dark:text-white">내 정보 수정</div>
          
      </button>
    </div >
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-lock" class='settings-icon-aligned-left'/>
      <button class="settings-button" @click="goChangePwd">
        <div class="text-zz-light-p dark:text-white">비밀번호 변경</div>
        </button>
    </div>
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-crown" class='settings-icon-crown'/>
      <button class="settings-button" @click="managerApply">
        <div class="text-zz-light-p dark:text-white">매니저 권한 신청하기</div>
        </button>
    </div>
    <div class="flex" @click="toggleDark()" v-if="isDark==true">
      <font-awesome-icon icon="fa-regular fa-moon" class='settings-icon-aligned-left'/>
      <button class="settings-button">
        <div class="text-zz-light-p dark:text-white">다크모드</div>
        </button>
      <font-awesome-icon icon="fa-solid fa-toggle-off" class='settings-toggle-right'/>
      <div class="settings-right-letter-off">OFF</div>
    </div>
    <div class="flex" @click="toggleDark()" v-if="isDark==false">
      <font-awesome-icon icon="fa-regular fa-moon" class='settings-icon-aligned-left'/>
      <button class="settings-button">다크모드</button>
      <font-awesome-icon icon="fa-solid fa-toggle-on" class='settings-toggle-right'/>
      <div class="settings-right-letter-on">ON</div>
    </div>
  </div>

  <div class="logout">
    <button @click="logoutSubmit">로그아웃하기</button>
  </div>
  
  <main-bottom-nav></main-bottom-nav>

</template>

<script>
import OnlyGoBackTopNav from "../../components/Common/NavBar/OnlyGoBackTopNav.vue"
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue"
import { useDark, useToggle } from "@vueuse/core";

const isDark = useDark();
import { useStore } from "vuex";
import { useRouter } from 'vue-router';
const toggleDark = useToggle(isDark);

export default {
  name: "UserSettingsView",
  components: {
    OnlyGoBackTopNav,
    MainBottomNav
  },
  data() {
    return {
      isDark,
      toggleDark,
    };
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const logoutSubmit = async function() {
      await store.dispatch('userStore/logoutAction')
      router.push({name: 'main'})
    }
    const goProfileEdit = () => {
      router.push({name: 'edit-profile'})
    }
    const goChangePwd = () => {
      router.push({name: 'change-password'})
    }
    const managerApply = () => {
      store.dispatch('userStore/managerApplyAction')
    }
    return {
      logoutSubmit,goProfileEdit,managerApply,goChangePwd
    }
  }
}
</script>

<style lang="postcss" scoped>
.logout {
  @apply fixed inset-x-0 flex flex-wrap bottom-16 border-t text-zz-light-p font-spoq text-xl pt-3 px-4 dark:text-zz-light-s
}

.settings-icon-crown {
  @apply absolute mx-4 my-5 h-8 text-zz-light-p dark:text-white
}
</style>