const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: 'http://i8c109.p.ssafy.io:8080/',
    // proxy: 'http://172.30.1.12:3000/',
  }
})