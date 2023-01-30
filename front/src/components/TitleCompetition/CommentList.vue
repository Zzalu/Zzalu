<template>
  <ol>
    <li v-for="(comment, rank) in comments" :key="rank" class="mb-1">
      <CommentListItem :comment="comment" />
      <span class="w-full h-divider-height bg-zz-light-div"></span>
    </li>
  </ol>
</template>

<script>
import CommentListItem from './item/CommentListItem.vue';
export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const comments = [
      {
        profile_image: 'profile.jpg',
        nickname: '내가 1등',
        content: '1등의 댓글이야',
        time: '13시간 전',
        reply_cnt: 1,
        like_cnt: 231,
        modified: false,
      },
      {
        profile_image: 'profile.jpg',
        nickname: '내가 2등',
        content: '2등의 댓글이야',
        time: '13시간 전',
        reply_cnt: 0,
        like_cnt: 110,
        modified: true,
      },
    ];
    return {
      comments,
    };
  },
};
</script>

<style scoped></style>
