<template>
  <ol ref="commentListComponent">
    <li v-for="comment in comments" :key="comment.id" :comment="comment" class="mb-1">
      <CommentListItem :comment="comment" />
      <span class="w-full h-divider-height bg-zz-light-div"></span>
    </li>
  </ol>
</template>

<script>
import CommentListItem from './item/CommentListItem.vue';
import { useStore } from 'vuex';
import { onMounted, ref } from 'vue';

export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const store = useStore();
    const commentListComponent = ref(null);
    let comments = store.state.titleCompetitionStore.comments;

    const loadMoreComments = () => {
      store.dispatch('titleCompetitionStore/getCommentList', 4);
    };

    onMounted(() => {
      window.addEventListener('scroll', handleScroll);
    });
    const handleScroll = () => {
      let element = commentListComponent.value;
      if (element.getBoundingClientRect().bottom < window.innerHeight) {
        loadMoreComments();
      }
    };

    return {
      comments,
      loadMoreComments,
      commentListComponent,
    };
  },
};
</script>

<style scoped></style>
