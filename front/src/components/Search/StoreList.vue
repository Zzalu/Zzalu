<template>
  <div ref="List">
    <div v-if="!creating">
      <div class="list-title">
        <p class="list-title-content">저장하기</p>
        <p class="list-title-make" @mouseup="ChangeCreate">
          <font-awesome-icon icon="fa-solid fa-plus" /> 새보관함 만들기
        </p>
      </div>
      <div class="list-items-contain">
        <div v-for="(list, items) in list_name" :key="items" class="list-items">
          <input type="checkbox" :id="'check' + items" :value="list" />
          <label :for="'check' + items"> </label>
          <label :for="'check' + items" class="cursor-pointer">
            {{ list }}</label
          >
        </div>
      </div>
      <div class="save-btn-contain">
        <button class="save-btn"
        @mouseup="SaveItem"
        >저장</button>
      </div>
    </div>
    <!-- 생성중 이라면 -->
    <div v-else>
      <div class="list-title">
        <p class="list-title-content">저장하기</p>
      </div>
      <div class="creating-list-items-contain">
        <div v-for="(list, items) in list_name" :key="items" class="list-items">
          <input type="checkbox" :id="'check' + items" :value="list" />
          <label :for="'check' + items"> </label>
          <label :for="'check' + items" class="cursor-pointer">
            {{ list }}</label
          >
        </div>
      </div>

      <p class="m-2 text-xs font-bhs">이름</p>
      <div class="creating-input-contain">
        <input placeholder="보관함 이름 입력.." class="font-spoq" />
      </div>
      <div class="creating-save-btn-contain" ref="tmp2">
        <button class="cancel-btn"
        @mouseup="CancelCreateList"
        >취소</button>
        <button class="save-btn"
        @mouseup="CreateList"
        >저장</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "StoreList",
  setup() {
    const store = useStore();

    const close_list_modal = () => {
      store.commit("searchModalStore/close_list_modal");
    };
    const open_list_modal = computed(
      () => store.state.searchModalStore.open_list_modal
    );
    return {
      close_list_modal,
      open_list_modal,
    };
  },
  data() {
    return {
      creating: false,
      list_name: [
        "즐겨찾기",
        "고양이",
        "무한도전",
        "리스트1리스트1리스트1리스트1",
        "리스트2",
        "리스트3",
        "리스트4",
        "리스트5",
        "리스트6",
        "리스트7",
      ],
    };
  },
  mounted() {
    if (this.open_list_modal) {
      setTimeout(() => {
        document.addEventListener("click", this.ListoutClick);
      }, 100);
    } else {
      document.removeEventListener("click", this.ListoutClick);
    }
  },
  methods: {
    // console.log(e.target,'타겟',this.$refs.List, this.$refs.List.contains(e.target));
    // console.log(e.target,'1')
    // console.log(this.$refs.List,'List',this.$refs.List.contains(e.target))
    // if (this.$refs.tmp2) {
    //   console.log('tmp2 true');
    //   console.log(this.$refs.tmp1);
    //   console.log(this.$refs.tmp2.contains(e.target))
    // }
    //   this.creating = false
    // } else if (this.$refs.tmp2) {
    //   console.log('tmp2 true');
    // console.log(this.$refs.tmp2.contains(e.target))
    //   this.creating = false
    // }
    // console.log(this.$refs.tmp1,'tmp1',this.$refs.tmp1.contains(e.target))
    // console.log(this.$refs.tmp2, "tmp2", this.$refs.tmp2.contains(e.target));
    // let flag = null
    // if (this.$refs.tmp1) {
    //   flag = true
    // } else if (this.$refs.tmp2) {
    //   flag = false
    // }
    // console.log('이벤트시작',flag,'<-- flag',this.creating,'<-- creating');
    // if (this.$refs.List == null) {
    //   return;
    // } else if (
    //   !(this.$refs.List.contains(e.target)) ||
    //   ((this.creating == true) && (flag == false))
    // ) {
    //   console.log("취소나 저장누름",this.creating == true);
    //   console.log(this.$refs.tmp2.contains(e.target));
    // } else if (
    //   !this.$refs.List.contains(e.target) ||
    //   (this.creating == false && (flag == true))
    // ) {
    //   console.log(this.$refs.tmp1.contains(e.target));
    //   console.log("새 보관함 누름");
    // } else {
    //   this.close_list_modal();
    // }
    ListoutClick(e) {
      if (this.$refs.List == null) {
        return;
      } else if (!this.$refs.List.contains(e.target)) {
        this.close_list_modal();
      }
    },
    SaveItem() {
      this.close_list_modal();
    },
    CancelCreateList() {
      this.creating = false;
    },
    CreateList() {
      this.list_name.push("추가제목");
      this.creating = false;
    },
    ChangeCreate() {
      this.creating = true;
    }
  },
};
</script>

<style scoped lang="postcss">
/* 상단 */
.list-title {
  @apply mt-2 flex items-center mb-2 ;
}

.list-title-content {
  @apply mr-auto ml-4 mt-1 font-bhs;
}

.list-title-make {
  @apply text-xs ml-auto pr-2 text-zz-p font-spoq;
}

/* 생성중일 때 */
.hide-list-title-make {
  @apply hidden;
}
/* 보관함 목록 */
.list-items-contain {
  @apply h-56 overflow-y-scroll font-spoq;
}
.list-items {
  @apply m-2;
}
/* 저장 버튼 */
.save-btn-contain {
  @apply h-7 absolute bottom-0 left-16 right-16 flex justify-center mb-4 font-spoq;
}
.save-btn {
  @apply bg-zz-s text-white px-5 rounded-lg py-1 text-xs;
}

/* 생성중일때 리스트 */

.creating-list-items-contain {
  @apply border h-40 overflow-y-scroll font-spoq;
}

/* 생성중일때 인풋 */

.creating-input-contain {
  @apply ml-2 border-b-2 w-11/12 mb-5;
}

/* 생성중일 때 버튼 */

.creating-save-btn-contain {
  @apply flex place-content-evenly font-spoq;
}

.cancel-btn {
  @apply border border-zz-p text-zz-p px-5 rounded-lg py-1 text-xs;
}

/* 체크박스 커스텀마이징 */

input[type="checkbox"] {
  display: none;
}
input[type="checkbox"] + label {
  display: inline-block;
  width: 0.85rem;
  height: 0.85rem;
  cursor: pointer;
  @apply border border-zz-p mr-2;
}

input[type="checkbox"]:checked + label {
  background-image: url(../QuietChat/QuietChatList/assets/done_check_sm2.png);
  background-repeat: no-repeat;
  background-position: 50%;
  background-size: cover;
  @apply bg-zz-p text-lg;
}
</style>