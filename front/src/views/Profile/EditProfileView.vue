<template>
  <!-- <modify-profile-top-nav></modify-profile-top-nav> -->
  <div class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center dark:bg-zz-bd">
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon icon="fa-solid fa-chevron-left" class="text-2xl dark:text-white" />
    </span>
    <span class="inline-block px-4 absolute right-0 text-zz-s dark:text-white" @click="saveEditInfo"> 완료 </span>
  </div>
  <div class="text-center-container">
    <!-- <div class="bg-zz-light-p rounded-full h-28 w-28 mt-10"></div> -->
    <img class="profile-image" :style="{ backgroundImage: `url(${this.image})` }" />
    <div>이미지: {{ this.profileImg.name }}</div>
    <div class="flex" for="file_input">
      <div class="flex mt-10 mb-20 items-center justify-center bg-grey-lighter">
        <label
          class="px-4 flex flex-col items-center bg-white dark:bg-gray-500 rounded-lg shadow-lg tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue hover:text-white"
        >
          <input type="file" class="bg-red-200" ref="profilePic" @change="inputImage()" />
          <button @click="profileUploadBtn"></button>
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
    <input type="text" class="account-input" v-model="submit.nickname" v-bind:placeholder="this.my_data.nickname" />
    <button class="button-in-input" @click="uniqueNickname">중복확인</button>
  </div>
  <!-- <div class="signup-error" v-if="errorMsgs.err.nickname" > {{ errorMsgs.err.nickname }} </div> -->
  <div class="my-2">
    <font-awesome-icon icon="fa-solid fa-pen-to-square" class="icon-aligned-left" />
    <input type="text" class="intro-input" v-bind:placeholder="this.my_data.profileMessage" />
  </div>
  <router-link to="/account/delete" class="delete-account">탈퇴하기</router-link>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
// import Swal from 'sweetalert2'
import { useStore } from 'vuex';
import { computed } from '@vue/runtime-core';
// import { reactive } from 'vue'
// import SignupNicknameValidations from '@/services/SignupNicknameValidations'
import MainBottomNav from '../../components/Common/NavBar/MainBottomNav.vue';
export default {
  name: 'EditProfileView',
  components: {
    // ModifyProfileTopNav,
    MainBottomNav,
  },
  setup() {
    const me = localStorage.getItem('id');
    const store = useStore();
    const my_data = computed(() => store.state.profileStore.profile_user);
    // const errorMsgs = reactive({
    //   err: {
    //     nickname: '',
    //   }
    // })
    // 닉 바꾸는지 확인
    /*     watch(() => submit.nickname, (newValue, oldValue) => {
      console.log(newValue,oldValue);
      if (newValue != oldValue) {
        submit.nicknameState = false
        }
    }) */
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
    // const uploadImage = () => {
    //   this.profileImg = this.$refs.profilePic.files
    //   console.log(this.profileImg)
    //   console.log(this.$refs.profilePic.filters)
    // }

    // const profileUploadBtn = () => {

    // }
    return {
      me,
      my_data,
      // uploadImage
      // uniqueNickname
    };
  },
  data() {
    return {
      profileImg: '',
    };
  },
  methods: {
    inputImage() {
      this.profileImg = this.$refs.profilePic.files[0];
      console.log(this.profileImg, '잘 들어왔는지');
      var image = this.$refs.profilePic.files[0];
      console.log('이미지', image);
      const url = URL.createObjectURL(image);
      console.log('유알엘', url);
      this.image = url;
      console.log(url);
      console.log(this.image);
    },
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

.profile-image {
  width: 100px;
  height: 100px;
  background-size: contain;
  /* max-width: 100px;
    max-height: 100px; */
  /* object-fit: cover; */
  @apply rounded-full;
}
</style>
