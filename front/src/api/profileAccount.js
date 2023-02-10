import { apiInstance } from './index.js';
const api = apiInstance();



function getProfileStat(params, res, err) {
  console.log("들엉ㅇㅁ",params)
  api.get(`statistics/member?memberId=${params}`)
      .then(res).catch(err)
}


export { getProfileStat }