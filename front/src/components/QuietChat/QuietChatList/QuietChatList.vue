<template>
  <div>
    <div class="card-container">
      <div class="card-img-contanier"></div>
      <div class="card-content">
        <div class="flex">
          <font-awesome-icon class="master-icon" icon="fa-solid fa-crown" />
          <p class="master-p">{{ datas.master }}</p>
        </div>
        <p class="title-p">{{ datas.name }}</p>
        <p class="content-p">{{ datas.content }}</p>
        <div class="hashtag-div">
          <p v-for="(hashtags, i) in datas.hashtag" :key="i" class="hashtag-p">
            {{ hashtags }}
          </p>
        </div>
        <div class="last-lane">
          <p class="updated-p">최근대화시간 : {{ datas.updatedAt }}</p>
          <p class="like-p">
            <font-awesome-icon icon="fa-solid fa-heart" class="text-zz-s" />
            {{ datas.like }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "QuietChatList",
  props: {
    datas : Object
  }
};
</script>

<style scoped lang="postcss">
.card-container {
  @apply grid grid-cols-12 text-white mt-5 h-32 font-spoq;
}
.card-img-contanier {
  background-image: url(./assets/nyang.gif);
  @apply col-span-4 border-2 bg-cover bg-center bg-no-repeat rounded-l-lg;
}
.card-content {
  word-break: keep-all;
  @apply col-span-8 bg-zz-p rounded-r-lg pl-2 flex flex-col;
}
.master-icon {
  @apply mt-2 mr-1 text-zz-light-p text-xs;
}
.master-p {
  font-size: 0.25rem;
  @apply pt-2 line-clamp-1;
}
.title-p {
  @apply text-xl font-bold line-clamp-1;
}

.content-p {
  @apply text-xs line-clamp-2 h-8;
}

.hashtag-div {
  @apply flex flex-row text-xs text-zz-light-input flex-wrap line-clamp-2 h-8;
}

.hashtag-p {
  font-size: 0.5rem;
  @apply ml-1;
}
.like-p {
  @apply ml-auto mr-2 line-clamp-1;
}
.updated-p {
  @apply mr-auto line-clamp-1;
}
.last-lane {
  font-size: 0.5rem;
  @apply flex mt-auto pb-2 items-center;
}
</style>