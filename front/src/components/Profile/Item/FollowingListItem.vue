<template>
  <h1>ing item</h1>
  <div>{{ this.Followings }}</div>
  <div>{{ this.id }}</div>
  <div>{{ this.username }}</div>
  <button @click="follow_request">{{ this.followState }}</button>
</template>

<script>
import { useStore } from 'vuex';
import { toRefs } from 'vue';

export default {
  name : "FollowingListItem",
  props : {
    Followings : Object,
  },
  setup(props) {
    const { id } = toRefs(props.Followings)
    const { username } = toRefs(props.Followings)
    const { nickname } = toRefs(props.Followings)
    const { profilePath } = toRefs(props.Followings)
    const { followState } = toRefs(props.Followings)
    const store = useStore();

    const follow_request = () => {
      if (followState.value == false) {
        store.dispatch("followStore/requsetFollow", id.value)
        .then((res) => {
          console.log(res)
          followState.value = true;
        })
        .catch((err) => {
          console.log(err)
        })
      } else {
        store.dispatch("followStore/requestUnfollow", id.value)
        .then((res) => {
          console.log(res)
          followState.value = false;
        })
        .catch((err) => {
          console.log(err)
        })
      }
    };
    return {
      id, username, nickname, profilePath, followState, follow_request
    }
  },
}
</script>

<style>

</style>