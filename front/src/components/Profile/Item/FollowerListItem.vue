<template>
  <div class="flex">
    <h1>er item</h1>
    <div>{{ this.Followers }}</div>
    <div>{{ this.id }}</div>
    <div>{{ this.username }}</div>
    <button @click="follow_request">{{ this.followState }}</button>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { toRefs } from 'vue';

export default {
  name : "FollowerListItem",
  props : {
    Followers : Object,
  },
  setup(props) {
    const { id } = toRefs(props.Followers)
    const { username } = toRefs(props.Followers)
    const { nickname } = toRefs(props.Followers)
    const { profilePath } = toRefs(props.Followers)
    const { followState } = toRefs(props.Followers)
    const store = useStore();

    const follow_request = () => {
      if (followState.value == false) {
        store.dispatch("followStore/requsetFollow", id.value)
        .then((res) => {
          followState.value = true;
        })
        .catch((err) => {
          console.log(err)
        })
      } else {
        store.dispatch("followStore/requestUnfollow", id.value)
        .then((res) => {
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