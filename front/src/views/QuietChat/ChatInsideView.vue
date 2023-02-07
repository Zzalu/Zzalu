<template>
  <div>
    <ChatRoomTopNav
    :room_name="this.$route.query.room_name"
    />
    <h1>{{ this.$route.query.name }}</h1>
    <MainBottomNav />
  </div>
</template>

<script>
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav";
import ChatRoomTopNav from "../../components/Common/NavBar/ChatRoomTopNav"
export default {
  name: "ChatInsideView",
  components: {
    MainBottomNav,
    ChatRoomTopNav
  },
};
</script>

<style>
</style>