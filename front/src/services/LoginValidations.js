import Validations from './Validations'

export default class LoginValidations {
  constructor(username, password) {
      this.username = username;
      this.password = password;
  }

  // checkValidations() {
  //     let errors = [];
  //     // 아이디
  //     if (!Validations.minLength(this.password, 8) ) {
  //         errors['password'] = '비밀번호는 8자 이상, 20자 이하여야 합니다.'
  //     }

  //     return errors;
  // }
}