<template>
  <li>
    <div class="dark:text-white">
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <p class="text-xs text-zz-darkgray mr-1">{{ new_time }}</p>
        <p v-if="canDelete" class="text-xs text-zz-negative" @click="clickDeleteBtn">· 삭제</p>
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
import Swal from 'sweetalert2';
export default {
  components: { NestedCommentList },
  name: 'CommentListItem',
  props: {
    comment: Object,
    index: Number,
  },
  setup(props) {
    const store = useStore();
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
    const username = window.localStorage.getItem('profile_user');

    // 삭제 버튼
    const canDelete = computed(() => {
      return (comment_data.username = username);
    });

    // 시간표시: ~ 전
    const new_time = timeForToday(new Date(comment_data.time));
    function timeForToday(value) {
      const today = new Date();
      const timeValue = new Date(value);

      const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
      if (betweenTime < 1) return '방금 전';
      if (betweenTime < 60) {
        return `${betweenTime}분 전`;
      }

      const betweenTimeHour = Math.floor(betweenTime / 60);
      if (betweenTimeHour < 24) {
        return `${betweenTimeHour}시간 전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
      if (betweenTimeDay < 365) {
        return `${betweenTimeDay}일 전`;
      }

      return `${Math.floor(betweenTimeDay / 365)}년 전`;
    }

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
      const comment_id = comment_data.comment_id;
      if (comment_data.is_liked) {
        minusLike(
          comment_id,
          ({ data }) => {
            console.log(data);
            comment_data.is_liked = false;
            comment_data.like_cnt -= 1;
          },
          (error) => {
            console.log(error);
          },
        );
      } else {
        plusLike(
          comment_id,
          ({ data }) => {
            console.log(data);
            comment_data.is_liked = true;
            comment_data.like_cnt += 1;
          },
          (error) => {
            console.log(error);
          },
        );
      }
    };

    // 삭제버튼 클릭
    const clickDeleteBtn = () => {
      Swal.fire({
        icon: 'warning',
        text: '삭제하시겠습니까?',
        showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
        confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
        cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
        confirmButtonText: '승인', // confirm 버튼 텍스트 지정
        cancelButtonText: '취소', // cancel 버튼 텍스트 지정
      }).then((result) => {
        if (result.isConfirmed) {
          store.dispatch('titleCompetitionStore/deleteComment', props.index);
          deleteComment(
            comment_data.comment_id,
            ((data) => {
              console.log(data);
            },
            (error) => {
              console.log(error);
            }),
          );
        }
      });
      /*       store.dispatch('titleCompetitionStore/deleteComment', props.index);
      deleteComment(
        comment_data.comment_id,
        ((data) => {
          console.log(data);
        },
        (error) => {
          console.log(error);
        }),
      ); */
    };

    return {
      ...toRefs(comment_data),
      writeNestedComment,
      clickLikeBtn,
      canDelete,
      clickDeleteBtn,
      new_time,
    };
  },
};
</script>

<style scoped></style>
