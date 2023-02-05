<template>
  <div>
    <ol>
      <li v-for="nested_comment in nested_comments" :key="nested_comment.nested_comment_id" class="mb-1">
        <nested-comment-list-item :nested_comment="nested_comment" />
        <span class="w-full h-divider-height bg-zz-light-div"></span>
      </li>
    </ol>
    <div @click="loadMoreNestedComments">
      <button v-if="nested_comment_cnt - nested_comments.length > 0" class="text-xs text-zz-negative ml-6">
        - {{ nested_comment_cnt - nested_comments.length }}개의 답글 더보기
      </button>
    </div>
  </div>
</template>

<script>
import NestedCommentListItem from './item/NestedCommentListItem.vue';
import { useStore } from 'vuex';
import { reactive, toRefs } from 'vue-demi';

export default {
  components: { NestedCommentListItem },
  name: 'NestedCommentList',
  props: {
    comment_id: Number,
    nested_comment_cnt: Number,
  },
  setup(props) {
    console.log(props.nested_comment_cnt);
    const store = useStore();
    const size = 3;
    const state = reactive({
      nested_comments: [],
      last_nested_comment_id: Number.MAX_SAFE_INTEGER,
    });
    const pushNestedComments = async () => {
      return new Promise((resolve) => {
        state.nested_comments.push(...store.state.titleCompetitionStore.new_nested_comments);
        resolve();
      });
    };
    // 답글 읽기
    const loadMoreNestedComments = async () => {
      console.log(state.last_nested_comment_id);
      await store.dispatch('titleCompetitionStore/getNestedCommentList', {
        lastCommentId: state.last_nested_comment_id,
        parentCommentId: props.comment_id,
        size: size,
      });
      await pushNestedComments();
      console.log(state.nested_comments);
      state.last_nested_comment_id = state.nested_comments[state.nested_comments.length - 1].id;
      console.log(`last_nested_comment_id: ${state.last_nested_comment_id}`);
    };

    loadMoreNestedComments();
    return {
      ...toRefs(state),
      loadMoreNestedComments,
    };
  },
};
</script>

<style></style>
