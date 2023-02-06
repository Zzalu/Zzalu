<template>
  <div>
    <li>
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <p class="text-xs mr-1">{{ time }}</p>
        <p v-if="canDelete" class="text-xs" @click="deleteComment">삭제</p>
      </div>
      <p class="text-base mb-1">{{ content }}</p>
    </li>
  </div>
</template>

<script>
import { reactive, toRefs } from '@vue/reactivity';
import { deleteNestedComment } from '@/api/titleCompetition.js';
import { computed } from 'vue-demi';

export default {
  name: 'NestedCommentListItem',
  props: {
    nested_comment: Object,
  },
  setup(props) {
    // TODO: time ~~전으로 출력하기
    const nested_comment_data = reactive({
      profile_image: 'profile.jpg',
      username: props.nested_comment_data.username,
      nested_comment_id: props.nested_comment.id,
      nickname: props.nested_comment.nickname,
      time: props.comment.createdTime,
      content: props.nested_comment.content,
      // modified: false,
    });

    // TODO: 나중에 로그인 기능 완성되면 username 수정하기
    const username = 'c109';
    const canDelete = computed(() => {
      return username != nested_comment_data.username;
    });

    const clickDeleteBtn = () => {
      deleteNestedComment(
        nested_comment_data.nested_comment_id,
        (({ data }) => {
          console.log(data);
          //TODO: 데이터 삭제 후 부모의 nested comment 배열에서 삭제하기
        },
        (error) => {
          console.log(error);
        }),
      );
    };

    return {
      ...toRefs(nested_comment_data),
      canDelete,
      clickDeleteBtn,
    };
  },
};
</script>

<style scoped></style>
