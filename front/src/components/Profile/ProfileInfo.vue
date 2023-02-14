<template>
    <div class="ml-4 flex">
      <img v-if="this.profile_user_data.profilePath != null" class="profile-image" :style="{ backgroundImage: `url(${this.profile_user_data.profilePath})` }" />
      <img v-else class="profile-image" src="@/assets/zzalu_logo_light.png" />
      <!-- <img src="" alt=""> -->
      <div class="mx-auto">
        <div class="flex">
          <div
            @click="[GetFollower(), goFollow()]"
            class="follower-and-following"
          >
            <div>{{ this.profile_user_data.followerCnt }}</div>
            <div>팔로워</div>
          </div>
          <div
            @click="[GetFollowing(), goFollow()]"
            class="follower-and-following"
          >
            <div>{{ this.profile_user_data.followingCnt }}</div>
            <div>팔로잉</div>
          </div>
        </div>
        <div v-if="this.profile_user_data.username != this.me">
          <button
            class="bg-zz-p mt-2 ml-4 text-white text-xl font-spoq px-5 py-1 rounded"
            @click="follow_request"
          >
            팔로우
          </button>
        </div>
      </div>
    </div>
    <div class="mt-4 mb-4">
      <div class="flex">
        <div class="profile-title">{{ this.profile_user_data.nickname }}</div>
          <div v-if="this.profile_user_data.isManager == true">
            <font-awesome-icon icon="fa-solid fa-crown" class="text-zz-p ml-2"/>
        </div>
      </div>

      <div class="text-zz-negative font-spoq text-xs my-auto">
        @{{ this.profile_user_data.username }}
      </div>
      <div class="mt-2 line-clamp-2 font-spoq text-zz-dark-input dark:text-white">
        {{ this.profile_user_data.profileMessage }}
      </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { computed } from "@vue/runtime-core";

export default {
  name: "ProfileInfo",
  data() {
    return {
      me: localStorage.getItem("current_userid")
      // myProfile: false,
    };
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const profile_user_data = computed(
      () => store.state.profileStore.profile_user
    );
    // const my_data = computed(
    //   () => store.state.userStore
    // );
    const get_follower = (member_id) => {
      console.log("member_id", member_id)
      store.dispatch("followStore/getFollowerList", member_id)
    };
    

    const get_following = (member_id) => {
      console.log("member_id", member_id)
      store.dispatch("followStore/getFollowingList", member_id)
    
    };
    const goFollow = function() {router.push({name: "follow", params: {username: this.profile_user_data.username}})}

    // const follow_request = () => {
    //   if (followState.value == false) {
    //     store.dispatch("followStore/requsetFollow", id.value)
    //     .then((res) => {
    //       console.log(res)
    //       followState.value = true;
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    //   } else {
    //     store.dispatch("followStore/requestUnfollow", id.value)
    //     .then((res) => {
    //       console.log(res)
    //       followState.value = false;
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    //   }
    // };

    return {
      get_follower, get_following, goFollow,
      profile_user_data,
      // follow_request
    }

  },
  methods: {
    GetFollower() {
      let member_id = this.profile_user_data.id
      console.log(this.profile_user_data)
      console.log(member_id, "멤버")
      this.get_follower(member_id)
      this.goFollow
      // console.log("followers=", this.followers) // undefined
    },
    GetFollowing() {
      let member_id = this.profile_user_data.id
      this.get_following(member_id)
      this.goFollow
      // console.log("followings=", this.followings) // undefined
    }
  }
};

</script>

<style scoped lang="postcss">
.follower-and-following {
  @apply mt-10 text-center mx-2 text-zz-s font-spoq;
}

.profile-image {
  width: 100px;
  height: 100px;
  background-size: cover;
  /* max-width: 100px;
    max-height: 100px; */
  /* object-fit: cover; */
  @apply rounded-full bg-center bg-no-repeat;
}

</style>