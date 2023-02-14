import { getQuiet, createQuietChat, nosearchAllLikeroom, SearchCreatedRecentroom, PastMessage, UserStats, noSearchBookmarkRecentroom, SearchBookmarkRecentroom, nosearchBookmarkLikeroom, searchBookmarkLikeroom,
  noSearchallrecentroom, getHotQuietList, getOnlySearch, noSearchCreatedRecentroom, noSearchCreatedLikeroom, SearchAllLikeroom, SearchCreatedLikeroom, RoomLike } from "@/api/quietChatList";

const quietChatStore = {
  namespaced: true,
  state: () => ({
    open_chat_info: false,
    open_chat_id: null,
    quiet_list: null,
    hot_quiet_list: null,
    past_message : [],
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
    },
    GET_PAST_MESSAGE(state, pastMessage) {
      for (let i =0; i<pastMessage.data.length; i++) {
        if (pastMessage.data[i].type == 'TALK') {
          let tmp = ""
          let sendtime = ""
          tmp += pastMessage.data[i].sendDate[11]
          tmp += pastMessage.data[i].sendDate[12]
          if (Number(tmp) >= 12) {
            sendtime += '오후 '
            sendtime += Number(tmp-12)
          } else {
            sendtime += '오전 '
            sendtime += pastMessage.data[i].sendDate[11]
            sendtime += pastMessage.data[i].sendDate[12]
          }
          sendtime += ':'
          sendtime += pastMessage.data[i].sendDate[14]
          sendtime += pastMessage.data[i].sendDate[15]

          pastMessage.data[i].sendDate = JSON.parse(JSON.stringify(sendtime));
        } else {
          continue
        }
      }
      pastMessage.data = [...pastMessage.data].reverse()
      state.past_message = pastMessage.data
    },
    GET_NOSEARCH_BOOKMARK_RECENT(state, nosearchbookmarkrecent) {
      state.hot_quiet_list = nosearchbookmarkrecent.data
    },
    GET_SEARCH_BOOKMARK_RECENT(state, searchbookmarkrecent) {
      state.hot_quiet_list = searchbookmarkrecent.data
    },
    GET_NOSEARCH_BOOKMARK_LIKE(state, nosearchbookmarklike) {
      state.hot_quiet_list = nosearchbookmarklike
    },
    SEARCHBOOKMARKLIKE(state, searchbookmarklike){ 
      state.hot_quiet_list = searchbookmarklike
    }
  },
  actions: {
    // 유저 스탯
    postUserStat(params,datas) {
      UserStats(
        params,
        datas,
        (data) => {
          console.log('스탯보내기 성공', data)
        },
        (err) => {
          console.log('스탯보내기 실패',err);
        }
      )
    },
    getPastMessage({ commit }, params) {
      PastMessage(
        params,
        (data) => {
          console.log(data, '과거 데이터 가져오기 성공');
          commit('GET_PAST_MESSAGE',data)
        },
        (err) => {
          console.log(err, '과거 데이터 가져오기 실패');
        }
      )
    },
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
    create_quiet_room(params, datas) {
      createQuietChat(
        params,
        datas,
        (data) => {
          console.log(data, '고독방 생성 성공');
          // commit('CREATE_QUIET_CHAT', data)
        },
        (err) => {
          console.log(err, ' 고독방 생성 실패');
        }
      )
    },
    getHotQuietList({ commit }) {
      getHotQuietList(
        (data) => {
          console.log(data, '고독방 인기 리스트 get 성공');
          commit('GET_HOT_QUIET', data)
        },
        (err) => {
          console.log(err, '고독방 인기 리스트 get 실패');
        }
      )
    },

    // 좋아요

    chatRoomLike(params, datas) {
      RoomLike(
        params,
        datas,
        (data) => {
          if (data.data == "true") {
            console.log(data.data, '고독방 좋아요 성공');
          } else {
            console.log(data.data, "고독방 좋아요 실패")
          }
        },
        (err) => {
          console.log(err, '고독방 좋아요 실패');
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
          commit('GET_ONLY_SEARCH', data)
        },
        (err) => {
          console.log(err, 'only search 고독방 get 실패');
        }
      )
    },
    noSearchAllRecent({ commit }) {
      noSearchallrecentroom(
        (data) => {
          console.log(data, '검색x, 전체고독, 최신대화 성공');
          commit('GET_ONLY_SEARCH', data)
        },
        (err) => {
          console.log(err, '검색x, 전체고독, 최신대화 실패');
        }
      )
    },
    nosearchAllLike({ commit }) {
      nosearchAllLikeroom(
        (data) => {
          console.log(data, '검색x 전체고독, 좋아요순 성공');
          commit('GET_ONLY_SEARCH', data)
        }, 
        (err) =>
        {
          console.log(err, '검색x 전체고독, 좋아요순 실패');
        }
      )
    },
    



    // 검색x + 
    SearchCreatedRecent({ commit }, params) {
      SearchCreatedRecentroom(
        params,
        (data) => {
          console.log(data, '검색, 내가만든, 최신대화순 성공');
          commit('GET_NOSEARCH_CRATED_RECENT', data)
        },
        (err) => {
          console.log(err, '검색, 내가만든, 최신대화순 실패');
        }
      )
    },
    noSearchCreatedRecent({ commit }, params) {
      noSearchCreatedRecentroom(
        params,
        (data) => {
          console.log(data, '검색x, 내가만든, 최신대화순 성공');
          commit('GET_NOSEARCH_CRATED_RECENT', data)
        },
        (err) => {
          console.log(err, '검색x, 내가만든, 최신대화순 실패');
        }
      )
    },
    noSearchCreatedLike({ commit }, params) {
      noSearchCreatedLikeroom(
        params,
        (data) => {
          console.log(data, '검색x, 내가만든, 좋아요순 성공');
          commit('GET_NOSEARCH_CREATED_LIKE', data)
        },
        (err) => {
          console.log(err, '검색x, 내가만든, 좋아요순 실패');
        }
      )
    },
    searchAllLike({ commit }, params) {
      SearchAllLikeroom(
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
    searchCreatedLike({ commit }, params) {
      SearchCreatedLikeroom(
        params,
        (data) => {
          console.log(data, '검색, 내가 만든, 좋아요순 성공');
          commit('GET_SEARCH_CREATED_LIKE', data)
        },
        (err) => {
          console.log(err, '검색, 내가 만든, 좋아요순 실패');
        }
      )
    },

    // 즐겨찾기

    noSearchBookmarkRecent({ commit }) {
      noSearchBookmarkRecentroom (
        (data) => {
          console.log(data,'검색x, 즐겨찾기, 최근활동순 성공');
          commit('GET_NOSEARCH_BOOKMARK_RECENT',data)
        },
        (err) => {
          console.log(err,'검색x, 즐겨찾기, 최근활동순 실패');
        }
      )
    },
    SearchBookmarkRecent({ commit } , params) {
      SearchBookmarkRecentroom (
        params,
        (data) => {
          console.log(data, '검색, 즐겨찾기, 최근활동순 성공');
          commit('GET_SEARCH_BOOKMARK_RECENT',data)
        },
        (err) => {
          console.log(err, '검색, 즐겨찾기, 최근활동순 실패');
        }
      )
    },
    nosearchBookmarkLike({ commit },params) {
      nosearchBookmarkLikeroom (
        params,
        (data) => {
          console.log(data,'검색x, 즐겨찾기, 좋아요순 성공');
          commit('GET_NOSEARCH_BOOKMARK_LIKE',data)
        },
        (err) => {
          console.log(err,'검색x, 즐겨찾기, 좋아요순 실패');
        }
      )
    },
    searchBookmarkLike({ commit }, params) {
      searchBookmarkLikeroom (
        params,
        (data) => {
          console.log(data,'검색, 즐겨찾기, 좋아요순 성공');
          commit('SEARCHBOOKMARKLIKE', data)
        },
        (err) => {
          console.log(err, '검색, 즐겨찾기 좋아요순 실패');
        }
      )
    }
  }
};

export default quietChatStore;