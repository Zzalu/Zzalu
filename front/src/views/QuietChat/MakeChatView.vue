<template>
  <div>
    <div class="image-container">
      <div class="preview-image" :style="`background-image:url(${this.url})`"></div>
      <input @change="upload" type="file" id="file" class="select-image" />
      <label class="select-image-text" for="file">gif 첨부하기!</label>
    </div>

    <p class="guide">채팅방 이름</p>
    <input class="guideline">
    <p class="caution">고독방 이름에는 특수문자를 사용할 수 없습니다.</p>
    <p class="guide">고독방 한 줄 소개</p>
    <input class="guideline">
    <p class="caution"></p>
    <p class="guide">해시태그</p>
    <button class="hashtag-btn">+</button>
  </div>
</template>

<script>
export default {
  name: "MakeChatView",
  data() {
    return {
      url : null,
    } 
  },
  methods : {
    upload(e) {
      let file = e.target.files;
      this.url = URL.createObjectURL(file[0]);
    }
  }
};
</script>

<style scoped lang="postcss">
.image-container {
  @apply mt-16 text-center mb-4;
}
.preview-image {
  /* background-image: url(./assets/Dragon.jpg); */
  @apply bg-contain bg-center bg-no-repeat rounded-l-lg h-48 w-24 mx-auto;
}
.select-image {
  @apply hidden;
}
.select-image-text {
  @apply cursor-pointer text-xs font-bold font-spoq;
}
.guide {
  @apply font-bold font-spoq;
}
.guideline{
  @apply border-b-2 h-6 border-black w-full
}
.caution{
  font-size: 0.625rem;
  @apply text-zz-error font-spoq
}
.hashtag-btn{
}
</style>