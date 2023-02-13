import { getQuiet, createQuietChat, getHotQuietList, getOnlySearch, noSearchCreatedRecentroom, noSearchCreatedLikeroom, SearchAllLikeroom, SearchCreatedLikeroom } from "@/api/quietChatList";

const quietChatStore = {
  namespaced: true,
  state: () => ({
    open_chat_info: false,
    open_chat_id: null,
    quiet_list: null,
    hot_quiet_list : null,
  }),
  mutations: {
    open_chat_info(state) {
      state.open_chat_info = true
    },
    close_chat_info(state) {
      state.open_chat_info = false,
        state.open_chat_id = null
    },
    open_chat_id(state, id) {
      state.open_chat_id = id
    },
    GET_QUIET_CHAT(state, quietChatList) {
      state.quiet_list = quietChatList.data
    },
    GET_HOT_QUIET(state, hotQuietChat) {
      state.hot_quiet_list = hotQuietChat.data
    },
    GET_ONLY_SEARCH(state, onlySearch) {
      state.quiet_list = onlySearch.data
    },
    GET_NOSEARCH_CRATED_RECENT(state, noSearchCreatedRecent) {
      state.quiet_list = noSearchCreatedRecent.data
    },
    GET_NOSEARCH_CREATED_LIKE(state, noSearchCreatedLike) {
      state.quiet_list = noSearchCreatedLike.data
    },
    GET_SEARCH_ALL_LIKE(state, searchAllLike) {
      state.quiet_list = searchAllLike.data
    },
    GET_SEARCH_CREATED_LIKE(state, searchCreatedLike) {
      state.quiet_list = searchCreatedLike.data
    }
  },
  actions: {
    getQuietList({ commit }) {
      getQuiet(
        (data) => {
          console.log(data, '고독방 리스트 get 성공');
          commit('GET_QUIET_CHAT', data)
        },
        (err) => {
          console.log(err, '고독방 리스트 get 실패');
        }
      )
    },
    create_quiet_room(params,datas) {
      createQuietChat(
        params,
        datas,
        (data) => {
          console.log(data, '고독방 생성 성공');
          // commit('CREATE_QUIET_CHAT', data)
        },
        (err) => {
          console.log(err,' 고독방 생성 실패');
        }
      )
    },
    getHotQuietList({ commit }) {
      getHotQuietList(
        (data) => {
          console.log(data, '고독방 인기 리스트 get 성공');
          commit('GET_HOT_QUIET',data)
        },
        (err) => {
          console.log(err, '고독방 인기 리스트 get 실패');
        }
      )
    },

    // 고독방검색 api


    // 검색만 이용

    onlySearchRoom({ commit }, params) {
      getOnlySearch(
        params,
        (data) => {
          console.log(data, 'only search 고독방 get 성공');
          commit('GET_ONLY_SEARCH',data)
        },
        (err) => {
          console.log(err, 'only search 고독방 get 실패');
        }
      )
    },

    // 검색x + 
    noSearchCreatedRecent({ commit }, params) {
      noSearchCreatedRecentroom (
        params,
        (data) => {
          console.log(data, '검색x, 내가만든, 최신대화순 성공');
          commit('GET_NOSEARCH_CRATED_RECENT',data)
        },
        (err) => {
          console.log(err, '검색x, 내가만든, 최신대화순 실패');
        }
      )
    },
    noSearchCreatedLike ({commit}, params) {
      noSearchCreatedLikeroom(
        params,
        (data) => {
          console.log(data, '검색x, 내가만든, 좋아요순 성공');
          commit('GET_NOSEARCH_CREATED_LIKE',data)
        },
        (err) => {
          console.log(err, '검색x, 내가만든, 좋아요순 실패');
        }
      )
    },
    searchAllLike ({commit}, params) {
      SearchAllLikeroom (
        params,
        (data) => {
          console.log(data, '검색, 전체고독방, 좋아요순 성공');
          commit('GET_SEARCH_ALL_LIKE', data)
        },
        (err) => {
          console.log(err, '검색, 전체고독방 좋아요순 실패');
        }
      )
    },
    searchCreatedLike({commit}, params) {
      SearchCreatedLikeroom (
        params,
        (data) => {
          console.log(data, '검색, 내가 만든, 좋아요순 성공');
          commit('GET_SEARCH_CREATED_LIKE',data)
        },
        (err) => {
          console.log(err, '검색, 내가 만든, 좋아요순 실패');
        }
      )
    }
  }
};

export default quietChatStore;