import { apiInstance } from './index.js';
const api = apiInstance();

function getQuiet(res,err) {
    api.get(`/chat/allRooms`)
    .then(res).catch(err)
}
function getGIF(res,err) {
    api.post(`/giphy/main`)
    .then(res).catch(err)
}

export { getQuiet, getGIF }