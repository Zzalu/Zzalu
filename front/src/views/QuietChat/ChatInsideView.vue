<template>
  <div>
    <ChatRoomTopNav
    :room_name="this.$route.query.room_name"
    />
    <div>
      <div>
        <label>내용</label>
      </div>
      <input
        type="text"
        v-model="message"
        v-on:keypress.enter="sendMessage"
      />
      <div>
        <button @click="sendMessage">보내기</button>
      </div>
    </div>
    <ul>
      <li v-for="message in messages" :key="message">
        {{ message.sender }} - {{ message.message }}
      </li>
    </ul>
    <MainBottomNavInChat />
  </div>
</template>

<script>
import MainBottomNavInChat from "../../components/Common/NavBar/MainBottomNavInChat";
import ChatRoomTopNav from "../../components/Common/NavBar/ChatRoomTopNav"
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';
import {findQuiteChatRoom} from '@/api/quietChatList.js' 

export default {
  name: "ChatInsideView",
  
  components: {
    MainBottomNavInChat,
    ChatRoomTopNav
  },
  setup() {

  },
  created() {
    this.findQuiteChatList = findQuiteChatRoom;
    this.room_id = this.$route.query.room_id;
    // this.room_id = "71682114-325a-458c-85de-bb007a724546"
    
    this.socket = new SockJS("http://i8c109.p.ssafy.io:8090" + "/ws-stomp")
    let options = {debug: false, protocols: Stomp.VERSIONS.supportedProtocols()};
    console.log(this.socket)
    this.web_stomp = Stomp.over(this.socket, options);

    this.reconnect = 0;
    this.connect();
    console.log("created_end");
  },
  data() {
    return {
        findQuiteChatList : "",
        room_id : "",
        access_token : "",
        test_tenmporal_member_id : 1,
        socket : "",
        web_stomp : "",
        reconnect : 0,
        message : "",
        messages : [],
    }
  },
  methods: {
    findRoom() {
      this.findQuiteChatList(this.room_id);
    }, 
    sendMessage() {
      this.web_stomp.send(
        "/pub/chat/message",
        JSON.stringify({
          type: "TALK",
          roomId: this.room_id,
          sender: this.test_tenmporal_member_id,
          message: this.message
        }),
        {}
      )
    },
    reciveMessage(recv) {
      this.messages.unshift({
        type: recv.type,
        sender: recv.type == "ENTER" ? "[알림]" : recv.sender,
        message: recv.message,
      });
    },
    connect() {
      let local_web_stomp =  this.web_stomp
      let local_recive_message = this.reciveMessage
      let local_reconnect = this.reconnect
      let local_connect = this.connect
      let local_socket = this.socket
      let local_room_id = this.room_id
      let local_sender = this.test_tenmporal_member_id

      console.log("local_web_stomp : " + local_web_stomp)
      console.log("local_room_id : " + local_room_id)

      local_web_stomp.connect({}, function(frame) {
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
            sender: local_sender,
          }),
          {}
        );
      },
      function(error) {
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
</style>