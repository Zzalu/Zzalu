<template>
  <div class="mt-6"></div>
  <div class="hashtag-contain">
    <div class="hashtag-div">
      <div v-for="(hashtag, i) in tags" :key="i" class="relative">
        <div class="hashtag-text">{{ hashtag }}</div>
        <div class="hashtag-deleted">
          <font-awesome-icon
            icon="fa-solid fa-circle-minus"
            class="hashtag-edited"
            @click="RemoveHashtag(i)"
          />
        </div>
      </div>
    </div>
    <!-- 해시태그 인풋 -->
    <div v-if="hashtags_input_mode" class="input_contain">
      <input type="text" class="input_value" v-model="hash_input" autofocus />
    </div>
    <!-- 해시태그 인풋 버튼 -->
    <button v-if="hashtags_input_mode" class="hashtag-btn" @click="AddHashtag">
      <button>등록</button>
    </button>
    <button v-else class="hashtag-btn" @click="InputHashtag">
      <font-awesome-icon icon="fa-solid fa-plus" class="text-xl" />
    </button>
  </div>

  <div class="mt-6 mb-2 font-bold font-spoq text-zz-p">이 짤의 유래는?</div>
  <div class="zzal-origin-edit">
    <input
      v-if="description"
      type="textarea"
      class="input-box"
      v-model="description"
    />
    <input
      v-else
      type="textarea"
      class="input-box"
      placeholder="이 짤의 유래를 입력해주세요!"
    />
  </div>
  <div class="mt-6 mb-1 font-bold font-spoq text-zz-p">관련 영상 추가하기</div>
  <div class="edit-original-vid">
    <font-awesome-icon icon="fa-brands fa-youtube" class="yt-icon" />
    <input type="text" class="edit-original-link" v-model="relationsVideo" />
    <font-awesome-icon
      icon="fa-solid fa-square-plus"
      class="plus-icon"
      @click="updateVideo"
    />
  </div>
  <div class="flex justify-center dark:text-white">
    <button
      class="text-center border-2 w-9/12 text-white bg-zz-s rounded-lg h-8 mx-auto cursor-pointer font-spoq dark:border-zz-dark-div"
      @click="update_request(this.request_form)"
    >
      저장하기
    </button>
  </div>
  <div>
    <button @click="updateRequest">완료</button>
  </div>
</template>

<script>
export default {
  name: "ZzalInfo",
  data() {
    return {
      hashtags_input_mode: false,
      hash_input_err: false,
      hash_input: "",
      description: this.jjal_detail_data.description,
      relationsVideo: this.jjal_detail_data.relationsVideo,
      request_form : {
        origin_id : "",
        updated_description : "",
        updated_relationsVideo : "",
        updated_tags : "",
      }
    };
  },
  props: {
    // zzal_origin_content: String,
    jjal_detail_data: Object,
  },
  computed: {
    tags() {
      return this.jjal_detail_data.tags.split(",");
    },
    visitedcount() {
      return this.jjal_detail_data.visitedcount;
    },
    id() {
      return this.jjal_detail_data.id;
    },
    // description() {
    //   return this.jjal_detail_data.description
    // }
  },
  methods: {
    RemoveHashtag(i) {
      this.tags.splice(i, 1);
    },
    InputHashtag() {
      this.hashtags_input_mode = true;
    },
    AddHashtag() {
      const regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
      if (this.hash_input == "") {
        alert("내용을 입력해주세요");
        this.hashtags_input_mode = false;
      } else if (regex.test(this.hash_input) == false) {
        alert("한글과 숫자와 영어만 입력해주세요");
      } else {
        this.tags.push(this.hash_input);
        this.hash_input = "";
        this.hashtags_input_mode = false;
        this.hash_input_err = false;
        // console.log(this.tags.join())
      }
    },
    updateRequest() {
      this.request_form.updated_description = this.description;
      this.request_form.updated_tags = this.tags.join();
      this.request_form.updated_relationsVideo = this.relationsVideo;
      console.log(this.request_form.updated_description)
      // this.$emit("infoUpdateRequest", this.description, this.tags.join(), this.relationsVideo);
    },

  },
  watch: {
    hash_input(nv, ov) {
      if (this.hash_input.length >= 10) {
        this.hash_input_err = true;
        this.hash_input = ov;
      }
    },
  },
};
</script>

<style lang="postcss" scoped>
.hashtag-container {
  @apply mt-10 flex flex-wrap;
}
.hashtag-contain {
  @apply flex flex-wrap mt-2;
}
.hashtag-div {
  @apply flex text-white flex-wrap;
}
.hashtag-text {
  @apply border rounded-lg bg-zz-p px-2 py-1 mr-2 mb-2 font-spoq dark:border-zz-dark-div;
}

.hashtag-deleted {
  @apply absolute inset-0;
}

.hashtag-edited {
  transform: translate(-6px, -6px);
  @apply text-white border-2 border-zz-error rounded-full bg-zz-error;
}

.create-btn {
  @apply text-center border-2 w-9/12 text-white bg-zz-s rounded-lg h-8 mx-auto mt-8 cursor-pointer font-spoq dark:border-zz-dark-div;
}

.input_contain {
  font-size: 1rem;
  width: 5.5rem;
  @apply mr-4 h-8 mt-1 border border-zz-p dark:border-zz-s;
}
.input_value {
  width: 5.3rem;
  @apply align-middle h-6 bg-transparent dark:text-white;
}
.hashtag-btn {
  @apply text-zz-p;
}
.hashtag-deleted {
  @apply absolute inset-0;
}

.last-edited {
  @apply text-zz-negative text-xs;
}

.zzal-origin {
  @apply bg-zz-light-input;
}

.zzal-origin-edit {
  min-height: 2rem;
  @apply border-2 border-zz-s;
}
.input-box {
  @apply w-full bg-transparent dark:text-white font-spoq;
}
.original-vid {
  @apply mt-6 flex justify-center;
}

.edit-original-vid {
  @apply flex mb-20;
}

.edit-original-link {
  @apply flex-col border-2 border-zz-s rounded-sm w-4/5 px-1;
}

.yt-icon {
  @apply my-auto mr-3 text-zz-error;
}

.plus-icon {
  @apply my-auto ml-3 text-zz-s;
}
</style>
