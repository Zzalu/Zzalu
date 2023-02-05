<template>
  <li>
    <div>
      <div class="flex items-center mb-2">
        <div class="w-3 h-3 rounded-full mr-2">
          <img :src="require(`@/assets/${profile_image}`)" alt="프로필 이미지" class="rounded-full" />
        </div>
        <p class="text-xs mr-2 font-bold">{{ nickname }}</p>
        <!-- <p class="text-xs mr-1">{{ time }}</p> -->
        <!-- <p v-if="modified" class="text-xs">(수정됨)</p> -->
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
          <button class="my-auto"><font-awesome-icon icon="fa-regular fa-heart" class="text-xs" /></button>
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
export default {
  components: { NestedCommentList },
  name: 'CommentListItem',
  props: {
    comment: Object,
  },
  setup(props) {
    const store = useStore();
    const comment_data = reactive({
      profile_image: 'profile.jpg',
      comment_id: props.comment.id,
      nickname: props.comment.nickname,
      time: '13시간 전',
      content: props.comment.content,
      nested_comment_cnt: props.comment.replyCommentsSize,
      modified: false,
      nested_active: false,
      like_cnt: props.comment.likeNumber,
    });

    const writeNestedComment = () => {
      const comment_writer = {
        id: comment_data.comment_id,
        nickname: comment_data.nickname,
      };
      store.dispatch('titleCompetitionStore/writeNestedComment', comment_writer);
    };

    return {
      ...toRefs(comment_data),
      writeNestedComment,
    };
  },
};
</script>

<style scoped></style>
