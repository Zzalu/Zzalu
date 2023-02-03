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
import { reactive, toRefs } from '@vue/reactivity';
export default {
  name: 'NestedCommentListItem',
  props: {
    nested_comment: Object,
  },
  setup(props) {
    console.log(props);
    const nested_comment_data = reactive({
      profile_image: 'profile.jpg',
      id: props.comment.id,
      nickname: props.comment.nickname,
      time: '13시간 전',
      content: props.comment.content,
      modified: false,
      like_cnt: props.comment.likeNumber,
    });
    console.log(nested_comment_data);

    return {
      ...toRefs(nested_comment_data),
    };
  },
};
</script>

<style scoped></style>
