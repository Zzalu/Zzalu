<template>
  <ol>
    <li v-for="comment in comments" :key="comment.comment_id" class="mb-1">
      <CommentListItem :comment="comment" />
      <span class="w-full h-divider-height bg-zz-light-div"></span>
    </li>
  </ol>
</template>

<script>
import CommentListItem from './item/CommentListItem.vue';
import { useStore } from 'vuex';
import { onBeforeMount } from '@vue/runtime-core';

export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const store = useStore();
    onBeforeMount(() => {
      store.dispatch('titleCompetitionStore/getCommentList', {
        lastCommentId: Number.MAX_SAFE_INTEGER,
        titleHakwonId: 1,
        size: 1,
      });
    });
    const comments = store.state.titleCompetitionStore.comments;

    return {
      comments,
    };
  },
};
</script>

<style scoped></style>
