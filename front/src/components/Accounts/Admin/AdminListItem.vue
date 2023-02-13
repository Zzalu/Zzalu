<template>
  <div>
    <div v-if="this.isDisplay" class="bg-red-300 p-8 m-2">
      <div>요청 번호: {{ this.id }}</div>
      <div>요청 타입: {{ this.requestType }}</div>
      <img v-if="gifPath" :src="`${this.gifPath}`" alt="" class="col-span-2 justify-center h-40">
      <div v-if="tags">태그: {{ this.tags }}</div>
      <div v-if="relationsVideo">관련 동영상: {{ this.relationsVideo }}</div>
      <div v-if="description">짤 설명: {{ this.description }}</div>
      <div>작성자 ID: {{ this.writerUsername }}</div>
      <div v-if="originGifsId">원본 gif 게시글: {{ this.originGifsId }}</div>
      <button class="bg-zz-s" @click="put_temp_gif">승인 횟수: {{ this.permittedCount }}</button>
      <button @click="delete_temp_gif">삭제</button>
      <div>{{ this.isDisplay }}</div>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { toRefs } from 'vue';
import { ref } from 'vue';

export default {
  name: "AdminListItem",
  props: {
    tempGif : Object,
  },
  setup(props) {
    const { id } = toRefs(props.tempGif)
    const { gifPath } = toRefs(props.tempGif)
    const { tags } = toRefs(props.tempGif)
    const { relationsVideo } = toRefs(props.tempGif)
    const { description } = toRefs(props.tempGif)
    const { writerUsername } = toRefs(props.tempGif)
    const { permittedCount } = toRefs(props.tempGif)
    const { requestType } = toRefs(props.tempGif)
    const { originGifsId } = toRefs(props.tempGif)
    const isDisplay = ref(true);
    const store = useStore();

    const put_temp_gif = () => {
      store.dispatch("tempGifStore/putTempGif", id.value)
      .then((res) => {
        console.log(res)
        if (permittedCount.value >= 2) {
          isDisplay.value = false

        } else {
          permittedCount.value = permittedCount.value + 1
        }
      })
      .catch((err) => {
        console.log(err)
      })
    };
    const delete_temp_gif = () => {
      store.dispatch("tempGifStore/deleteTempGif", id.value)
      .then((res) => {
        console.log(res)
        alert("게시물이 삭제되었습니다.")
        isDisplay.value = false

      })
      .catch((err) => {
        console.log(err)
      })
    };
    return {
      id, gifPath, tags, relationsVideo, description, writerUsername, permittedCount, requestType, originGifsId, put_temp_gif, delete_temp_gif, 
      isDisplay
    }
  },

}
</script>

<style>

</style>