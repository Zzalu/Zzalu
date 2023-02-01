import { getQuiet, getGIF } from "@/api/quietChatList";

const quietChatStore = {
  namespaced: true,
  state: () => ({
    open_chat_info: false,
    open_chat_id: null,
    quiet_list: null,
    gif_data: null
  }),
  mutations: {
    open_chat_info(state) {
      state.open_chat_info = true
    },
    close_chat_info(state) {
      state.open_chat_info = false
    },
    open_chat_id(state, id) {
      state.open_chat_id = id
    },
    getQuietChat(state, quietChatList) {
      state.quiet_list = quietChatList
    },
    getGIF(state, GIFdata) {
      state.gif_data = GIFdata
    }
  },
  actions: {
    getQuietList({ commit }) {
      getQuiet(
        (data) => {
          console.log(data, 'dd');
          commit('getQuietChat', data)
        },
        (err) => {
          console.log(err);
        }
      )
    },
    getGIFList({ commit }) {
      getGIF(
        (data) => {
          console.log(data, 'POST요청성공');
          commit('getGIF', data)
        },
        (err) => {
          console.log(err, 'post');
        }
      )
    }
  }
};


export default quietChatStore;