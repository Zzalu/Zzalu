import { authApiInstance } from './index.js';

const authapi = authApiInstance();

function getAllTempGif(params, res, err) {
    authapi.get(`/temp/all`)
        .then(res).catch(err)
}

function putTempGif(params, data, success, fail) {
    authapi.put(`/temp/${data}`)
        .then(success).catch(fail)
}

function deleteTempGif(params, data, res, err) {
    authapi.delete(`/temp/${data}`)
        .then(res).catch(err)
}

function postTempGif(params, formData, res, err) {
    authapi.post(`/temp`, formData)
        .then(res).catch(err)
}

export { getAllTempGif, putTempGif, deleteTempGif, postTempGif }