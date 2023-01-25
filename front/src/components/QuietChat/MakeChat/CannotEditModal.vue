<template>
  <div class="modal">
    <font-awesome-icon @click="$emit('close-modal', false)" class="modal-icon" icon="fa-solid fa-xmark" />
    <p class="modal-title">잠깐만요 !</p>
    <hr class="modal-line" />
    <p class="modal-content">
      고독방을 한 번 만들게 되면 정보 수정이 불가능해요.
    </p>
    <p class="modal-content">이대로 만드시겠어요?</p>
    <div class="flex place-content-evenly">
      <button class="modal-cancel-btn" @click="$emit('close-modal', false)">
        취소
      </button>
      <button class="modal-create-btn">
        <router-link to="/chat-list"> 만들기 </router-link>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CannotEditModal",
};
</script>

<style scoped lang="postcss">
.modal {
  min-height: 8rem;
  @apply fixed inset-0 m-auto border-2 border-zz-p rounded-2xl w-80 h-32 text-center bg-white;
}
.modal-title {
  @apply text-xl font-bold font-carter ml-8 line-clamp-1;
}
.modal-icon {
  @apply float-right text-3xl mr-3 cursor-pointer;
}
.modal-line {
  @apply w-9/12 h-1 bg-zz-p mx-auto mb-2 my-1;
}
.modal-content {
  @apply text-xs font-spoq line-clamp-1;
}
.modal-cancel-btn {
  @apply border rounded-xl w-16 h-8 mt-3 bg-zz-negative text-white;
}
.modal-create-btn {
  @apply border rounded-xl mt-3 w-16 h-8 bg-zz-p text-white;
}
</style>