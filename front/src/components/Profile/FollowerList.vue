<template>
  <div>
    <h1>팔로워 리스트</h1>
    <div class="items">
      <div v-for="(Followers, i) in FollowerListItemData" :key="i">
        <follower-list-item :Followers="Followers" />
      </div>
    </div>
  </div>
</template>

<script>
import FollowerListItem from '@/components/Profile/Item/FollowerListItem';
import { useStore } from 'vuex';
import { computed } from '@vue/runtime-core';

export default {
  name: 'FollowerList',
  setup() {
    const store = useStore();
    const FollowerListItemData = computed(() => store.state.followStore.follower_list);
    return {
      FollowerListItemData,
    };
  },
  components: {
    FollowerListItem,
  },
};
</script>

<style></style>
