<template>
  <!-- 댓글 input -->
  <div v-if="state != 'DONE'" class="comment_write flex">
    <div v-show="canWriteNested.value" class="absolute bottom-10 bg-zz-p px-2 py-1 text-xs rounded-xl">
      <span class="mr-1">{{ comment_writer_nickname }}에게 답글</span>
      <button @click="undoWriteNestedComment">
        <font-awesome-icon icon="fa-solid fa-circle-xmark" class="text-zz-light-p" />
      </button>
    </div>
    <input v-if="isLogined" type="text" class="comment_input" @change="changeInput" placeholder="글 남기기..." />
    <input
      v-else
      type="text"
      class="comment_input"
      @change="changeInput"
      placeholder="로그인 후에 참여하실 수 있습니다 :)"
      disabled
    />

    <button class="comment_submit" @click="clicksubmitBtn">등록</button>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, ref } from 'vue';
import { addComment, addNestedComment } from '@/api/titleCompetition';
export default {
  name: 'CommentInput',
  setup() {
    const store = useStore();
    let content = '';
    let comment_writer_id = computed(() => ref(store.state.titleCompetitionStore.comment_writer.id));
    let comment_writer_nickname = computed(() => ref(store.state.titleCompetitionStore.comment_writer.nickname));

    let canWriteNested = computed(() => ref(store.state.titleCompetitionStore.isNested));
    let state = computed(() => store.state.titleCompetitionStore.state);
    let isLogined = computed(() => {
      if (window.localStorage.getItem('token')) {
        return true;
      } else {
        return false;
      }
    });
    const undoWriteNestedComment = async () => {
      await store.dispatch('titleCompetitionStore/deleteCommentWriter');
    };

    const changeInput = (e) => {
      return (content = e.target.value);
    };
    // 등록버튼 눌렀을 때
    const clicksubmitBtn = () => {
      if (!canWriteNested.value.value) {
        const comment_data = {
          content: content,
          titleHakwonId: store.state.titleCompetitionStore.title_competition_id,
        };
        addComment(
          comment_data,
          ({ data }) => {
            console.log(data);
            store.dispatch('titleCompetitionStore/pushComment', data);
          },
          (error) => {
            console.log(error);
          },
        );
        console.log('댓글입니다');
      } else {
        const nested_comment_data = {
          content: content,
          parentCommentId: store.state.titleCompetitionStore.comment_writer.comment_id,
        };
        console.log(nested_comment_data);
        addNestedComment(
          nested_comment_data,
          ({ data }) => {
            console.log(data);
          },
          (error) => {
            console.log(error);
          },
        );
        console.log('답글입니다');
      }
    };

    return {
      undoWriteNestedComment,
      comment_writer_id,
      comment_writer_nickname,
      content,
      canWriteNested,
      clicksubmitBtn,
      changeInput,
      state,
      isLogined,
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
