<template>
  <div>
    <div v-if="createCheck" class="bg-negative"></div>
    <div v-if="createCheck">
      <CannotEditModal @close-modal="createCheck = $event" />
    </div>

    <div class="image-container">
      <div v-if="url != null" class="preview-image" :style="`background-image:url(${this.url})`"></div>
      <div v-else class="preview-image"></div>
      <input @change="upload" type="file" id="file" class="select-image" />
      <label class="select-image-text" for="file"> <font-awesome-icon icon="fa-solid fa-upload" /> gif 첨부하기!</label>
    </div>

    <p class="guide">고독방 이름</p>
    <input class="guideline" placeholder="고독방을 제외한 고독방 이름을 입력해주세요." />
    <p class="caution">고독방 이름에는 특수문자를 사용할 수 없습니다.</p>
    <p class="guide">고독방 한 줄 소개</p>
    <input class="guideline" />
    <p class="caution"></p>
    <p class="guide">해시태그</p>
    <div class="hashtag">
      <div v-if="hashtags.length == 0">빈 해시태그</div>
      <div v-else class="hashtag-div">
        <div v-for="(hashtag, i) in hashtags" :key="i">
          <div class="hashtag-text">{{ hashtag }}</div>
        </div>
      </div>
      <button class="hashtag-btn">
        <font-awesome-icon icon="fa-solid fa-plus" />
      </button>
    </div>
    <div class="create-btn" @click="this.createCheck = true">
      <button>개설하기</button>
    </div>
  </div>
</template>

<script>
import CannotEditModal from '../../components/QuietChat/MakeChat/CannotEditModal.vue';

export default {
  name: 'MakeChatView',
  data() {
    return {
      url: null,
      hashtags: ['고양이', '키보드', 'morehashtag1', 'morehashtag2'],
      createCheck: 0,
    };
  },
  components: {
    CannotEditModal,
  },
  created() {},
  methods: {
    upload(e) {
      let file = e.target.files;
      this.url = URL.createObjectURL(file[0]);
    },
  },
};
</script>

<style scoped lang="postcss">
/* 배경 */
.bg-negative {
  @apply fixed bg-zz-dark-input opacity-50 w-full h-full left-0 top-0;
}

/* 이미지 */
.image-container {
  @apply text-center mb-4;
}
.preview-image {
  background-image: url(../../components/QuietChat/QuietChatList/assets/zzalu_logo_light.png);
  @apply bg-contain bg-center bg-no-repeat rounded-l-lg h-48 w-24 mx-auto;
}
.select-image {
  @apply hidden;
}
.select-image-text {
  @apply cursor-pointer text-xs font-bold font-spoq line-clamp-1;
}

/* 생성 가이드라인 */
.guide {
  @apply font-bold font-spoq line-clamp-1;
}
.guideline {
  @apply border-b-2 h-6 border-black w-full text-xs line-clamp-1;
}
.caution {
  font-size: 0.625rem;
  @apply text-zz-error font-spoq h-4 line-clamp-1;
}
.hashtag {
  @apply flex flex-wrap;
}
.hashtag-div {
  @apply flex text-white flex-wrap;
}
.hashtag-text {
  @apply border rounded-lg bg-zz-p px-2 py-1 mr-2;
}
.hashtag-btn {
  @apply text-zz-p;
}
.create-btn {
  @apply text-center border-2 w-9/12 text-white bg-zz-s rounded-lg h-8 mx-auto mt-8 cursor-pointer;
}
</style>
