<template>
  <div>
    <ChatRoomTopNav :room_name="this.$route.query.room_name"
    :room_id="this.$route.query.room_id"
    :like="this.$route.query.like"
    class="z-50" />
    <!-- {{ member_Id }}
      {{ my_member_Id }} -->
    <div class="message-contain">
      <div v-for="message in messages" :key="message">
        <div v-if="message.type != 'ENTER'">
        <!-- 내가 보낸 메세지 -->
          <div v-if="user_nickname == message.sender">
            <!-- 짤 이미지 -->

            <font-awesome-icon
              icon="fa-solid fa-play"
              class="my-message-balloon"
            />
            <div class="my-image-group">
              <span class="my-write-time">{{ message.send_date }}</span>
              <img class="my-image-box" :src="`${message.message}`" alt="" />
            </div>
          </div>
          <!-- ---------------------------------------------------------------------------------- -->
          <!-- 상대방이 보낸 메세지 -->

          <!-- Sender : {{ message.sender }} ProfilePath : {{ message.profilePath }} -->
          <div class="profile-image" v-if="user_nickname != message.sender">
            <!-- 만약 방장이라면
  "  -->    
            <div v-if="message.member_id == member_Id">
              <font-awesome-icon icon="fa-solid fa-crown" class="master-icon" />
              <p class="profile-nickname dark:text-white">{{ message.sender }}</p>
              <font-awesome-icon
                icon="fa-solid fa-play"
                class="message-balloon"
              />
            </div>
            <div v-else>
              <p class="profile-nicknames dark:text-white">{{ message.sender }}</p>
              <font-awesome-icon
                icon="fa-solid fa-play"
                class="message-balloons"
              />
            </div>
          </div>
        </div>

        <!-- 짤 이미지 -->
        <div class="image-contain" v-if="user_nickname != message.sender">
          <div class="image-group">
            <img class="image-box" :src="`${message.message}`" alt="" />
            <!-- 작성 시간  -->
            <span class="write-time">{{ message.send_date }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 서치바 -->

    <div class="navbar-main">
      <div class="navbar-input-box" @click="open_search_modal">
        <font-awesome-icon
          icon="fa-solid fa-magnifying-glass"
          class="navbar-icon"
        />
        <input
          type="text"
          placeholder="짤 검색하기"
          class="navbar-input"
          disabled
        />
      </div>
    </div>

    <!--  -->
    <div class="pb-20"></div>
    <MainBottomNavInChat
      @gif_data="gif_data"
      @gif_data2="gif_data2"
      :search_modal="search_modal"
    />
  </div>
</template>

<script>
import MainBottomNavInChat from "../../components/Common/NavBar/MainBottomNavInChat";
import ChatRoomTopNav from "../../components/Common/NavBar/ChatRoomTopNav";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
// import { findQuiteChatRoom } from '@/api/quietChatList.js'

export default {
  name: "ChatInsideView",

  components: {
    MainBottomNavInChat,
    ChatRoomTopNav,
  },
  setup() {
    const token = localStorage.getItem("token");

    return {
      token,
    };
  },
  created() {
    if (localStorage.getItem("token") == null) {
      this.$router.push({name: 'login-required'})
    }
    // this.findQuiteChatList = findQuiteChatRoom;
    this.room_id = this.$route.query.room_id;
    this.access_token = this.token;
    console.log("token : " + this.token);
    // this.room_id = "71682114-325a-458c-85de-bb007a724546"

    this.socket = new SockJS("http://i8c109.p.ssafy.io:8090" + "/ws-stomp");
    let options = {
      debug: false,
      protocols: Stomp.VERSIONS.supportedProtocols(),
    };
    console.log(this.socket);
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
      gif_id: 0,
      search_modal: false,

      // 방장 확인
      member_Id: this.$route.query.member_Id,
      that_member_Id: localStorage.getItem("profile_id"),

      // 본인 확인
      user_nickname: localStorage.getItem("current_nickname"),
    };
  },

  methods: {
    open_search_modal() {
      this.search_modal = !this.search_modal;
    },
    gif_data(data) {
      this.message = data.gifPath;
      console.log(this.message, this.message);
      console.log("gifId : ", data.id);
      this.gif_id = data.id;

      this.sendMessage();
      // BE에 짤 유즈 메세지 보내기
      // gifId => data.id
      // header에 AccessToken 넣어서 보내야함
      // post 요청

      this.message = "";
    },
    gif_data2(data2) {
      this.message = data2.gifPath;
      console.log("gifId : ", data2.id);
      this.gif_id = data2.id;
      this.sendMessage();
      this.message = "";
      // console.log(data, "여기서데이터받음2");
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
          message: this.message,
          gifId: this.gif_id
        }),
        {}
      );
    },
    reciveMessage(recv) {
      console.log("receive message: " + recv);
      console.log("test", recv);
      let tmp = ""
      let sendtime = ""
      let totalheight = document.body.scrollHeight;
      tmp += recv.sendDate[11]
      tmp += recv.sendDate[12]
      if (Number(tmp) >= 12) {
        sendtime += '오후 '
        sendtime += Number(tmp-12)
      } else {
        sendtime += '오전 '
        sendtime += recv.sendDate[11]
        sendtime += recv.sendDate[12]
      }
      sendtime += ':'
      sendtime += recv.sendDate[14],
      sendtime += recv.sendDate[15],
      this.messages.unshift({
        type: recv.type,
        member_id: recv.memberId,
        sender: recv.sender,
        message: recv.message,
        // send_date: recv.sendDate,
        send_date : sendtime,
        profilePath: recv.profilePath,
      });
      setTimeout(() => {
        window.scrollTo({ top: totalheight, left: 0, behavior: "smooth" });
      }, 100);
    },
    connect() {
      let local_web_stomp = this.web_stomp;
      let local_recive_message = this.reciveMessage;
      let local_reconnect = this.reconnect;
      let local_connect = this.connect;
      let local_socket = this.socket;
      let local_room_id = this.room_id;
      let local_token = this.access_token;

      console.log("local_web_stomp : " + local_web_stomp);
      console.log("local_room_id : " + local_room_id);

      local_web_stomp.connect(
        {},
        function (frame) {
          console.log("frame : " + frame);
          local_web_stomp.subscribe(
            "/sub/chat/room/" + local_room_id,
            function (message) {
              let recv = JSON.parse(message.body);
              local_recive_message(recv);
            }
          );
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
          console.log(error);
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
        }
      );
    },
  },
};
</script>

