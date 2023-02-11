<template>
  <div>
    <ChatRoomTopNav :room_name="this.$route.query.room_name" />
    <ul>
      <li v-for="message in messages" :key="message">
        Sender : {{ message.sender }}
        ProfilePath : {{ message.profilePath }}
        <div class="w-40 h-40">
          <div 
          class="w-full h-full bg-center bg-cover"
          :style="`background-image:url(${ message.message})`"></div>
        </div>
      </li>
    </ul>
    <MainBottomNavInChat @gif_data="gif_data" @gif_data2="gif_data2" />
  </div>
</template>

<script>
import MainBottomNavInChat from "../../components/Common/NavBar/MainBottomNavInChat";
import ChatRoomTopNav from "../../components/Common/NavBar/ChatRoomTopNav"
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';
// import { findQuiteChatRoom } from '@/api/quietChatList.js'

export default {
  name: "ChatInsideView",

  components: {
    MainBottomNavInChat,
    ChatRoomTopNav
  },
  setup() {
    const token = localStorage.getItem("token");

    return {
      token
    }
  },
  created() {
    // this.findQuiteChatList = findQuiteChatRoom;
    this.room_id = this.$route.query.room_id;
    this.access_token = this.token;
    console.log("token : " + this.token)
    // this.room_id = "71682114-325a-458c-85de-bb007a724546"

    this.socket = new SockJS("http://i8c109.p.ssafy.io:8080" + "/ws-stomp")
    let options = { debug: false, protocols: Stomp.VERSIONS.supportedProtocols() };
    console.log(this.socket)
    this.web_stomp = Stomp.over(this.socket, options);

    this.reconnect = 0;
    this.connect();
    console.log("created_end");
  },
  data() {
    return {
      findQuiteChatList: "",
      room_id: "",
      access_token: "",
      test_tenmporal_member_id: 1,
      socket: "",
      web_stomp: "",
      reconnect: 0,
      message: "",
      messages: [],
    }
  },
  methods: {
    gif_data(data) {
      this.message = data.gifPath;
      console.log(this.message, this.message);
      console.log(data, '여기서데이터받음');
      this.sendMessage();
      // BE에 짤 유즈 메세지 보내기
      // gifId => data.id 
      // header에 AccessToken 넣어서 보내야함
      // post 요청

      this.message = "";
    },
    gif_data2(data) {
      this.message = data.gifPath;
      this.sendMessage();
      this.message = "";
      console.log(data, '여기서데이터받음2');
    },
    findRoom() {
      this.findQuiteChatList(this.room_id);
    },
    sendMessage() {
      this.web_stomp.send(
        "/pub/chat/message",
        JSON.stringify({
          type: "TALK",
          roomId: this.room_id,
          sender: this.access_token,
          message: this.message
        }),
        {}
      )
    },
    reciveMessage(recv) {
      console.log("receive message: " + recv)
      console.log(recv)
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == "ENTER" ? "[알림]" : recv.sender,
        message: recv.message,
        profilePath : recv.profilePath,
      });
    },
    connect() {
      let local_web_stomp = this.web_stomp
      let local_recive_message = this.reciveMessage
      let local_reconnect = this.reconnect
      let local_connect = this.connect
      let local_socket = this.socket
      let local_room_id = this.room_id
      let local_token = this.access_token

      console.log("local_web_stomp : " + local_web_stomp)
      console.log("local_room_id : " + local_room_id)

      local_web_stomp.connect({}, function (frame) {
        console.log("frame : " + frame);
        local_web_stomp.subscribe("/sub/chat/room/" + local_room_id, function (message) {
          let recv = JSON.parse(message.body)
          local_recive_message(recv)
        });
        local_web_stomp.send(
          "/pub/chat/message",
          JSON.stringify({
            type: "ENTER",
            roomId: local_room_id,
            sender: local_token,
          }),
          {}
        );
      },
        function (error) {
          console.log(error)
          console.log(local_reconnect);
          console.log(local_connect);
          console.log(local_socket);
          // if(local_reconnect++ <= 5) {
          //   setTimeout(function() {
          //     local_socket = new SockJS("/ws-stomp");
          //     local_web_stomp = Stomp.over(local_socket);
          //     local_connect();
          //   }, 10 * 1000);
          // }
        })
    }
  },
};
</script>

<style>
.select-jjal-img {
  filter: opacity(0.5) drop-shadow(0 0 0 rgb(0, 0, 0));
  @apply h-full w-full rounded-2xl bg-cover;
}
</style>