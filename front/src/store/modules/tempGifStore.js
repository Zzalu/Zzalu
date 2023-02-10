import { getAllTempGif, putTempGif, deleteTempGif, postTempGif } from "@/api/tempGif"

const tempGifStore = {
	namespaced: true,
	state: () => ({
    // 임시 게시물 전체 조회
    temp_gif_list: null,
	}),
	mutations: {
    SET_TEMP_GIF_LIST(state, data) {
      state.temp_gif_list = data;
    },
	},
  getters: {
    getTempGifList: (state) => state.temp_gif_list,
  },
	actions: {
    // 임시 게시물 전체 조회
    getAllTempGif: ({ commit }) => {
      getAllTempGif(
        ({ data }) => {
          commit('SET_TEMP_GIF_LIST', data.tempGifList);
        },
      )
    },
    // 임시 게시물 +1
    putTempGif: (params, temp_id) => {
      putTempGif(
        params,
        temp_id,
        ({ data }) => {
          console.log(data, "성공입니다.");
        },
        (error) => {
          console.log(error, "실패입니다.");
          alert(error.response.data.message);
        },
      )
    },
    // 임시 게시물 삭제
    deleteTempGif: (params, temp_id) => {
      deleteTempGif(
        params,
        temp_id,
        ({ data }) => {
          console.log(data, "성공입니다.");
        },
        (error) => {
          console.log(error, "실패입니다.");
          alert(error.response.data.message);
        },
      )
    },
	},
  
}
export default tempGifStore