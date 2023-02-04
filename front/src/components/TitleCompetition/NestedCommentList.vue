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
// import { onMounted } from 'vue';
// import { ref, computed, onBeforeMount } from 'vue';
// import { onBeforeMount } from 'vue';
// import { ref } from 'vue';
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
    // let nested_comments = store.state.titleCompetitionStore.nested_comments;
    let nested_comments = [];
    let last_nested_comment_id = Number.MAX_SAFE_INTEGER;
    // console.log(last_nested_comment_id.value);

    const pushNestedComments = async () => {
      return new Promise((resolve) => {
        nested_comments.push(...store.state.titleCompetitionStore.new_nested_comments);
        resolve();
      });
    };
    // 답글 읽기
    const loadMoreNestedComments = async () => {
      console.log(last_nested_comment_id);
      await store.dispatch('titleCompetitionStore/getNestedCommentList', {
        lastCommentId: last_nested_comment_id,
        parentCommentId: props.comment_id,
        size: size,
      });
      // nested_comments.push(...store.state.titleCompetitionStore.new_nested_comments);
      await pushNestedComments();
      console.log(nested_comments);
      last_nested_comment_id = nested_comments[nested_comments.length - 1].id;
      console.log(`last_nested_comment_id: ${last_nested_comment_id}`);
      // await store.dispatch('titleCompetitionStore/setNestedCommentList');
    };

    // onBeforeMount(() => {
    //   loadMoreNestedComments();
    // });

    loadMoreNestedComments();
    return {
      nested_comments,
      loadMoreNestedComments,
      last_nested_comment_id,
    };
  },
};
</script>

<style></style>
