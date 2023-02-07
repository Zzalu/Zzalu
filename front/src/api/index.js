// local vue api axios instance
import axios from 'axios';

function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjMTA5IiwiYXV0aCI6IlJPTEVfVVNFUiIsInVzZXJuYW1lIjoiYzEwOSIsImV4cCI6MTY3NTgzNDMxOH0.DhJRK3MdvIq5O4boDw_PjeGBBlU785Gnyf_87dzXHV0'
    },
  });
  return instance;
}

function authApiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
    },
  });
  return instance;
}

export { apiInstance, authApiInstance };
