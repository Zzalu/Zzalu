<template>
  <ol>
    <li v-for="nested_comment in nested_comments" :key="nested_comment.nested_comment_id" class="mb-1">
      <nested-comment-list-item :comment="nested_comment" />
      <span class="w-full h-divider-height bg-zz-light-div"></span>
    </li>
  </ol>
</template>

<script>
import NestedCommentListItem from './item/NestedCommentListItem.vue';
export default {
  components: { NestedCommentListItem },
  setup() {
    const nested_comments = [
      {
        nested_comment_id: 1,
        nickname: '대댓글조아',
        content: '댓글보다 웃긴 대댓글',
        comment_id: 1,
        profile_image: 'profile.jpg',
        time: '12시간 전',
        like_cnt: 231,
        modified: false,
      },
      {
        nested_comment_id: 2,
        nickname: '그저웃기만',
        content: 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',
        comment_id: 1,
        profile_image: 'profile.jpg',
        time: '11시간 전',
        like_cnt: 2,
        modified: false,
      },
    ];
    return {
      nested_comments,
    };
  },
};
</script>

<style></style>
