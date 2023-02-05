import { getPopularGIF,getRecommendGIF } from "@/api/zzalList"

const zzalListStore = {
  namespaced: true,
  state: () => ({
    popular_gif_data: null,
    recommend_gif_data: null,
  }),
  mutations: {
    getPopularGIF(state, PopulardataGIFdata) {
      state.popular_gif_data = PopulardataGIFdata.data
    },
    getRecommendGIF(state, RecommendGIFdata) {
      state.recommend_gif_data = RecommendGIFdata.data
    }
  },
  actions: {
    getPopularGIFList({ commit }) {
      getPopularGIF(
        (data) => {
          console.log(data, '인기짤 get 성공');
          commit('getPopularGIF', data)
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
          commit('getRecommendGIF',data)
        },
        (err) => {
          console.log(err, '추천짤 get 실패');
        }
      )
    }
  }
}

export default zzalListStore;