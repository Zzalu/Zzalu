<template>
  <div>
    <ol>
      <li v-for="nested_comment in nested_comments" :key="nested_comment.nested_comment_id" class="mb-1">
        <nested-comment-list-item :nested_comment="nested_comment" />
        <span class="w-full h-divider-height bg-zz-light-div"></span>
      </li>
    </ol>
    <div @click="loadMoreNestedComments">
      <button v-if="last_nested_comment_id > 0">{{ last_nested_comment_id }}개의 답글 더보기</button>
    </div>
  </div>
</template>

<script>
import NestedCommentListItem from './item/NestedCommentListItem.vue';
import { useStore } from 'vuex';
// import { ref } from 'vue';
export default {
  components: { NestedCommentListItem },
  name: 'NestedCommentList',
  props: {
    comment_id: Number,
    nested_comment_cnt: Number,
  },
  setup(props) {
    console.log(props);
    const store = useStore();
    const size = 3;
    // let nested_comments = store.state.titleCompetitionStore.nested_comments;
    let nested_comments = [];
    // let last_nested_comment_id = ref(props.nested_comment_cnt).value;
    let last_nested_comment_id = 1000;
    store.dispatch('titleCompetitionStore/getNestedCommentList', {
      lastCommentId: last_nested_comment_id,
      parentCommentId: props.comment_id,
      size: size,
    });
    nested_comments.push(...store.state.titleCompetitionStore.new_nested_comments);
    // 답글 더 읽기
    const loadMoreNestedComments = () => {
      store.dispatch('titleCompetitionStore/getNestedCommentList', {
        lastCommentId: last_nested_comment_id,
        parentCommentId: props.comment_id,
        size: size,
      });
      nested_comments.push(...store.state.titleCompetitionStore.new_nested_comments);
      console.log(nested_comments);
      last_nested_comment_id -= size;
      console.log(last_nested_comment_id);
    };

    return {
      nested_comments,
      loadMoreNestedComments,
      last_nested_comment_id,
    };
  },
};
</script>

<style></style>
