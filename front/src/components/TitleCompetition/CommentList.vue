<template>
  <ol ref="commentListComponent">
    <li v-for="comment in comments" :key="comment.id" :comment="comment" class="mb-1">
      <CommentListItem :comment="comment" />
      <span class="w-full h-divider-height bg-zz-light-div dark:border-b-zz-dark-div"></span>
    </li>
  </ol>
</template>

<script>
import CommentListItem from './item/CommentListItem.vue';
import { useStore } from 'vuex';
import { ref } from 'vue';
// import { onMounted, ref } from 'vue';

export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const store = useStore();
    const commentListComponent = ref(null);
    let comments = store.state.titleCompetitionStore.comments;

    /*  onMounted(() => {
      window.addEventListener('scroll', handleScroll);
    });
 */

    /* const handleScroll = () => {
      let element = commentListComponent.value;

      // TODO: 댓글 스크롤 내리면 마지막이 계속 불러와지는 오류 고치기
      if (element.getBoundingClientRect().bottom < window.innerHeight) {
        loadMoreComments();
      }
    }; */

    return {
      comments,
      commentListComponent,
    };
  },
};
</script>

<style scoped></style>
