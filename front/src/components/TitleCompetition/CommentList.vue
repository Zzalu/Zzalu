<template>
  <ol ref="commentListComponent">
    <li v-for="comment in comments" :key="comment.commentId" class="mb-1">
      <CommentListItem :comment="comment" />
      <span class="w-full h-divider-height bg-zz-light-div dark:border-b-zz-dark-div"></span>
    </li>
  </ol>
  <div class="flex-row justify-center items-center" v-if="comments.length == 0">
    <p>좋아요가 없네요ㅠㅠ</p>
    <p>당신의 웃음벨에게 좋아요를 주세요!</p>
  </div>
</template>

<script>
import CommentListItem from './item/CommentListItem.vue';
import { useStore } from 'vuex';
// import { ref } from 'vue';
import { ref, computed } from 'vue';
// import { onMounted, ref } from 'vue';

export default {
  components: { CommentListItem },
  name: 'CommentList',
  setup() {
    const store = useStore();
    const commentListComponent = ref(null);
    let comments = computed(() => store.state.titleCompetitionStore.comments);
    // let comments = store.state.titleCompetitionStore.comments;

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
