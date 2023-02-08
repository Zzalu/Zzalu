<template>
 <h1>팔로잉 리스트</h1>
 <div class="items">
  <div v-for="(Followings, i) in FollowingListItemData" :key="i">
    <following-list-item
    :Followings="Followings"
    />
  </div>
 </div>
 
</template>

<script>
import FollowingListItem from "@/components/Profile/Item/FollowingListItem.vue"
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "FollowingList",
  setup() {
    const store = useStore();
    const FollowingListItemData = computed(
      () => store.state.followStore.following_list
    );
    return {
      FollowingListItemData
    }

  },
  components: {
    FollowingListItem
  }
};
</script>

<style>

</style>
