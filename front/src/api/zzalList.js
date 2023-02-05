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

export { getPopularGIF,getRecommendGIF }