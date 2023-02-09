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
        @click="this.$router.go(-1)"
      />
    </span>
    <span class="inline-block px-4 absolute right-0">
      <font-awesome-icon
        v-if="is_like"
        icon="fa-solid fa-heart"
        class="toempty text-xl text-zz-s dark:text-zz-dark-s"
        @click="changelike"
      />
      <font-awesome-icon
        v-else
        icon="fa-regular fa-heart"
        class="tofill text-xl text-zz-s dark:text-zz-dark-s"
        @click="changelike"
      />
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

@keyframes filltoempty {
  0% {
    opacity: 0.3;
  }
  100% {
    opacity: 1;
  }
}

@keyframes emptytofill {
  0% {
    opacity: 0.3;
  }
  100% {
    opacity: 1;
  }
}
.toempty {
  animation-name: filltoempty;
  animation-duration:1s;
  animation-direction:normal;
  animation-fill-mode: both;
}

.tofill{
  animation-name: emptytofill;
  animation-duration:1s;
  animation-direction:normal;
  animation-fill-mode: both;
}
</style>
