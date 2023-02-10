<template>
  <li>
    <div class="dark:text-white">
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <p class="text-xs mr-1">{{ time }}</p>
        <p v-if="canDelete" class="text-xs" @click="clickDeleteBtn">삭제</p>
      </div>
      <p class="text-base mb-1">{{ content }}</p>
      <div class="flex flex-row mb-2">
        <div class="w-full">
          <button @click="writeNestedComment" class="text-xs mr-2">답글쓰기</button>
          <button v-if="nested_comment_cnt > 0 && !nested_active" class="text-xs">
            <font-awesome-icon icon="fa-solid fa-chevron-down" class="mr-1 text-xs" />
            <span class="text-center" @click="nested_active = !nested_active"
              >{{ nested_comment_cnt }}개의 답글보기</span
            >
          </button>
          <button v-if="nested_comment_cnt > 0 && nested_active" class="text-xs">
            <font-awesome-icon icon="fa-solid fa-chevron-up" class="mr-1 text-xs" />
            <span class="text-center" @click="nested_active = !nested_active">답글 접기</span>
          </button>
        </div>

        <div class="flex items-center text-zz-p">
          <span class="text-xs mr-1">
            {{ like_cnt }}
          </span>
          <button class="my-auto" @click="clickLikeBtn">
            <font-awesome-icon v-if="!is_liked" icon="fa-regular fa-heart" class="text-xs" />
            <font-awesome-icon v-else icon="fa-solid fa-heart" class="text-xs text-zz-p" />
          </button>
        </div>
      </div>
      <!-- 대댓글 -->
      <div class="w-full flex justify-end">
        <nested-comment-list
          v-if="nested_active"
          :comment_id="comment_id"
          :nested_comment_cnt="nested_comment_cnt"
          class="w-11/12"
        />
      </div>
    </div>
  </li>
</template>

<script>
import { useStore } from 'vuex';
import { reactive, toRefs } from '@vue/reactivity';
import NestedCommentList from '@/components/TitleCompetition/NestedCommentList.vue';
import { plusLike, minusLike, deleteComment } from '@/api/titleCompetition.js';
import { computed } from 'vue-demi';
export default {
  components: { NestedCommentList },
  name: 'CommentListItem',
  props: {
    comment: Object,
  },
  setup(props) {
    const store = useStore();
    // TODO: time ~~전으로 출력하기
    const comment_data = reactive({
      profile_image: 'profile.jpg',
      username: props.comment.username,
      comment_id: props.comment.commentId,
      nickname: props.comment.nickname,
      time: props.comment.createdTime,
      content: props.comment.content,
      nested_comment_cnt: props.comment.replyCommentsSize,
      // modified: false,
      nested_active: false,
      like_cnt: props.comment.likeNumber,
      is_liked: props.comment.pressed,
    });

    // TODO: 나중에 로그인 기능 완성되면 username 수정하기
    const canDelete = computed(() => {
      return (comment_data.username = 'c109');
    });

    // 답글쓰기 버튼 클릭
    const writeNestedComment = () => {
      const comment_writer = {
        comment_id: comment_data.comment_id,
        nickname: comment_data.nickname,
      };
      store.dispatch('titleCompetitionStore/writeNestedComment', comment_writer);
    };

    // 좋아요 버튼 클릭
    const clickLikeBtn = () => {
      console.log(`isLike 버튼 클릭 전: ${comment_data.is_liked}`);
      const comment_id = comment_data.comment_id;
      if (comment_data.is_liked) {
        console.log('음별로');
        minusLike(
          comment_id,
          ({ data }) => {
            console.log(data);
            comment_data.is_liked = false;
          },
          (error) => {
            console.log(error);
          },
        );
      } else {
        console.log('좋아요!');
        plusLike(
          comment_id,
          ({ data }) => {
            console.log(data);
            comment_data.is_liked = true;
          },
          (error) => {
            console.log(error);
          },
        );
      }
      console.log(`is_like 버튼 클릭 후: ${comment_data.is_liked}`);
    };

    const clickDeleteBtn = () => {
      deleteComment(
        comment_data.comment_id,
        (({ data }) => {
          console.log(data);
          //TODO: 데이터 삭제 후 store의 comments 배열에서 삭제하기
        },
        (error) => {
          console.log(error);
        }),
      );
    };

    return {
      ...toRefs(comment_data),
      writeNestedComment,
      clickLikeBtn,
      canDelete,
      clickDeleteBtn,
    };
  },
};
</script>

<style scoped></style>
