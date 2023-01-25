<template>
    <div class="modal">
        잠깐만요!
        <button @click="$emit('close-modal',false)"> 취소 </button>
        <button>  만들기 </button>
    </div>
</template>

<script>
export default {
    name:"CannotEditModal",
    
}
</script>

<style scoped lang="postcss">
.modal{
  position:fixed;
  @apply border-2
}

</style>