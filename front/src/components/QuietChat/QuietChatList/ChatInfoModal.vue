<template>
  <div class="modal">
    <div class="modal-title-box">
      <font-awesome-icon
        @click="$emit('close-modal', false)"
        class="modal-icon"
        icon="fa-solid fa-xmark"
      />
      <p class="modal-title">{{ info_data.name }}</p>
    </div>
    <div class="img-container">
      <img
        src="../../QuietChat/QuietChatList/assets/nyang.gif"
        class="modal-img"
      />
    </div>
    <div class="modal-content-box">
      <div class="modal-first-line">
        <div class="group">
          <font-awesome-icon class="master-icon" icon="fa-solid fa-crown" />
          <p class="first-line-content">{{ info_data.master }}</p>
        </div>
        <div class="group">
          <font-awesome-icon icon="fa-solid fa-heart" class="text-zz-s" />
          <p class="first-line-content">{{ info_data.like }}</p>
        </div>
      </div>
      <p class="modal-content">{{ info_data.content }}</p>
      <div class="hashtag-div">
        <p
          v-for="(hashtags, j) in info_data.hashtag"
          :key="j"
          class="hashtag-p"
        >
          {{ hashtags }}
        </p>
      </div>
    </div>
    <div class="flex place-content-evenly">
      <button class="modal-create-btn">
        <router-link to="/chat/0"> 입장하기 </router-link>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatInfoModal",
  props: {
    info_data: Object,
  },
};
</script>

<style scoped lang="postcss">
/* 모달 타이틀 */
.modal {
  min-height: 24rem;
  @apply fixed inset-0 m-auto border border-zz-p rounded-2xl w-9/12 h-96 text-center bg-zz-p;
}
.modal-title-box {
  @apply bg-zz-p border-2 rounded-t-xl border-zz-p;
}
.modal-title {
  @apply text-xl font-bold font-carter ml-8 line-clamp-1 pt-3 pb-3 text-white;
}
.modal-icon {
  @apply float-right text-3xl mr-3 cursor-pointer mt-2;
}
/* 모달 이미지 */

.img-container {
  @apply bg-white h-40 w-full flex justify-center items-center
}
.modal-img {
  @apply h-32 w-40 
}

/* 모달 콘텐트 */
.group {
  @apply flex items-center
}
.master-icon {
  @apply text-zz-s;
}
.modal-content-box {
  @apply text-xs font-spoq ml-4 mr-3;
}
.modal-content {
  @apply text-white line-clamp-3 h-12 text-left;
}
.modal-first-line {
  @apply flex place-content-evenly mt-2 mb-2;
}
.first-line-content {
  @apply text-white ml-1;
}
.hashtag-div {
  @apply flex flex-row text-xs text-zz-light-input flex-wrap line-clamp-2 h-8;
}

.hashtag-p {
  font-size: 0.5rem;
  @apply ml-1;
}
.modal-create-btn {
  @apply rounded-xl mt-3 w-32 h-8 bg-zz-s text-white;
}
</style>