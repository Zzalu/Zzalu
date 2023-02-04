<template>
  <div>
    <li>
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <p class="text-xs mr-1">{{ time }}</p>
        <p v-if="modified" class="text-xs">(수정됨)</p>
      </div>
      <p class="text-base mb-1">{{ content }}</p>
    </li>
  </div>
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
      id: props.nested_comment.id,
      nickname: props.nested_comment.nickname,
      time: '13시간 전',
      content: props.nested_comment.content,
      modified: false,
    });
    console.log(nested_comment_data);

    return {
      ...toRefs(nested_comment_data),
    };
  },
};
</script>

<style scoped></style>
