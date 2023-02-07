import Validations from './Validations'

export default class SignupEmailValidations {
  constructor(email) {
      this.email = email;
  }

  checkValidations() {
      let errors = [];
      // 이메일 네이밍 규칙
      console.log(this.email)
      if (!Validations.emailNaming(this.email) ) {
          errors['email'] = '아이디는 영문, 숫자만 가능하며 2 ~ 12자리까지 가능합니다.'
      }

      return errors;
  }
}