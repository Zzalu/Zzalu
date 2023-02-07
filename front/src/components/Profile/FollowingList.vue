<template>
 <h1>팔로잉 리스트</h1>
 <following-list-item/>
</template>

<script>
import FollowingListItem from "@/components/Profile/Item/FollowingListItem.vue"

export default {
  name: "FollowingList",
  components: {
    FollowingListItem,
    // FollowTopNav,
    // MainBottomNav
  }
}
</script>

<style>

</style>