<style scoped lang="postcss">
/* 내 메세지 */
.my-image-group {
  transform: translateY(-1.3rem);
  @apply flex items-end ml-14 flex-wrap justify-end mb-4 z-20;
}
.my-image-box {
  max-width: 75%;
  min-width: 5%;
  @apply bg-zz-p bg-left bg-contain h-40 bg-no-repeat ml-3 border-2 border-zz-p rounded-2xl;
}
.my-write-time {
  font-size: 0.2rem;
  @apply dark:text-white text-right text-zz-dark-p;
}

.my-message-balloon {
  transform: translateX(0.7rem);
  @apply text-zz-p absolute text-2xl right-0;
}

/* 상대방 메세지 */
.message-contain {
  @apply flex flex-col-reverse relative pt-4 font-spoq pt-8;
}

/* 프로필 */
.profile-image {
  @apply w-12 h-12 rounded-full bg-zz-dark-input flex inset-x-0 text-sm border dark:border-zz-dark-s;
}
.profile-nickname {
  transform: translateY(-1.4rem);
  @apply absolute ml-14 dark:text-white;
}
.profile-nicknames {
  @apply absolute ml-14 dark:text-white;
}

.message-balloon {
  transform: rotate(180deg) translate(-2rem, -2rem);
  @apply text-zz-p text-2xl;
}

.message-balloons {
  transform: rotate(180deg) translate(-2.8rem, -2rem);
  @apply text-zz-p text-2xl;
}
.master-icon {
  transform: translate(3.5rem, -1.2rem);
  @apply text-zz-dark-p text-sm;
}

.image-group {
  transform: translate(0.1rem, -1.5rem);
  @apply flex items-end ml-14 flex-wrap mb-4 z-20;
}
.image-box {
  max-width: 75%;
  min-width: 5%;
  @apply bg-zz-p bg-left bg-contain h-40 bg-no-repeat mr-3 border-2 border-zz-p rounded-2xl;
}
.write-time {
  font-size: 0.2rem;
  @apply dark:text-white text-right text-zz-dark-p;
}

/* 서치바 */

.navbar-main {
  @apply h-nav-height fixed inset-x-0 bottom-14 flex items-center justify-center;
}
.navbar-input-box {
  @apply bg-zz-light-input w-10/12 h-9 flex items-center px-5 py-1 rounded-lg dark:bg-zz-dark-input;
}
.navbar-icon {
  @apply text-zz-darkgray mr-2;
}
.navbar-input {
  @apply bg-transparent text-zz-darkgray w-full dark:text-white font-spoq;
}
</style>