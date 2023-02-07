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
      <font-awesome-icon
        v-if="is_like"
        icon="fa-solid fa-heart"
        class="text-xl text-zz-s dark:text-zz-dark-s"
        id="vibration"
        @click="[changelike(), vibration(this)]"
      />
      <font-awesome-icon
        v-else
        icon="fa-regular fa-heart"
        class="text-xl text-zz-s dark:text-zz-dark-s"
        id="vibration"
        @click="[changelike(), vibration(this)]"
      />
    </span>
  </div>
</template>

<script>

export default {
  name: 'ChatRoomTopNav',
  props: {
    room_name : String,
  },
  data() {
    return {
      is_like:false,
    }
  },
  methods: {
    changelike() {
      this.is_like = !this.is_like
    },
    vibration(target){
      target.classList.add("vibration");
      setTimeout(function() {
        target.classList.remove("vibration");
          }, 400);
    }
  }
};
</script>

<style scoped lang="postcss">
@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");

@keyframes vibration {
  from {
    transform: rotate(1deg);
  }
  to {
    transform: rotate(-1deg);
  }
}
.vibration {
  animation: vibration .1s infinite;
}
</style>
