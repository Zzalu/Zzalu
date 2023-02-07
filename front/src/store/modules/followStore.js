import { follow, unfollow, getFollowingList, getFollowerList, checkFollowState } from "@/api/follow";

const followStore = {
    namespaced: true,
    state: () => ({
    //   your_id: 0, // 팔로우, 언팔 상대의 id
    //   member_id: 0, // 리스트가 보고 싶은 상대의 id
      following_list: [],
      follower_list: [],
    }),
    mutations: {
    //   SET_YOUR_ID(state, request_member_id) {
    //     state.your_id = request_member_id;
    //   },
    //   SET_MEMBER_ID(state, request_member_id) {
    //     state.member_id = request_member_id;
    //   },
      SET_FOLLOWING_LIST(state, data) {
        state.following_list = data;
        console.log(data)
        console.log(state.following_list, "...")
      },
      SET_FOLLOWER_LIST(state, data) {
        state.follower_list = data;
      },
    },
    getters: {
        getYourId: (state) => state.your_id,
        getMemberId: (state) => state.member_id,
        getFollowings: (state) => state.following_list,
        getFollowers: (state) => state.follower_list
    },
    actions: {
      // 팔로잉 리스트 가져오기
      getFollowingList: async ({ commit }, member_id) => {
        console.log("member_id=", member_id)
        await getFollowingList(
            member_id,
            ({ data }) => {
                console.log(data);
                console.log(data.followings);
                commit('SET_FOLLOWING_LIST', data.followings);
                
            },
            (error) => {
              console.log("error");
              console.log(error);
            }
            
        );
        return true
        
      },

      // 팔로워 리스트 가져오기
      getFollowerList(commit, member_id) {
        getFollowerList(
            member_id,
            ({ data }) => {
                console.log(data);
                commit('SET_FOLLOWER_LIST', data.followers);
            },
            (error) => console.log(error),
        );
    
      },

      // 팔로우 요청
      requsetFollow(your_id) {
        follow(
            your_id,
            ({ data }) => {
                console.log(data);
            },
            (error) => console.log(error),
        );
      },

      // 언팔로우 요청
      requestUnfollow(your_id) {
        unfollow(
            your_id,
            ({ data }) => {
                console.log(data);
            },
            (error) => console.log(error),
        );
      },

      // 팔로우 상태 확인
      checkFollowState(your_id) {
        checkFollowState(
            your_id,
            ({ data }) => {
                console.log(data);
            },
            (error) => console.log(error),
        );
      },
    },
  };
  
  export default followStore;