<template>
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
          <input type="file" ref="profilePic" class="hidden" @change="inputImage()" />
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
export default {
  name: 'EditProfileView',
  components: {
    // ModifyProfileTopNav,
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
    // watch(() => submit.nickname, (newValue, oldValue) => {
    //   console.log(newValue,oldValue);
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
    // const uploadImage = () => {
    //   this.profileImg = this.$refs.profilePic.files
    //   console.log(this.profileImg)
    //   console.log(this.$refs.profilePic.filters)
    // }

      if ('nickname' in errors) {
        errorMsgs.err.nickname = errors['nickname'];
        this.state.newNicknameState = false;
      } else {
        const result = await store.dispatch('userStore/uniqueNicknameAction', state.newNickname);
        if (result.data.unique == true) {
          state.newNicknameState = true;
          Swal.fire({
            icon: 'success',
            text: '사용 가능한 닉네임입니다.',
          });
        } else {
          state.newNicknameState = false;
          Swal.fire({
            icon: 'error',
            html: '이미 사용 중인 닉네임입니다. <br>다른 닉네임을 등록해주세요.',
          });
        }
      }
    };

    const saveEditInfo = async function () {
      let changedData = new FormData();
      changedData.append("nickname", this.state.newNickname)
      console.log('이미지... 넣고싶어요', )
      changedData.append("profileMultipartFile", this.send_image)
      changedData.append("profileMessage", this.state.profileIntro)
      console.log(changedData.entries, '폼데이터')
      for (let key of changedData.keys()) {
        console.log(key);
      }

      // FormData의 value 확인
      for (let value of changedData.values()) {
        console.log(value);
      }
      const result = await store.dispatch('userStore/patchUserInfoAction', changedData )
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
    let profile_Image = null;
    const test = (a1) => {
      profile_Image = a1;
    };
    return {
      profile_Image,
      state,
      errorMsgs,
      me,
      my_data,
      uniqueNickname,
      saveEditInfo,
      test,
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
      console.log(this.profileImg, "잘 들어왔는지")
      var image = this.$refs.profilePic.files[0]
      

      console.log('이미지2', this.send_image)
      const url = URL.createObjectURL(image)
      console.log('유알엘', url)
      this.image = url
      this.profileImg = url
      console.log('this',this.profileImg)

      console.log('send_image',this.send_image)

      // test
      let asd = url;
      this.test(asd);

      console.log(this.profileImg, '얘는찍힘?');
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
