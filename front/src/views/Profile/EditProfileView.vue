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
      @change = 'uploadImage()'
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
      v-bind:placeholder="this.my_data.nickname"
    />
    <button class="button-in-input" @click="uniqueNickname" >중복확인</button>
  </div>
    <!-- <div class="signup-error" v-if="errorMsgs.err.nickname" > {{ errorMsgs.err.nickname }} </div> -->
  <div class="my-2">
    <font-awesome-icon
      icon="fa-solid fa-pen-to-square"
      class="icon-aligned-left"
    />
    <input
      type="text"
      class="intro-input"
      v-bind:placeholder="this.my_data.profileMessage"
    />
  </div>
  <router-link to="/account/delete" class="delete-account">탈퇴하기</router-link>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
// import Swal from 'sweetalert2'
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
// import { reactive,watch } from 'vue'
// import SignupNicknameValidations from '@/services/SignupNicknameValidations'
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue";
export default {
  name: "EditProfileView",
  components: {
    // ModifyProfileTopNav,
    MainBottomNav,
  },
  setup() {
    const me = localStorage.getItem("id");
    const store = useStore();
    const my_data= computed(
      () => store.state.profileStore.profile_user
    );
    // const submit = reactive({
    //   profile_msg:'',
    //   nickname: '',
    //   nicknameState: false,
    // })
    // const errorMsgs = reactive({
    //   err: {
    //     nickname: '',
    //   }
    // })
    // 닉 바꾸는지 확인
    // watch(() => submit.nickname, (newValue, oldValue) => {
    //   if (newValue != oldValue) {
    //     submit.nicknameState = false
    //     }
      
    // })
    // // 닉네임 중복확인
    // const uniqueNickname = async function () {
    //   // 중복확인 전에 네이밍규칙 확인 ㄱㄱ
    //   const validations = new SignupNicknameValidations(
    //     submit.nickname
    //     );
    //   const errors = validations.checkValidations();
      
    //   if ('nickname' in errors) {
    //     errorMsgs.err.nickname = errors['nickname']
    //     this.submit.nicknameState = false
    //   } else {
      // console.log(submit.nickname)
      // const result = store.dispatch('userStore/uniqueNicknameAction', submit.nickname )
      // if (result.data.unique==true) {
      //   submit.nicknameState = true
      //   Swal.fire({
      //     icon: "success",
      //     text:"사용 가능한 닉네임입니다."
      //     })
      // } else {
      //   submit.nicknameState = false
      //   Swal.fire({
      //     icon: "error",
      //     html:"이미 사용 중인 닉네임입니다. <br>다른 닉네임을 등록해주세요."
      //     })
      // }
      // }
    // }
    return {
      // submit,
      me,
      my_data,
      // uniqueNickname
    };


  },
  methods: {

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
