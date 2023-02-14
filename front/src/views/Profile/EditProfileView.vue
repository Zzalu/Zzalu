<template>
  <div class="h-nav-height fixed inset-x-0 top-0 bg-white flex items-center justify-center dark:bg-zz-bd">
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon icon="fa-solid fa-chevron-left" class="text-2xl dark:text-white" @click="goSettings"/>
    </span>
    <span class="inline-block px-4 absolute right-0 text-zz-s dark:text-white" @click="saveEditInfo"> 완료 </span>
  </div>
  <div class="text-center-container">
    <img class="profile-image" :style="{ backgroundImage: `url(${this.profileImg})` }" />
    <!-- <div>이미지: {{ this.profileImg }}</div> -->
    <div class="flex" for="file_input">
      <div class="flex mt-10 mb-20 items-center justify-center bg-grey-lighter">
        <label
          class="px-4 flex flex-col items-center bg-white dark:bg-gray-500 rounded-lg shadow-lg tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue hover:text-white"
        >
          <input type="file" ref="profilePic"  @change="inputImage()" name="profile_image" id="profile_image"/>
          <!-- <input type="file" name="profile_image" id="profile_image"/> -->
          <button @click="profileUploadBtn"></button>
          <div class="flex">
            <font-awesome-icon icon="fa-solid fa-image" class="my-auto mr-2 dark:text-white" />
            <span class="text-base leading-normal dark:text-white">사진 수정</span>
          </div>
        </label>
      </div>
    </div>
  </div>
  <div class="my-2">
    <font-awesome-icon icon="fa-solid fa-user" class="icon-aligned-left" />
    <input type="text" class="account-input" v-bind:placeholder="this.me" v-model="state.newNickname" />
    <button class="button-in-input" @click="uniqueNickname">중복확인</button>
  </div>
  <div class="signup-error" v-if="errorMsgs.err.nickname">{{ errorMsgs.err.nickname }}</div>
  <div class="my-2">
    <font-awesome-icon icon="fa-solid fa-pen-to-square" class="icon-aligned-left" />
    <input
      type="text"
      class="account-input"
      v-bind:placeholder="this.my_data.profileMessage"
      v-model="state.profileIntro"
    />
  </div>
  <router-link to="/account/delete" class="delete-account">탈퇴하기</router-link>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import Swal from 'sweetalert2';
import { useStore } from 'vuex';
import { computed } from '@vue/runtime-core';
import { reactive, watch } from 'vue';
import SignupNicknameValidations from '@/services/SignupNicknameValidations';
import MainBottomNav from '../../components/Common/NavBar/MainBottomNav.vue';
var imageFile = document.getElementById("profile_image");

export default {
  name: 'EditProfileView',
  components: {
    MainBottomNav,
  },
  setup() {
    const me = localStorage.getItem('current_nickname');
    const store = useStore();
    const my_data = computed(() => store.state.profileStore.profile_user);
    const errorMsgs = reactive({
      err: {
        nickname: '',
      },
    });
    const state = reactive({
      newNickname: null,
      newNicknameState: true,
      newNicknameError: '',
      profileIntro: null,
      // profileImg: ''
    });
    // const inputImage = function () {
    //   state.profileImg = this.$refs.profilePic.files[0]
    //   console.log(state.profileImg, "잘 들어왔는지")
    //   var image = this.$refs.profilePic.files[0]
    //   console.log('이미지', image)
    //   const url = URL.createObjectURL(image)
    //   console.log('유알엘', url)
    //   this.image = url
    //   state.profileImg = url
    //   console.log(state.profileImg)
    // }
    // 닉 바꾸는지 확인
    watch(() => state.newNickname, (newValue, oldValue) => {
      if (newValue != oldValue) {
        state.newNicknameState = false
        }
    })
    // // 닉네임 중복확인
    const uniqueNickname = async function () {
      // 중복확인 전에 네이밍규칙 확인 ㄱㄱ
      const validations = new SignupNicknameValidations(
        state.newNickname
        );
      const errors = validations.checkValidations();
      
      if ('nickname' in errors) {
        errorMsgs.err.nickname = errors['nickname']
        this.state.newNicknameState = false
      } else {
      const result = await store.dispatch('userStore/uniqueNicknameAction', state.newNickname )
      if (result.data.unique==true) {
        state.newNicknameState = true
        errorMsgs.err.nickname = ''
        Swal.fire({
          icon: "success",
          text:"사용 가능한 닉네임입니다."
          })
      } else {
        state.newNicknameState = false
        Swal.fire({
          icon: "error",
          html:"이미 사용 중인 닉네임입니다. <br>다른 닉네임을 등록해주세요."
          })
      }
      }
    }
    // const uploadImage = () => {
    //   this.profileImg = this.$refs.profilePic.files
    //   console.log(this.profileImg)
    //   console.log(this.$refs.profilePic.filters)
    // }

  const saveEditInfo = function () {
      let changedData = new FormData();
      if (this.state.newNickname) {
        changedData.append("nickname", this.state.newNickname)
      }
      console.log(imageFile,'jjjj')
      if (imageFile != null) {
        changedData.append("profileMultipartFile", imageFile)
      }
      if (this.state.profileIntro) {
        changedData.append("profileMessage", this.state.profileIntro)
      }
      
      for (let key of changedData.keys()) {
        console.log(key, "키값");
      }

  //     // FormData의 value 확인
      for (let value of changedData.values()) {
        console.log(value);
      }
      
      const result = store.dispatch('userStore/patchUserInfoAction', changedData )
      console.log('제발', result)
      // if (result.status==200) {
      //   state.newNicknameState = true
      //   Swal.fire({
      //     icon: "success",
      //     text:"사용 가능한 닉네임입니다."
      //     })
      // } else {
      //   state.newNicknameState = false
      //   Swal.fire({
      //     icon: "error",
      //     html:"이미 사용 중인 닉네임입니다. <br>다른 닉네임을 등록해주세요."
      //     })
      // }

    }
  //   let profile_Image = null;
  //   const test = (a1) => {
  //     profile_Image = a1;
  //   };
    return {
      // profile_Image,
      state,
      errorMsgs,
      me,
      my_data,
      uniqueNickname,
      saveEditInfo,
  //     test,
      // inputImage
    };
  },
  data() {
    return {
      profileImg: '',
      send_image:'',
    }
  },
  methods: {
    inputImage () {
      this.send_image = this.$refs.profilePic.files[0]
      this.profileImg = this.send_image
      const url = URL.createObjectURL(this.send_image)
      this.profileImg = url
    },

    goSettings() {
      this.$router.push({name: "account-settings"})
    }
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
