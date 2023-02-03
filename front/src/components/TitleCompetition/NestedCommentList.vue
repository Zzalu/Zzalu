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
import { useStore } from 'vuex';
export default {
  components: { NestedCommentListItem },
  name: 'NestedCommentList',
  setup() {
    const store = useStore();
    let nested_comments = store.state.titleCompetitionStore.nested_comments;

    const roadMoreNestedComments = () => {
      store.dispatch('titleCompetitionStore/getNestedCommentList', 1);
    };
    return {
      nested_comments,
      roadMoreNestedComments,
    };
  },
};
</script>

<style></style>
