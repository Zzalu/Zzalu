<template>
  <ol>
    <li v-for="comment in comments" :key="comment.id" :comment="comment" class="mb-1">
      <CommentListItem :comment="comment" />
      <span class="w-full h-divider-height bg-zz-light-div"></span>
    </li>
  </ol>
</template>

<script>
import CommentListItem from './item/CommentListItem.vue';
// import { reactive, toRefs } from 'vue';
import { useStore } from 'vuex';

export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const store = useStore();
    let comments = store.state.titleCompetitionStore.comments;

    return {
      comments,
    };
  },
};
</script>

<style scoped></style>
