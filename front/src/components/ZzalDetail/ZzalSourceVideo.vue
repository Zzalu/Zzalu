<template>
  <!-- <div class="original-vid">
    <iframe src="https://www.youtube.com/watch?v=sey8rFdvq6M" frameborder="0"></iframe>
  </div> -->
  <div class="edit-original-vid">
    <font-awesome-icon icon="fa-brands fa-youtube" class="yt-icon"/>
    <input type="text" class="edit-original-link" placeholder="https://www.youtube.com/watch?v=sey8rFdvq6M">
    <font-awesome-icon icon="fa-solid fa-square-plus" class="plus-icon"/>
  </div>
</template>

<script>
export default {
  name: "ZzalSourceVideo"

}
</script>

<style lang="postcss" scoped>
  .original-vid {
    @apply mt-6 flex justify-center
  }

  .edit-original-vid {
    @apply mt-6 flex mb-20
  }

  .edit-original-link {
    @apply flex-col border-2 border-zz-s rounded-sm w-4/5 px-1

  }

  .yt-icon{
    @apply my-auto mr-3 text-zz-error
  }

  .plus-icon {
    @apply my-auto ml-3 text-zz-s
  }
</style>