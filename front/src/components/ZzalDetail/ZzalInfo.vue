<template>
  <button class="edit-button text-zz-edit flex float-right">
    [편집하기]
  </button>
  <div class="mt-6">
    <div class="last-edited">
      마지막으로 수정한 사람: {{누구}}
    </div>
    <div class="last-edited">
      마지막으로 수정한 날짜: {{ 날짜 }}
    </div>
  </div>

  <div class="hashtag-container">
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-circle-minus" class="hashtag-edit" />
      <div class="hashtag">해시태그해시태그해시</div>
    </div>
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-circle-minus" class="hashtag-edit" />
      <div class="hashtag">해시태그</div>
    </div>
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-circle-minus" class="hashtag-edit" />
      <div class="hashtag">해시태그</div>
    </div>
    <div class="flex">
      <font-awesome-icon icon="fa-solid fa-circle-minus" class="hashtag-edit" />
      <div class="hashtag">해시태그</div>
    </div>
  </div>

  <div class="mt-6 mb-2 font-bold font-spoq text-zz-p"> 
    이 짤의 유래는?
  </div>
  <div class="zzal-origin-edit">
    짤 유래 짤 유래 짤 유래 짤 유래 짤 유래 짤 유래 짤 유래 짤 유래 짤 유래 
  </div>
</template>

<script>
export default {
  name: 'ZzalInfo',


}
</script>

<style lang="postcss" scoped>

.hashtag-container {
  @apply mt-10 flex flex-wrap
}

.last-edited {
  @apply text-zz-negative text-xs
}

.zzal-origin {
  @apply bg-zz-light-input
}

.zzal-origin-edit {
  @apply border-2 border-zz-s
}
</style>