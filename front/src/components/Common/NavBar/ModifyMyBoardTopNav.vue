<template>
  <!-- 내 보드 수정중 nav-->
  <div
    class="
      h-nav-height
      fixed
      inset-x-0
      top-0
      bg-white
      flex
      items-center
      justify-center
      dark:bg-zz-bd
    "
  >
    <div class="flex flex-col">
      <input
        type="text"
        class="
          text-center
          focus:outline-none
          font-bold
          w-3/4
          bg-transparent
          dark:text-white
        "
        :value="`${board_name}`"
        @input="input_store_title = $event.target.value"
      />
      <span class="w-3/4 h-divider-height bg-black dark:bg-white"></span>
    </div>
    <span class="inline-block px-4 absolute left-0">
      <font-awesome-icon
        icon="fa-solid fa-chevron-left"
        class="text-2xl dark:text-white"
        @click="this.$emit('EditMode')"
      />
    </span>
    <span
      class="
        inline-block
        px-4
        absolute
        right-14
        text-zz-s text-xs text-zz-error
        font-spoq
      "
      @click="WantDelete"
    >
      삭제
    </span>
    <span
      class="
        inline-block
        px-4
        absolute
        right-0
        text-zz-s
        dark:text-white
        font-spoq
      "
      @click="BoardNameChange(input_store_title)"
    >
      저장
    </span>
  </div>
</template>

<script>
import { useStore } from "vuex";

export default {
  name: "ModifyMyBoardTopNav",
  setup() {
    const store = useStore();

    const board_name_change = (data) => {
      store.dispatch("boardListStore/boardNameChange", data);
    };
    return {
      board_name_change,
    };
  },
  props: {
    board_name: String,
  },
  data() {
    return {
      input_store_title: "",
    };
  },
  methods: {
    BoardNameChange(data) {
      this.board_name_change(data)
    },
  },
};
</script>

<style></style>
