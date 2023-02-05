import { apiInstance } from './index.js';
const api = apiInstance();

function postSignup(res,err) {
    api.post(`/members/signup`)
    .then(res).catch(err)
}


export { postSignup }