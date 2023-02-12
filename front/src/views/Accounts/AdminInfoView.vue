<template>
  <div>
    <h1>어드민 페이지</h1>
    <div class="grid-cols-2">
      <div v-for="(tempGif,i) in tempGifList" :key="i">
        <admin-list-item
        :tempGif="tempGif"/>
      </div>

    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
import AdminListItem from "@/components/Accounts/Admin/AdminListItem.vue"
export default {
  name: "AdminInfoView",
  components: {
    AdminListItem,
  },
  setup () {
    const store = useStore();
    const tempGifList = computed(
      () => store.state.tempGifStore.temp_gif_list
    );
    return {
      tempGifList
      // follow_request
    }
  }
  
}
</script>

<style>

</style>