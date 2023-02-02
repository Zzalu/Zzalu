<template>
  <div>
    <h1>채팅방안</h1>
    <button v-touch:longtap="hi">ㅇㅇㅇㅇㅇㅇㅇ인사봇</button>
    <MainBottomNav />
  </div>
</template>

<script>
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav";
export default {
  name: "ChatInsideView",
  components: {
    MainBottomNav,
  },
  methods: {
    hi() {
      console.log("인사합니다");
    }
  }
};
</script>

<style>
</style>