<template>
  <li>
    <div>
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <p class="text-xs mr-1">{{ time }}</p>
        <p v-if="modified" class="text-xs">(수정됨)</p>
      </div>
      <p class="text-base mb-1">{{ content }}</p>
      <div class="flex flex-row">
        <div class="w-full">
          <button class="text-xs mr-2">답글쓰기</button>
          <button v-if="reply_cnt > 0" class="text-xs">
            <font-awesome-icon icon="fa-solid fa-chevron-down" class="mr-1 text-xs" />
            <span class="text-center">{{ reply_cnt }}개의 답글보기</span>
          </button>
        </div>

        <div class="flex items-center text-zz-p">
          <span class="text-xs mr-1">
            {{ like_cnt }}
          </span>
          <button class="my-auto"><font-awesome-icon icon="fa-regular fa-heart" class="text-xs" /></button>
        </div>
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
    const modified = ref(props.comment.modified);

    console.log(props);

    return {
      profile_image,
      nickname,
      time,
      content,
      reply_cnt,
      like_cnt,
      modified,
    };
  },
};
</script>

<style scoped></style>
