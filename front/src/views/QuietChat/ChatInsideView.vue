<template>
  <div>
    <ChatRoomTopNav
    :room_name="this.$route.query.room_name"
    />
    <MainBottomNavInChat />
  </div>
</template>

<script>
// import MainBottomNav from "../../components/Common/NavBar/MainBottomNav";
// import ChatRoomTopNav from "../../components/Common/NavBar/ChatRoomTopNav"
// import Stomp from 'webstomp-client';
// import SockJS from 'sockjs-client';
// import {findQuiteChatRoom} from '@/api/quietChatList.js' 

// export default {
//   name: "ChatInsideView",
//   setup() {

//   },
//   created() {
//     this.findQuiteChatList = findQuiteChatRoom;
//     this.room_id = this.$route.params.room_id;
    
//     this.socket = new SockJS("/ws-stomp")
//     this.web_stomp = Stomp.over(this.socket);

//     this.reconnect = 0;
//     this.connect();
//     console.log("created_end");
//   },
//   data() {
//     return {
//         findQuiteChatList : "",
//         room_id : "71682114-325a-458c-85de-bb007a724546",
//         access_token : "",
//         test_tenmporal_member_id : 1,
//         socket : "",
//         web_stomp : "",
//         reconnect : 0,
//     }
//   },
//   methods: {
//     findRoom() {
//       this.findQuiteChatList(this.room_id);
//     }, 
//     connect() {
//       let local_web_stomp =  this.web_stomp
//       let local_recive_message = this.receive_message
//       let local_reconnect = this.reconnect
//       let local_connect = this.connect
//       let local_socket = this.socket
//       let local_room_id = this.room_id
//       let local_sender = this.test_tenmporal_member_id

//       local_web_stomp.connect({}, function(frame) {
//         console.log(frame);
//         local_web_stomp.subscribe("/sub/chat/room/" + local_room_id, function (message) {
//           let recv = JSON.parse(message.body)
//           local_recive_message(recv)
//         });
//         local_web_stomp.sned(
//           "/pub/chat/message",
//           JSON.stringify({
//             type: "ENTER",
//             roomId: local_room_id,
//             sender: local_sender,
//           }),
//           {}
//         );
//       },
//       function(error) {
//         console.log(error)
//         if(local_reconnect++ <= 5) {
//           setTimeout(function() {
//             local_socket = new SockJS("/ws-stomp");
//             local_web_stomp = Stomp.over(local_socket);
//             local_connect();
//           }, 10 * 1000);
//         }
//       })
//     }
//   },
//   components: {
//     MainBottomNav,
//     ChatRoomTopNav
//   },
// };
</script>

<style>
</style>