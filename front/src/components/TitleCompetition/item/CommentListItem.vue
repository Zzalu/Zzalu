<template>
  <li>
    <div>
      <!-- <img src="../../../assets/logo.png" alt="프로필" /> -->
      <img :src="require(`@/assets/${profile_image}.png`)" alt="프로필 이미지" />
      <p>{{ nickname }}</p>
      <p>{{ time }}</p>
      <p>{{ content }}</p>
      <div>
        <button>답글쓰기</button>
        <button v-if="reply_cnt > 0">{{ reply_cnt }}개의 답글보기</button>
        <button>{{ like_cnt }}<font-awesome-icon icon="fa-regular fa-heart" /></button>
      </div>
    </div>
  </li>
</template>

<script>
import { ref } from '@vue/reactivity';
export default {
  name: 'CommentListItem',
  props: {
    comment: Object,
  },
  setup(props) {
    const profile_image = ref(props.comment.profile_image);
    const nickname = ref(props.comment.nickname);
    const time = ref(props.comment.time);
    const content = ref(props.comment.content);
    const reply_cnt = ref(props.comment.reply_cnt);
    const like_cnt = ref(props.comment.like_cnt);

    console.log(props);

    return {
      profile_image,
      nickname,
      time,
      content,
      reply_cnt,
      like_cnt,
    };
  },
};
</script>

<style scoped></style>
