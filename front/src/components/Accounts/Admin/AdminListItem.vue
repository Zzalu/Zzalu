<template>
  <div>
    <div class="bg-red-300 p-8 m-2">
      뭔가뭔가임
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminListItem",
  props: {
    PopularJjal : Object,
  },
  data () {
    return {
      originid: '아이디',
      gifpath: '짤의경로',
      tags: ['해시태그1', '해시태그2', '해시태그3'],
      description: "유래유래유래",
    }
  }

}
</script>

<style>

</style>