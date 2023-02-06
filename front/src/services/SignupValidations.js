import Validations from './Validations'

export default class SignupValidations {
    constructor(username, nickname, password, passwordCheck, email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.email = email;
    }

    checkValidations() {
        let errors = [];
        // 아이디 네이밍 규칙

        if (!Validations.idNaming(this.username) ) {
            errors['username'] = '아이디는 영문, 숫자만 가능하며 2 ~ 12자리까지 가능합니다.'
        }

        // 닉네임 네이밍 규칙
        if (!Validations.lengthRange(this.nickname, 3, 10) ) {
            errors['username'] = '닉네임은 3자 이상 10자 이하여야합니다.'
        }

        // 비번 네이밍 규칙
        if (!Validations.passwordNaming(this.password) ) {
            errors['password'] = '비밀번호는 영문, 숫자, 특수문자(@$!%*#?&) 포함, 8자 이상 20자 이하여야 합니다.'
        }

        // 비번 둘이 같아야됨
        if (!Validations.passwordSame(this.password, this.passwordCheck) ) {
            errors['passwordCheck'] = '두 비밀번호는 같아야 합니다.'
        }

        // 이메일 네이밍 규칙
        if (!Validations.emailNaming(this.email) ) {
            errors['email'] = '이메일 형식으로 입력해주세요.'
        }

        return errors;
    }
}



