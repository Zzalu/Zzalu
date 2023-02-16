const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
<<<<<<< HEAD
    proxy: 'http://i8c109.p.ssafy.io:8080/',
    // proxy: 'http://i8c109.p.ssafy.io:8090/',
=======
    // proxy: 'http://i8c109.p.ssafy.io:8089/',
    proxy: 'http://i8c109.p.ssafy.io:8090/',
>>>>>>> a501a573aaae0abaf052f82b5c22c5624f00bc0d
    // proxy: 'http://localhost:8080/',
  },
});
