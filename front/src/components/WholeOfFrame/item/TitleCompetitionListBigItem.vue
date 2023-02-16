<template>
  <div class="title-competition-card-container">
    짤 유알엘 {{ zzal_url }}
    <div class="title-competiton-img-container">
      <!-- 아이콘 날짜 사진 -->
      <img :src="zzal_url" class="title-competiton-img" alt="" />
    </div>
    <div class="border-l-2 border-b-2 border-r-2 h-20 rounded-b-2xl border-white dark:border-zz-dark-div">
      <div class="flex">
        <div>
          <!-- 컨텐트 -->
          <div class="title-competition-content-profile">
            <!-- <img class="title-competiton-content-img" src="../../QuietChat/QuietChatList/assets/Newjeans.jpg" /> -->
            <img
              v-if="best_comment_profile_image != null"
              class="profile-image"
              :style="{ backgroundImage: `url(${best_comment_profile_image})` }"
            />
            <img v-else class="profile-image" :style="{ backgroundImage: `url(${best_comment_profile_image})` }" />
            <p class="title-competiton-content-text">{{ best_comment_nickname }}</p>
          </div>
        </div>
        <!-- 좋아요 -->
        <div class="title-competiton-button-contain">
          <font-awesome-icon icon="fa-solid fa-heart" class="title-competiton-button-icon" />
          <p class="title-competiton-button-text">{{ best_comment_like }}</p>
        </div>
      </div>
      <!-- 내용 -->
      <p class="title-competiton-content">{{ best_comment_content }}</p>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from 'vue-demi';
import { toRefs } from '@vueuse/shared';
import { getBestComments, getTitleCompetition } from '@/api/titleCompetition';
import { onMounted } from 'vue';
export default {
  name: 'TitleCompetitionListBigItem',
  computed: {
    title_competition() {
      console.log('text', this.title_competition);
      return this.title_competition;
    },
  },
  created() {
    console.log('BigItem - created - title_competition : ', this.title_competition);
  },
  // data() {
  //   return {
  //     local_title_competition: '',
  //   };
  // },
  setup() {
    console.log('card setup');
    // console.log(props);
    const title_competition = reactive({
      title_competition_id: title_competition.titleHakwonId,
      open_date: title_competition.openDate,
      zzal_url: title_competition.zzalUrl,
      state: title_competition.state,
    });
    console.log(title_competition);
    getTitleCompetition(
      today,
      (data) => {
        //제목학원 출력
        console.log('[제목학원 respose] ' + JSON.stringify(data.data));

        // getTitleCompetition -> reponseDto 에 "openDate가 없음 따라서 여기서 걍 넣어줌"
        title_competition.value = data.data;
        title_competition.value.openDate = today;
        console.log(title_competition);

        //**생각한대로 데이터가 안넘어감 아마 저 박스가 만들어질때 값을 안주는거같음 **

        console.log('[제목학원 respose] ' + JSON.stringify(title_competition.value));
        console.log(title_competition.value.openDate);
      },
      (error) => {
        console.log(error);
      },
    );
    // best comment를 가져온다.
    const best_comment_nickname = ref(null);
    const best_comment_like = ref(null);
    const best_comment_content = ref(null);
    const best_comment_profile_image = ref(null);

    getBestComments(
      title_competition.title_competition_id,
      {
        limit: 1,
        sort: 'POPULAR',
      },
      (data) => {
        // console.log(data);
        best_comment_nickname.value = data.data[0].nickname;
        best_comment_like.value = data.data[0].likeNumber;
        best_comment_content.value = data.data[0].content;
        best_comment_profile_image.value = data.data[0].profilePath;
      },
      (error) => {
        console.log(error);
      },
    );
    let open_date_obj = new Date(title_competition.open_date);
    const month = open_date_obj.toLocaleString('en-US', { month: 'short' });
    const date = open_date_obj.getDate();

    onMounted(() => {
      console.log('온마운트: ' + JSON.stringify(props));
    });

    return {
      ...toRefs(title_competition),
      best_comment_nickname,
      best_comment_like,
      best_comment_content,
      best_comment_profile_image,
      month,
      date,
      onMounted,
    };
  },
};
</script>

<style scoped lang="postcss">
.title-competition-card-container {
  @apply w-60 h-72 rounded-2xl ml-3 mr-2 mb-1 relative;
}
.title-competiton-img-container {
  @apply flex justify-center pb-2 bg-contain pt-2;
}
/* .date-icon {
  @apply w-14 h-12 text-zz-p;
} */

/* .title-competiton-icon {
  font-size: 0.8rem;
  text-align: center;
  @apply font-bhs text-white;
} */

.title-competiton-img {
  overflow: hidden;
  @apply h-40 w-36 rounded-xl border-2 dark:border-zz-dark-div;
}
.title-competition-content-profile {
  @apply flex items-center mt-2 mr-12;
}
.title-competiton-content-img {
  @apply rounded-full w-6 mx-1;
}
.title-competiton-content-text {
  font-size: 0.8rem;
  @apply font-spoq line-clamp-1 dark:text-white;
}
.title-competiton-button-contain {
  @apply border flex rounded-2xl bg-zz-p items-center px-1 ml-1 mt-3 mr-1 absolute right-1 w-10 h-4 dark:border-zz-dark-div;
}
.title-competiton-button-icon {
  font-size: 0.5rem;
  @apply mr-1 text-zz-s;
}
.title-competiton-button-text {
  font-size: 0.6rem;
  transform: translateY(-0.05rem);
  @apply text-white w-8 truncate;
}
.title-competiton-content {
  word-break: keep-all;
  @apply mt-3 ml-2 text-xs line-clamp-2 font-spoq mx-1 dark:text-white line-clamp-4;
}

.profile-image {
  width: 2.5rem;
  height: 2.5rem;
  background-size: cover;

  /* max-width: 100px;
    max-height: 100px; */
  /* object-fit: cover; */
  @apply mr-3 rounded-full bg-center bg-no-repeat;
}

.profile-image-none {
  width: 2.5rem;
  height: 2.5rem;
  background-size: cover;
  /* max-width: 100px;
    max-height: 100px; */
  /* object-fit: cover; */
  @apply mr-3 rounded-full bg-center bg-no-repeat text-zz-light-p dark:text-zz-negative;
}
</style>
