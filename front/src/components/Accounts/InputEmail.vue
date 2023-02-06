<template>
  <h1 class="account-title mb-10" v-if="$route.name == 'signup'">Sign Up</h1>
  <div class="mt-20">
    <h2 class="find-id-input-title" v-if="$route.name == 'find-id'">아이디 찾기</h2>
    <font-awesome-icon icon="fa-solid fa-envelope" class='icon-aligned-left'/>
      <input
        type="text"
        class="account-input"
        placeholder="이메일을 입력하세요"
        v-model="state.credentials.email"
      />
      <div class="error" v-if="errors.email">{{ errors.email }}</div>
    <div class="flex float-right mt-10">
      <button class="go-next-button" @click="sendEmailCode">다음</button>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { reactive } from 'vue'
import SignupValidations from '../../services/SignupValidations'

export default {
  name: 'InputEmail',
  setup() {
    const store = useStore();
    const state = reactive({
      credentials: {
        email: ''
      }
    })
    // 이메일 중복확인 및 코드 요청 보내기
    const sendEmailCode = async function () {
      console.log('sendEmailCode에는 잘 들어옴')
      const result = await store.dispatch('userStore/sendEmailAction', state.credentials.email )
      if (result.data.status == 400) {
        console.log('중복이군여')
        alert("이메일이 중복이에...")
      } else {
        console.log('이메일 중복 아님')
        const credentialsEmailCode = {
          email: state.credentials.email,
          code: state.credentials.code
        }
        console.log('넘겨줄 변수 정의')
      // 회원가입 등록
      this.$store.dispatch('userStore/signupSecondAction', credentialsEmailCode)
      console.log("회원가입 요청 2 잘 갔음")
      this.$router.push({name: 'input-code'})
      }

    }
    return {
      state,
      sendEmailCode
    }
  },
  data() {
    return {
      // 회원 정보
      email: '',
      // 에러
      errors: [],
    };
  },

  methods: {
    onSubmit() {
      const validations = new SignupValidations(
        this.email
        );
      this.errors = validations.checkValidations();
      console.log('onSubmit 안에는 잘 들어옴')
      if ('email' in this.errors) {
        return false;
      }
    }
  }
}
</script>

<style lang="postcss" scoped>
  .find-id-input-title {
    @apply font-spoq text-lg text-zz-darkgray mt-3 mb-1 mx-1
  }
</style>