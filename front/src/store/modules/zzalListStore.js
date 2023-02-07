import { getPopularGIF,getRecommendGIF, getFirstRandomGIF, getMoreRandomGIF } from "@/api/zzalList"

const zzalListStore = {
  namespaced: true,
  state: () => ({
    popular_gif_data: null,
    recommend_gif_data: null,
    random_gif_data: null,
  }),
  mutations: {
    GET_POPULAR_GIF(state, PopulardataGIFdata) {
      state.popular_gif_data = PopulardataGIFdata.data
    },
    GET_RECOMMEND_GIF(state, RecommendGIFdata) {
      state.recommend_gif_data = RecommendGIFdata.data
    },
    GET_FIRST_RANDOM_GIF(state, RandomFirstGIFdata) {
      state.random_gif_data = RandomFirstGIFdata.data
    },
    GET_MORE_RANDOM_GIF(state, RandomMoreGIFdata) {
      state.random_gif_data = [...state.random_gif_data, ...RandomMoreGIFdata.data]
    }
  },
  actions: {
    getPopularGIFList({ commit }) {
      getPopularGIF(
        (data) => {
          console.log(data, '인기짤 get 성공');
          commit('GET_POPULAR_GIF', data)
        },
        (err) => {
          console.log(err, '인기짤 get 실패');
        }
      )
    },
    getRecommendGIFList({ commit }) {
      getRecommendGIF(
        (data) => {
          console.log(data, '추천짤 get 성공');
          commit('GET_RECOMMEND_GIF',data)
        },
        (err) => {
          console.log(err, '추천짤 get 실패');
        }
      )
    },
    getFirstRandomGIFList({ commit }) {
      getFirstRandomGIF (
        (data) => {
          console.log(data, '겟퍼스트랜덤');
          commit('GET_FIRST_RANDOM_GIF', data)
        },
        (err) => {
          console.log(err, '실패');
        }
      )
    },
    getMoreRandomGIFLIST({ commit },params) {
      getMoreRandomGIF (
        params,
        (data) => {
          console.log(data, '모얼랜덤')
          commit('GET_MORE_RANDOM_GIF', data)
        },
        (err) => {
          console.log(err, '모얼실패');
        }
      )
    }
  }
}

export default zzalListStore;