<template>
  <div class="mt-20">
    <h2 class="find-id-input-title" v-if="$route.name == 'find-id'">아이디 찾기</h2>
    <font-awesome-icon icon="fa-solid fa-envelope" class='icon-aligned-left'/>
      <input
        type="text"
        class="account-input"
        placeholder="이메일을 입력하세요"
        v-model="state.credentials.email"
      />
      <div class="error" v-if="errorMsgs.err.email">{{ errorMsgs.err.email }}</div>
    <div class="flex float-right mt-10" v-if="$route.name == 'input-signup-email'">
      <button class="go-next-button" @click="[onSubmit(), sendEmailCode()]">다음</button>
    </div>
    <div class="flex float-right mt-10" v-if="$route.name == 'find-id-input-email'">
      <button class="go-next-button" @click="sendUsername">다음</button>
    </div>
  </div>
  
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { reactive } from 'vue'
import SignupEmailValidations from '../../services/SignupEmailValidations'

export default {
  name: 'InputEmail',
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      credentials: {
        email: '',
        code: ''
      }
    })

    const errorMsgs = reactive({
      err: {
        email: "",
      }
    })
    // 이메일 네이밍규칙
    const onSubmit = () => {
      const validations = new SignupEmailValidations(
        state.credentials.email,
        );
      const errors = validations.checkValidations();
      if ('email' in errors) {
        errorMsgs.err.email = errors['email']
      } else {
        errorMsgs.err.email = null
      }
      console.log(errorMsgs.err.email)
    }

    // 이메일 중복확인 및 코드 요청 보내기
    const sendEmailCode = async function () {
      
      const result = await store.dispatch('userStore/sendEmailAction', state.credentials.email )
      console.log(result.data.authKey)
      console.log(result.status)
      if (result.status == 400) {
        alert("이메일이 중복이에...")
      } else if (result.status == 200) {
        console.log('이메일 중복 아님', state.credentials)
        const credentialsEmailCode = {
          email: state.credentials.email,
          code: result.data.authKey,
        }
        console.log('넘겨줄 변수 정의',credentialsEmailCode)
        // 회원가입 등록
        const email_code = await store.dispatch('userStore/signupSecondAction', credentialsEmailCode)
        console.log(email_code)
        if (email_code) {
          console.log("회원가입 요청 2 잘 갔음")
          router.push({name: 'input-code'})
        }
      }

    }

    // 내가 잊은 아이디를 메일로 보내기
    const sendUsername = async function () {
      const result = await store.dispatch('userStore/sendUsernameAction', state.credentials.email )
      console.log(result)
      if (result.status == 200 ) {
        router.push({name: 'check-email'})
      }
    }
    return {
      state,
      errorMsgs,
      sendEmailCode,
      sendUsername,
      onSubmit
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

  }
}
</script>

<style lang="postcss" scoped>
  .find-id-input-title {
    @apply font-spoq text-lg text-zz-darkgray mt-3 mb-1 mx-1
  }
</style>