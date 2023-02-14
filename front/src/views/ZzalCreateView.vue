<template>
  <!-- 에디트 모드 -->
  <only-go-back-top-nav></only-go-back-top-nav>
  <div>
    <div>
        <div class="bg-center">
        <!-- <img :src="`${gifpath}`" alt="" class="img-contain" /> -->
          <!-- <img class="profile-image" :style="{backgroundImage : `url(${this.image})`}"> -->
          <form>

            <input type="file" ref="serveyImage" @change="onInputImage()">
            <!-- <input type="file" :v-model="request_form.updated_image"> -->
          </form>
          <!-- <button @click="profileUploadBtn"></button> -->
        </div>
    </div>

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
        type="textarea"
        class="input-box"
        v-model="request_form.updated_description"
        placeholder="이 짤의 유래를 입력해주세요!"
        />
        
    </div>
  

    <div class="edit-original-vid">
        <font-awesome-icon icon="fa-brands fa-youtube" class="yt-icon" />
        <input
        type="text"
        class="edit-original-link"
        v-model="request_form.updated_relationsVideo"
        />
        <font-awesome-icon icon="fa-solid fa-square-plus" class="plus-icon"/>
    </div>

    
    <div class="flex justify-center dark:text-white">
      <button
        class="
          text-center
          border-2
          w-9/12
          text-white
          bg-zz-s
          rounded-lg
          h-8
          mx-auto
          cursor-pointer
          font-spoq
          dark:border-zz-dark-div
        "
        @click="update_request(this.request_form)"
      >
        저장하기
      </button>
      
    </div>
  </div>
  <!-- 에디트 모드 -->
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import OnlyGoBackTopNav from "../components/Common/NavBar/OnlyGoBackTopNav.vue";
import MainBottomNav from "../components/Common/NavBar/MainBottomNav.vue"


import { useStore } from "vuex";
// import { computed } from "@vue/runtime-core";
// var imageFile = document.querySelector("#image111");
// imageFile.addEventListener("change", function (event) {
//   var fileList = imageFile.files || event.target.files
//   var file = fileList[0]
//   this.request_form.updated_image = file
// } )


export default {
  name: "ZzalCreateView",
  components: {
    OnlyGoBackTopNav,
    MainBottomNav
  },
  setup() {
    const store = useStore();
    const update_request = (form) => {
      // form.updated_image = imageFile.files[0];
      console.log(form.updated_image)
      if (form.updated_image == "") {
        alert("gif를 업로드 해주세요.")
      } else if (form.updated_tags == ""){
        alert("태그를 한 개 이상 넣어주세요.")
      } else {
        store.dispatch("tempGifStore/postTempGif", form)
      }
    
      // 이전 페이지로

    }
    return {
      update_request,
    };
  },
  data() {
    return {
      hashtags_input_mode : false,
      hash_input_err : false,
      hash_input : '',
      request_form : {
        updated_image : "",
        updated_description : "",
        updated_relationsVideo : "",
        updated_tags : "",
        origin_id : "",
      }
    };
  },
  computed: {
    tags() {
      return this.request_form.updated_tags.split(",")
    },
  },
  mounted() {
    // this.get_detail_data(this.$route.params.zzal_id)
  },
  methods: {
    onInputImage() {
      this.request_form.updated_image = this.$refs.serveyImage.files[0]
      console.log(this.request_form.updated_image, "gg")

    },
    RemoveHashtag(i) {
      this.tags.splice(i, 1);
    },
    InputHashtag() {
      this.hashtags_input_mode = true; 
    },
    AddHashtag() {
      const regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
      if (this.hash_input=='') {
        alert('내용을 입력해주세요')
        this.hashtags_input_mode = false
      } else if (regex.test(this.hash_input) == false) {
        alert('한글과 숫자와 영어만 입력해주세요')
      } else {
        
        
        this.tags.push(this.hash_input)
        console.log(this.tags, "태그다")
        this.hash_input = ''
        
        var strTag = this.tags.join();
        if (strTag.indexOf(',') == 0) {
          // this.request_form.updated_tags = strTag.substr(1);
          this.tags.shift()
        }
        this.request_form.updated_tags = this.tags.join()
        
      }
    },
  },
  watch: {
    hash_input(nv,ov) {
      if (this.hash_input.length >= 10) {
        this.hash_input_err = true;
        this.hash_input = ov;
      }
    },
  }
};
</script>

<style scoped lang="postcss">
.view-count {
  @apply my-3 mx-2 text-zz-negative;
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
.last-edited {
  @apply text-zz-negative text-xs;
}

.zzal-origin {
  @apply bg-zz-light-input;
}

.zzal-origin-edit {
  @apply border-2 border-zz-s;
}

.original-vid {
  @apply mt-6 flex justify-center;
}

.edit-original-vid {
  @apply mt-6 flex mb-20;
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
