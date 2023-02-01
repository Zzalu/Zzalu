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
import { reactive, toRefs } from 'vue';
import { useStore } from 'vuex';

export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const store = useStore();

    store.dispatch('titleCompetitionStore/getCommentList', {
      lastCommentId: Number.MAX_SAFE_INTEGER,
      titleHakwonId: 1,
      size: 1,
    });

    let comments = reactive(store.state.titleCompetitionStore.comments);

    return {
      ...toRefs(comments),
    };
  },
};
</script>

<style scoped></style>
