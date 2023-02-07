import { apiInstance } from './index.js';
const api = apiInstance();

function getPopularGIF(res,err) {
    api.get(`/gif/main/gifs`)
    .then(res).catch(err)
}
function getRecommendGIF(res,err) {
    api.get(`/gif/user`)
    .then(res).catch(err)
}
function getFirstRandomGIF(res,err) {
    api.post(`/gif/randoms`,{ exceptIds : [] })
    .then(res).catch(err)
}
function getMoreRandomGIF(params,res,err) {
    // console.log(params,'params') [1,2,3,4,5,68,8]
    api.post(`/gif/randoms`,{ exceptIds : params})
    .then(res).catch(err)
}

export { getPopularGIF,getRecommendGIF, getFirstRandomGIF, getMoreRandomGIF }