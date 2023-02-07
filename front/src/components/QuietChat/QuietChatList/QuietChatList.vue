<template>
  <div>
    <div class="card-container">
      <div class="card-img-contanier"></div>
      <div class="card-content">
        <div class="flex">
          <font-awesome-icon class="master-icon" icon="fa-solid fa-crown" />
          <p class="master-p">{{ room_data.userName }}</p>
        </div>
        <p class="title-p">{{ room_data.roomName }} 고독방</p>
        <p class="content-p">{{ room_data.description }}</p>
        <div class="hashtag-div">
          <div v-for="(hashtags, j) in hash" :key="j" class="hashtag-p">
            <div v-if="hashtags"># {{ hashtags }}</div>
          </div>
        </div>
        <div class="last-lane">
          <p class="updated-p">최근대화시간 : {{ date }}</p>
          <p class="like-p">
            <font-awesome-icon icon="fa-solid fa-heart" class="text-zz-s" />
            {{ room_data.likeCount }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "QuietChatList",
  data() {
    return {
      hash: [],
      date: "",
    };
  },
  props: {
    room_data: Object,
    hashtag: String,
  },
  created() {
    this.hash = this.hashtag.split(",");

    let date1 = new Date(this.room_data.lastActivation);
    const betweenTime =
      Math.floor((new Date().getTime() - date1.getTime()) / 1000 / 60) - 540;
    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    const betweenTimeHour = Math.floor(betweenTime / 60);

    if (betweenTime < 1) {
      this.date = "방금 전";
    } else if (betweenTime < 60) {
      this.date = `${betweenTime}분전`;
    } else if (betweenTime < 1440) {
      this.date = `${betweenTimeHour}시간 전`;
    } else if (betweenTimeDay < 525600) {
      this.date = `${betweenTimeDay}일전`;
    } else {
      this.date = `${Math.floor(betweenTimeDay / 365)}년전`;
    }
  },
};
</script>

<style scoped lang="postcss">
.card-container {
  box-shadow: 0 0 7px black;
  @apply grid grid-cols-12 text-white mt-5 h-32 font-spoq rounded-lg dark:border-zz-dark-div;
}
.card-img-contanier {
  background-image: url(./assets/nyang.gif);
  @apply col-span-4 border-2 bg-cover bg-center bg-no-repeat rounded-l-lg dark:border-zz-dark-div;
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