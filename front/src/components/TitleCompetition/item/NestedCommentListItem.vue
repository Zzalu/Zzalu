<template>
  <div>
    <li>
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <p class="text-xs mr-1">{{ new_time }}</p>
        <p v-if="canDelete" class="text-xs" @click="clickDeleteBtn">삭제</p>
      </div>
      <p class="text-base mb-1">{{ content }}</p>
    </li>
  </div>
</template>

<script>
import { reactive, toRefs } from '@vue/reactivity';
// import { deleteNestedComment } from '@/api/titleCompetition.js';
import { computed } from 'vue-demi';

export default {
  name: 'NestedCommentListItem',
  props: {
    nested_comment: Object,
    index: Number,
  },
  emits: ['popNestedComment'],
  setup(props, ctx) {
    const nested_comment_data = reactive({
      profile_image: 'profile.jpg',
      username: props.nested_comment.username,
      nested_comment_id: props.nested_comment.replyCommentId,
      nickname: props.nested_comment.nickname,
      time: props.nested_comment.createdTime,
      content: props.nested_comment.content,
      // modified: false,
    });

    const username = window.localStorage.getItem('profile_user');

    // 삭제 버튼
    const canDelete = computed(() => {
      return (nested_comment_data.username = username);
    });

    // 시간표시: ~ 전
    const new_time = timeForToday(new Date(nested_comment_data.time));
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

    const clickDeleteBtn = () => {
      console.log('dd');
      console.log(props.index);
      ctx.emit('popNestedComment', props.index);
      /*       deleteNestedComment(
        nested_comment_data.nested_comment_id,
        (({ data }) => {
          console.log(data);
          //TODO: 데이터 삭제 후 부모의 nested comment 배열에서 삭제하기
        },
        (error) => {
          console.log(error);
        }),
      ); */
    };

    return {
      ...toRefs(nested_comment_data),
      canDelete,
      clickDeleteBtn,
      new_time,
    };
  },
};
</script>

<style scoped></style>
