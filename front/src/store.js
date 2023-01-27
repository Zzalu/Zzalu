import { createStore } from 'vuex'

const store = createStore({
  state() {
    return {
      //하단내브바 서치모달
      open_search_modal: false,
    }
  },
  mutations: {
    //하단내브바 서치모달
    open_modal(state) {
      state.open_search_modal = true
    }
  },
  actions: {

  },
  getters: {

  }
})

export default store