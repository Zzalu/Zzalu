<template>
  <div>
    <ChatRoomTopNav
    :room_name="this.$route.query.room_name"
    />
    <MainBottomNavInChat />
  </div>
</template>

<script>
import MainBottomNavInChat from "../../components/Common/NavBar/MainBottomNavInChat";
import ChatRoomTopNav from "../../components/Common/NavBar/ChatRoomTopNav"
export default {
  name: "ChatInsideView",
  components: {
    MainBottomNavInChat,
    ChatRoomTopNav
  },
};
</script>

<style>
</style>