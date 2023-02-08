import { getStoreList, createBoard } from "@/api/boardList"

const boardListStore = {
  namespaced: true,
  state: () => ({
    user_store_list: null,
    select_gif_id: null
  }),
  mutations: {
    GET_USER_STORE_LIST(state, user_store_data) {
      state.user_store_list = user_store_data.data
    },
    SELECT_GIF(state, gif_id) {
      state.select_gif_id = gif_id
    },
  },
  actions: {
    getUserStoreList({ commit }, params, data) {
      getStoreList(
        params,
        data,
        (res) => {
          console.log('유저 보관함 요청 성공', res);
          commit('GET_USER_STORE_LIST', res)
        },
        (err) => {
          console.log('유저 보관함 요청 실패', err);
        }
      )
    },
    createStoreBoard(params, datas) {
      createBoard(
        params,
        datas,
        (data) => {
          console.log(data, '보드 생성 성공');
        },
        (err) => {
          console.log(err, '보드 생성 실패');
        }
      )
    },
  },

}

export default boardListStore