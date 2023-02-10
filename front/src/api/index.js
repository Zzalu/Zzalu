// local vue api axios instance
import axios from 'axios';
const token = window.localStorage.getItem('token')

function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}

function authApiInstance() {
  console.log(token)
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      'Authorization': `Bearer ${ token }`,
      // 'Authorization': `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnaW8iLCJhdXRoIjoiUk9MRV9VU0VSIiwidXNlcm5hbWUiOiJnaW8iLCJleHAiOjE2NzYwMTAwNzd9.kb6wOjYwAX8lfTN2unJI8j0U-vuQeQZdr-hV1hJE_uo`,
    },
  });
  return instance;
}

export { apiInstance, authApiInstance };
