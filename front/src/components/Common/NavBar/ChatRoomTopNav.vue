<template>
  <!-- 채팅방 안 nav-->
  <div
    class="
      h-nav-height
      fixed
      inset-x-0
      top-0
      bg-white
      flex
      items-center
      justify-center
      dark:bg-zz-bd
    "
  >
    <span class="font-bold text-xl dark:text-white line-clamp-1">
      {{ room_name }} 고독방
    </span>
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon
        icon="fa-solid fa-chevron-left"
        class="text-2xl dark:text-white"
      />
    </span>
    <span class="inline-block px-4 absolute right-0">
      <div class="loader">
        <div class="inner">
          <font-awesome-icon
            v-if="is_like"
            icon="fa-solid fa-heart"
            class="text-xl text-zz-s dark:text-zz-dark-s"
            id="vibration"
            @click="chagnelike"
          />
          <font-awesome-icon
            v-else
            icon="fa-regular fa-heart"
            class="text-xl text-zz-s dark:text-zz-dark-s"
            id="vibration"
            @click="changelike"
          />
        </div>
      </div>
    </span>
  </div>
</template>

<script>
export default {
  name: "ChatRoomTopNav",
  props: {
    room_name: String,
  },
  data() {
    return {
      is_like: false,
    };
  },
  methods: {
    changelike() {
      this.is_like = !this.is_like;
    },
    vibration(target) {
      target.classList.add("vibration");
      setTimeout(function () {
        target.classList.remove("vibration");
      }, 400);
    },
  },
};
</script>

<style scoped lang="postcss">
/* @import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");

@keyframes vibration {
  from {
    transform: rotate(1deg);
  }
  to {
    transform: rotate(-1deg);
  }
}
.vibration {
  animation: vibration .1s infinite; */
/* } */

body {
  margin: 0;
  padding: 0;
}

.loader {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  background: #000;
}

.loader .inner {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 80px;
  text-align: center;
}

.loader h1 {
  height: 160px;
  margin: 0;
  padding: 0;
  font-size: 66px;
  color: rgba(255, 255, 255, 0.2);
  background-size: 101% 100%;
  background-position: left 0px bottom -40px;
  background-repeat: repeat-x;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  background-clip: text;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 90 240 300' preserveAspectRatio='none'%3E%3Crect x='0' y='0' width='500' height='800' style='stroke: none; fill: rgb(255,255,255);' /%3E%3Cpath d='M0,100 C150,200 350,0 500,100 L500,00 L0,0 Z' style='stroke: none;'%3E%3C/path%3E%3C/svg%3E");
  animation: wave 3s both linear;
}

@keyframes wave {
  0% {
    background-position: left 0px bottom -80px;
  }
  100% {
    background-position: left 1500px bottom 20px;
  }
}
</style>
