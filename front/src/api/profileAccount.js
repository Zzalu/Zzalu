import { apiInstance } from './index.js';
const api = apiInstance();



function getProfileStats(params, res, err) {
  console.log("들엉ㅇㅁ")
  authapi.get(`statistics/member?memberId=${params}`)
      .then(res).catch(err)
}


export { getProfileStats }