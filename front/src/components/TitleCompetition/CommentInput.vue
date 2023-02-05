<template>
  <!-- 댓글 input -->
  <div class="comment_write flex">
    <div v-show="canWriteNested.value" class="absolute bottom-10 bg-zz-p px-2 py-1 text-xs rounded-xl">
      <span class="mr-1">{{ comment_writer_nickname }}에게 답글</span>
      <button @click="undoWriteNestedComment">
        <font-awesome-icon icon="fa-solid fa-circle-xmark" class="text-zz-light-p" />
      </button>
    </div>
    <input v-model="content" type="text" class="comment_input" placeholder="글 남기기..." />

    <button class="comment_submit">등록</button>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref } from 'vue';
export default {
  name: 'CommentInput',
  setup() {
    const store = useStore();

    let comment_writer_id = computed(() => ref(store.state.titleCompetitionStore.comment_writer.id));
    let comment_writer_nickname = computed(() => ref(store.state.titleCompetitionStore.comment_writer.nickname));

    let canWriteNested = computed(() => ref(store.state.titleCompetitionStore.isNested));

    const undoWriteNestedComment = () => {
      store.dispatch('titleCompetitionStore/deleteCommentWriter');
    };

    return {
      undoWriteNestedComment,
      comment_writer_id,
      comment_writer_nickname,
      content: '',
      canWriteNested,
    };
  },
};
</script>

<style>
.comment_write {
  @apply w-11/12 bg-zz-light-input flex rounded-lg p-1 fixed bottom-14;
}
.nested_comment_input {
  @apply w-auto bg-transparent  text-xs h-7 px-2 focus:outline-none;
}
.comment_input {
  @apply w-full bg-transparent  text-xs h-7 px-2 focus:outline-none;
}

.comment_submit {
  @apply text-xs w-14 px-2 bg-zz-light-p rounded-xl text-white;
}
</style>
