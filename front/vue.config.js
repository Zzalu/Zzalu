const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    // proxy: 'http://i8c109.p.ssafy.io:8089/',
    proxy: 'http://i8c109.p.ssafy.io:8090/',
    // proxy: 'http://localhost:8080/',
  },
});
