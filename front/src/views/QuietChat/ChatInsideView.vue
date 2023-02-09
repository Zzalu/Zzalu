<template>
  <div>
    <ChatRoomTopNav
    :room_name="this.$route.query.room_name"
    />
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