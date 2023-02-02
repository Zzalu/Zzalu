<template>
  <div class="title-competition-card-container">
    <div class="title-competiton-img-container">
      <!-- 아이콘 날짜 사진 -->
      <div class="absolute z-10">
        <p class="title-competiton-icon-text1">Jan</p>
        <p class="title-competiton-icon-text2">1</p>
      </div>
      <div class="absolute">
        <font-awesome-icon icon="fa-solid fa-bookmark" class="date-icon" />
      </div>
      <img
        src="../../QuietChat/QuietChatList/assets/nyang.gif"
        class="title-competiton-img"
        alt=""
      />
    </div>
    <div class="flex">
      <div>
        <!-- 컨텐트 -->
        <div class="title-competition-content-profile">
          <img
            class="title-competiton-content-img"
            src="../../QuietChat/QuietChatList/assets/Newjeans.jpg"
          />
          <p class="title-competiton-content-text">이름이최대열글자라니</p>
        </div>
      </div>
      <!-- 좋아요 -->
      <div class="title-competiton-button-contain">
        <font-awesome-icon
          icon="fa-solid fa-heart"
          class="title-competiton-button-icon"
        />
        <p class="title-competiton-button-text">999</p>
      </div>
    </div>
    <!-- 내용 -->
    <p class="title-competiton-content">
      잠깐만요. 불공평한거 같은데요. 이 펜싱 대회 만약 더 길다면
    </p>
  </div>
</template>

<script>
export default {
  name : "AcademyListItem"

};
</script>

<style scoped lang="postcss">

.title-competition-card-container {
  box-shadow:0 0 5px black;
  @apply border w-36 h-48 rounded-2xl ml-3 mr-2 mb-1 dark:border-zz-dark-div;
}
.title-competiton-img-container {
  @apply relative;
}
.date-icon {
  @apply w-14 h-12 text-zz-p;
}

.title-competiton-icon-text1 {
  margin-left: 0.9rem;
  font-size: 0.8rem;
  height: 16px;
  @apply font-bhs text-white;
}
.title-competiton-icon-text2 {
  margin-left: 2rem;
  font-size: 0.8rem;
  @apply font-bhs text-white;
}
.title-competiton-img {
  @apply h-28 w-36 rounded-t-xl;
}
.title-competition-content-profile {
  @apply flex items-center mt-2;
}
.title-competiton-content-img {
  @apply rounded-full w-4 mx-1;
}
.title-competiton-content-text {
  font-size: 0.2rem;
  @apply font-spoq line-clamp-1 dark:text-white;
}
.title-competiton-button-contain {
  @apply border flex rounded-2xl bg-zz-p items-center px-1 ml-1 mt-2 mr-1 ;
}
.title-competiton-button-icon {
  font-size: 0.6rem;
  @apply mr-1 text-zz-s;
}
.title-competiton-button-text {
  font-size: 0.4rem;
  @apply text-white;
}
.title-competiton-content {
  word-break: keep-all;
  @apply mt-2 text-xs line-clamp-2 font-spoq mx-1 dark:text-white;
}
</style>