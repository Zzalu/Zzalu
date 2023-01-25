<template>
    <div>
        잠깐만요!


        <button> 취소 </button>
        <button @click="$emit('Cancel',0)"> 만들기 </button>
    </div>
</template>

<script>
export default {
    name:"CannotEditModal",
    
}
</script>

<style>

</style>