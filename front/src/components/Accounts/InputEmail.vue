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

      <div class="signup-error" v-if="errorMsgs.err.email" >{{ errorMsgs.err.email }}</div>
    <div class="flex float-right mt-10" v-if="$route.name == 'input-signup-email'">
      <button class="go-next-button" @click="[sendEmailCode(), loadingAni()]">다음</button>
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
import Swal from 'sweetalert2'

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
    }

    // 이메일 중복확인 및 코드 요청 보내기
    const sendEmailCode = async function () {
      const validations = new SignupEmailValidations(state.credentials.email);
      const errors = validations.checkValidations();
      if ('email' in errors) {
        errorMsgs.err.email = errors['email']
      } else {
        const result = await store.dispatch('userStore/sendEmailAction', state.credentials.email )
        if (result.status == 400) {
          alert("이미 사용중인 이메일입니다.\n다른 이메일을 입력해주세요.")
        } else if (result.status == 200) {
          const credentialsEmailCode = {
            email: state.credentials.email,
            code: result.data.authKey,
          }
          // 회원가입 등록
          const email_code = await store.dispatch('userStore/signupSecondAction', credentialsEmailCode)
          if (email_code) {
            router.push({name: 'input-signup-code'})
          }
        }
      }
    }

    const loadingAni = () => {
      if (this.errorMsgs.err.email) {
        return true
      } else {
        return false
      }
    }

    // 내가 잊은 아이디를 메일로 보내기
    const sendUsername = async function () {
      const result = await store.dispatch('userStore/sendUsernameAction', state.credentials.email )
      console.log(result)
      if (result.status == 200 ) {
        router.push({name: 'check-email'})
      } else {
        Swal.fire({
          icon: "error",
          text:"해당 이메일에 대한 정보가 없습니다."
          })
      }
    }
    return {
      state,
      errorMsgs,
      sendEmailCode,
      sendUsername,
      onSubmit,
      loadingAni
    }
  },
  data() {
    return {
      // 회원 정보
      email: '',
    };
  },
  }
// }
</script>

<style lang="postcss" scoped>

</style>