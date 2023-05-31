<template>
  <div>
    <div class="ml-4 flex">
      <img
        v-if="profile_user_data.profilePath != null"
        class="profile-image"
        :style="{ backgroundImage: `url(${profile_user_data.profilePath})` }"
      />

      <font-awesome-icon v-else class="profile-image-none" icon="fa-solid fa-user" />
      <div class="mx-auto font-spoq" style="transform: translateY(-2rem)">
        <div class="flex" @click="goToFollowList()">
          <div class="follower-and-following">
            <div>{{ profile_user_data.followerCnt }}</div>
            <div>팔로워</div>
          </div>
          <div class="follower-and-following">
            <div>{{ profile_user_data.followingCnt }}</div>
            <div>팔로잉</div>
          </div>
        </div>
        <div v-if="profile_user_data.username != my_id" class="my-3 ml-2">
          <button
            v-if="isFollowed"
            class="bg-zz-negative mt-2 ml-4 text-white text-sm font-spoq px-5 py-1 rounded-2xl w-20 h-8"
            @click="unFollowRequest"
          >
            팔로잉
          </button>
          <button
            v-else
            class="bg-zz-p mt-2 ml-4 text-white text-sm font-spoq px-5 py-1 rounded-2xl w-20 h-8"
            @click="followRequest"
          >
            팔로우
          </button>
        </div>
      </div>
    </div>
    <div class="mt-4 mb-4">
      <div class="flex">
        <div class="profile-title">{{ profile_user_data.nickname }}</div>
        <div v-if="profile_user_data.isManager == true">
          <font-awesome-icon icon="fa-solid fa-crown" class="text-zz-p ml-2" />
        </div>
      </div>

      <div class="text-zz-negative font-spoq text-xs my-auto">@{{ this.profile_user_data.username }}</div>
      <div class="mt-2 line-clamp-2 font-spoq text-zz-dark-input dark:text-white">
        {{ profile_user_data.profileMessage }}
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { computed, ref } from 'vue';
import { follow, unfollow, checkFollowState } from '@/api/follow.js';
export default {
  name: 'ProfileInfo',
  setup() {
    const my_id = localStorage.getItem('current_userid');
    const store = useStore();
    const router = useRouter();
    const profile_user_data = computed(() => store.state.profileStore.profile_user);

    let isFollowed = ref(false);

    const followState = () => {
      checkFollowState(
        profile_user_data.value.id,
        ({ data }) => {
          isFollowed.value = data.followState;
        },
        (error) => console.log(error),
      );
    };

    const goToFollowList = async () => {
      store
        .dispatch('followStore/getFollowerList', profile_user_data.value.id)
        .then(store.dispatch('followStore/getFollowingList', profile_user_data.value.id))
        .then(router.push(`/follow`));
    };
    const followRequest = () => {
      follow(
        profile_user_data.value.id,
        ({ data }) => {
          console.log(data);
          isFollowed.value = true;
          profile_user_data.value.followerCnt += 1;
        },
        (error) => {
          console.log(error);
        },
      );
    };
    const unFollowRequest = () => {
      unfollow(
        profile_user_data.value.id,
        ({ data }) => {
          console.log(data);
          isFollowed.value = false;
          profile_user_data.value.followerCnt -= 1;
        },
        (error) => {
          console.log(error);
        },
      );
    };
    setTimeout(() => followState(), 50);
    return {
      goToFollowList,
      followRequest,
      unFollowRequest,
      profile_user_data,
      my_id,
      isFollowed,
    };
  },
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
  @apply rounded-full bg-center bg-no-repeat;
}

.profile-image-none {
  width: 100px;
  height: 100px;
  background-size: cover;
  @apply rounded-full bg-center bg-no-repeat text-zz-light-p dark:text-zz-negative;
}
</style>
