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
      // 'Authorization': `Bearer ${ token }`,
      'Authorization': `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJnaW8iLCJhdXRoIjoiUk9MRV9VU0VSIiwidXNlcm5hbWUiOiJnaW8iLCJleHAiOjE2NzYxMDE5MjV9.VlK0mu8PfrPxLY1lijbw04gHQFtUUQY6hJibeqa1JPc`,
    },
  });
  return instance;
}

export { apiInstance, authApiInstance };
