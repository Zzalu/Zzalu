<template>
  <!-- <h1 class="account-title mb-10">Sign Up</h1> -->
  <!-- <h1 class="account-title mb-10" v-if="$route.name == 'signup'">Sign Up</h1> -->
  <div class="text-center-container">
    <div class="almost-done">
      <div class="error-sub-title" v-if="$route.name == 'signup'">
        거의 다 끝났습니다!
      </div>
      <div class="error-sub-title">
        이메일로 전송된 인증코드를 입력해주세요.
      </div>
      <div class="time-remain">{{min}}:{{sec}}</div>
    </div>
  </div>
  <div class="flex justify-center">
    <input
      type="text"
      id="c1"
      maxlength="1"
      class="input-code"
    />
    <input
      type="text"
      maxlength="1"
      id="c2"
      class="input-code"
    />
    <input
      type="text"
      maxlength="1"
      id="c3"
      class="input-code"
    />
    <input
      type="text"
      maxlength="1"
      id="c4"
      class="input-code"
    />
    <input ref="c1" type="text" maxlength="4" class="input-test" v-model="this.inputCode"/>
  </div>
  
  <div class="text-center-container">
    <div class="didnt-get-mail mb-20">
      <div class="mail-error">메일을 받지 못하셨나요?</div>
      <button class="send-again-button">인증 메일 재전송</button>
      <!-- {{ userInfo.code }} -->
    </div>
    
    <button class="submit-button" @click="signupFinal">인증하기</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import { computed } from '@vue/runtime-core'

export default {
  name: "InputCodeForm",
  computed: mapState({
    userInfo: (state) => state.userStore.temp,
  }),
  mounted() {
    this.startCode();
    this.countDownTimer();
  },
  methods: {
    signupFinal: async function () {
      const code_try = this.inputCode
      // const result = await store.dispatch('userStore/signupFinalAction', this.userInfo )
      // console.log(result.data.authKey)
      if (code_try == this.userInfo.code) {
        console.log(this.userInfo);
        console.log(code_try);
        // 여기까지는 잘 왔음
        const userData = {
          username: this.userInfo.username,
          password: this.userInfo.password,
          passwordConfirmation: this.userInfo.passwordCheck,
          nickname: this.userInfo.nickname,
          userEmail: this.userInfo.email,
        };
        const result = await this.$store.dispatch(
          "userStore/signupFinalAction",
          userData
        );
        console.log(result);
        if (result.status == 400) {
          alert("야 넌 회원가입 다시해라");
        } else {
          this.$router.push({ name: "complete" });
        }
      } else {
        alert("님 코드 틀렸음");
      }
    },

    startCode() {
      this.$refs.c1.focus();
    },
    countDownTimer() {
        setTimeout(() => {
          if (this.sec==0 && this.min == 0) {
            this.$router.push('/input-signup-email')
          } else if (this.sec == 0) {
            this.min -= 1,
            this.sec =59
            this.countDownTimer()
          } else if (this.sec >0 ) {
            this.sec -=1 ;
            this.countDownTimer()
            }
        }, 1000)
    }
  },
  watch: {



  },
  data() {
    return {
      inputCode: '',
      sec: 59,
      min: 4
    };
  },
};
</script>

<style lang="postcss" scoped>
.mail-error {
  word-break: keep-all;
  @apply font-spoq text-xs mt-3;
}

.input-test {
  position: absolute;
  letter-spacing: 3em;
  /* transform: translate(0rem, 0rem); */
  outline: none;
  opacity: 50%;
  width: 300px;
  @apply bg-transparent mt-6 rounded-sm text-lg h-12 pl-11 text-black dark:text-white;
}
</style>
