<template>
  <follow-top-nav></follow-top-nav>
  <div class="grid grid-cols-2 text-center">
    <div class="py-2 border-b-2">팔로워
      <follower-list>
        {{ this.followers }}
      </follower-list>
    </div>
    <div class="py-2 border-b-2">팔로잉
      <following-list>
        {{ this.followings }}
      </following-list>
    </div>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
import { useStore } from 'vuex';
import FollowTopNav from "../../components/Common/NavBar/FollowTopNav.vue"
import MainBottomNav from "../../components/Common/NavBar/MainBottomNav.vue"
import FollowingList from "@/components/Profile/FollowingList"
import FollowerList from "@/components/Profile/FollowerList"

export default {
  name: "FollowView",
  components: {
    FollowTopNav,
    MainBottomNav,
    FollowingList,
    FollowerList,
  },
  setup() {
    const store = useStore();
    // const router = useRouter();

    const followings = store.getters['followStore/getFollowings']
    const followers = store.getters['followStore/getFollowers']
    console.log()

    return {
      followings, followers
    }

  },
  methods: {
    GetFollower() {
      let member_id = 1
      this.get_follower(member_id)
      this.goFollow
    },
    GetFollowing() {
      let member_id = 1
      this.get_following(member_id)
      this.goFollow
    }
  }
}
</script>

<style>

</style>