export default class Validations {

  static lengthRange(name, minLength, maxLength) {
    if (name.length < minLength && name.length > maxLength) {
      return false;
    }
    return true;
  }

  static idNaming(name) {
    const alphanumeric = /^[0-9a-zA-Z].{2,12}$/;
    if (!name.match(alphanumeric)) {
      return false;
    }
    return true
  }


  static passwordNaming(name) {
    const pwdRule = /^(?=.*\d)(?=.*[a-z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,20}$/
    if (!name.match(pwdRule)) {
      return false;
    }
    return true
  }

  static passwordSame(name1, name2) {
    if (name1 != name2) {
      return false;
    }
    return true
  }


}
