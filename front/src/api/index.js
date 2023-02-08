// local vue api axios instance
import axios from 'axios';

function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      // 'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjMTA4IiwiYXV0aCI6IlJPTEVfVVNFUiIsInVzZXJuYW1lIjoiYzEwOCIsImV4cCI6MTY3NTg1Mzc0M30.HyHC_XS3lxym4LBoxktKP8Pu9MOtVYZydnJ7bQGI2Sg'
    },
  });
  return instance;
}

function authApiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json;charset=utf-8',
      'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjMTA5IiwiYXV0aCI6IlJPTEVfVVNFUiIsInVzZXJuYW1lIjoiYzEwOSIsImV4cCI6MTY3NTkwODc2OX0.ONtnsMATAnGEFTpgBLOgY6URvNWj45xVedJ47OFO3r0'
    },
  });
  return instance;
}

export { apiInstance, authApiInstance };
