<template>
  <!-- <h1 class="account-title mb-10">Sign Up</h1> -->
  <!-- <h1 class="account-title mb-10" v-if="$route.name == 'signup'">Sign Up</h1> -->
  <div class="text-center-container">
    <div class="almost-done">
      <div class="error-sub-title" v-if="$route.name == 'signup'">거의 다 끝났습니다!</div>
      <div class="error-sub-title">이메일로 전송된 인증코드를 입력해주세요.</div>
      <div class="time-remain" >3:00</div>
    </div>
  </div>
  <!-- <div class="flex justify-center">
    <input type="text" class="input-code">
    <input type="text" class="input-code">
    <input type="text" class="input-code">
    <input type="text" class="input-code">
  </div> -->
  <input type="text" placeholder="{{ inputCode }}" v-model="inputCode">
  <h1>{{inputCode}}</h1>
  <!-- <input type="text" v-model="signupUser" placeholder="{{ inputCode }}" > -->
  <button @click="checkCode">입력하기</button>
  <div class="text-center-container">
    <div class="didnt-get-mail">
      <div class="error-content">메일을 받지 못하셨나요?</div>
      <button class="send-again-button">인증 메일 재전송</button>
      
    </div>

      <button class="submit-button" @click="signupFinal">인증하기</button>

  </div>
</template>

<script>
import { useStore} from 'vuex';
import { computed } from '@vue/runtime-core'
export default {
  name: 'InputCodeForm',

  setup() {
    const store = useStore();
    const signupUser = computed(() => (store.state.userStore.temp));
    const asd = computed(() => (store.state.quietChatStore.open_chat_info));
    const signupTempGet = computed(() => (store.getters.signupTempInfoGet))
    const signupFinal = async function () {
      console.log('함수시작', signupUser,'asd',asd)
      console.log(signupTempGet)
      const result = await store.dispatch('userStore/signupFinalAction', signupUser )
    
      // console.log(result.data.authKey)
      if (this.inputCode === signupUser.code) {
        console.log(signupUser)
        console.log(this.inputCode)
        if (result.response.status == 400) {
          alert("야 넌 회원가입 다시해라")
        } else {
          this.$router.push({name: 'complete'})
        }
      } else {
        alert ("님 코드 틀렸음")
      }
    }
    return {
      signupFinal,
      signupUser,
      asd
    }
  },
  data () {
    return {
      inputCode: '',
    }
    
  },
  }

  

</script>

<style>

</style>