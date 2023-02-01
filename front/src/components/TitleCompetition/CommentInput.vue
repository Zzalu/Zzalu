<template>
  <!-- 댓글 input -->
  <div class="comment_write">
    <input type="text" class="comment_input" placeholder="댓글 입력하기..." />
    <button class="comment_submit">등록</button>
  </div>
</template>

<script>
export default {
  name: 'CommentInput',
  setup() {},
};
</script>

<style>
.comment_write {
  @apply w-11/12 bg-zz-light-input flex rounded-lg p-1 fixed bottom-14;
}
.comment_input {
  @apply w-full bg-transparent  text-xs h-7 px-2 focus:outline-none;
}

.comment_submit {
  @apply text-xs w-14 px-2 bg-zz-light-p rounded-xl text-white;
}
</style>
