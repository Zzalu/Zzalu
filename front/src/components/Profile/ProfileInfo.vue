<template>
  <div class="mt-18 ml-4 flex">
    <div class="bg-black w-32 h-32 rounded-full mr-6"></div>
    <div class="mx-auto">
      <div class="flex">
        <div class="follower-and-following">
          <div>10</div>
          <div>팔로워</div>
        </div>
        <div class="follower-and-following">
          <div>8</div>
          <div>팔로잉</div>
        </div>
      </div>
      <button
        class="bg-zz-p mt-2 ml-4 text-white text-xl font-spoq px-5 py-1 rounded"
      >
        팔로우
      </button>
    </div>
  </div>
  <div class="mt-4 mb-4">
    <div class="profile-title">닉네임</div>
    <div class="text-zz-negative font-spoq text-xs my-auto">@아이디</div>
    <div class="mt-2 line-clamp-2">
      개발자입니다~~ 소개글이 엄청길다면????소개글이 엄청길다면????소개글이
      엄청길다면????소개글이 엄청길다면????소개글이 엄청길다면????소개글이
      엄청길다면????소개글이 엄청길다면????
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileInfo",
};
</script>

<style scoped lang="postcss">
.follower-and-following {
  @apply mt-4 text-center mx-2 text-zz-s font-spoq;
}
/* 내프로필이면 my-auto */
</style>