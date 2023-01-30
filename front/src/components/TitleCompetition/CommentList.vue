<template>
  <div>
    <ol>
      <li v-for="(comment, rank) in comments" :key="rank">
        <CommentListItem :comment="comment" />
      </li>
    </ol>
  </div>
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
      },
      {
        profile_image: 'profile.jpg',
        nickname: '내가 2등',
        content: '2등의 댓글이야',
        time: '13시간 전',
        reply_cnt: 0,
        like_cnt: 110,
      },
    ];
    return {
      comments,
    };
  },
};
</script>

<style scoped></style>
