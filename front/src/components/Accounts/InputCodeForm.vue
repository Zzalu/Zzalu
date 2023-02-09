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
  <div class="flex justify-center">
    <input type="text" class="input-code" v-model="inputCode.first">
    <input type="text" class="input-code" v-model="inputCode.second">
    <input type="text" class="input-code" v-model="inputCode.third">
    <input type="text" class="input-code" v-model="inputCode.fourth">
  </div>
  <input type="text" placeholder="{{ inputCode }}" v-model="inputCode">
  <h1>{{inputCode}}</h1>
  <!-- <input type="text" v-model="signupUser" placeholder="{{ inputCode }}" > -->
  <button @click="checkCode">입력하기</button>
  <div class="text-center-container">
    <div class="didnt-get-mail">
      <div class="error-content">메일을 받지 못하셨나요?</div>
      <button class="send-again-button">인증 메일 재전송</button>
      {{ userInfo.code }}
    </div>

      <button class="submit-button" @click="signupFinal">인증하기</button>

  </div>
</template>

<script>
import { mapState } from 'vuex';
// import { computed } from '@vue/runtime-core'
export default {
  name: 'InputCodeForm',
  computed: 
    mapState({
      userInfo: state => state.userStore.temp,
    }),
  methods: {
    signupFinal : async function () {
      const code_try = this.inputCode.first+this.inputCode.second+this.inputCode.third+this.inputCode.fourth
      // const result = await store.dispatch('userStore/signupFinalAction', this.userInfo )
      console.log(code_try);
      console.log(typeof(code_try))
      console.log('------------------')
      console.log(this.userInfo.code);
      console.log(typeof(this.userInfo.code))
      // console.log(result.data.authKey)
      if (code_try == this.userInfo.code) {
        console.log(this.userInfo)
        console.log(code_try)
        // 여기까지는 잘 왔음
        const userData = {
            "username": this.userInfo.username,
            "password": this.userInfo.password,
            "passwordConfirmation": this.userInfo.passwordCheck,
            "nickname": this.userInfo.nickname,
            "userEmail": this.userInfo.email
        }
        const result = await this.$store.dispatch('userStore/signupFinalAction', userData )
        console.log(result);
        if (result.status == 400) {
          alert("야 넌 회원가입 다시해라")
        } else {
          this.$router.push({name: 'complete'})
        }
      } else {
        alert ("님 코드 틀렸음")
      }
    }
  },

  data () {
    return {
      inputCode: {
        first: '',
        second:'',
        third: '',
        fourth:''
      },
    }
    
  },
  }

  

</script>

<style>

</style>