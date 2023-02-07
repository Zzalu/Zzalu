<template>
  <h1>팔로워 리스트</h1>
  <follower-list-item/>
</template>

<script>
import FollowerListItem from "@/components/Profile/Item/FollowerListItem"

export default {
  name: "FollowingList",
  components: {
    FollowerListItem
  }
}
</script>

<style>

</style>