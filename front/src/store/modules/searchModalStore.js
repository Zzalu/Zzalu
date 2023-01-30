const searchModalStore = {
  namespaced: true,
  state:() => ({
      //하단내브바 서치모달
      open_search_modal: false,
      open_list_modal: false,
    }),
  mutations: {
    //하단내브바 서치모달
    open_search_modal(state) {
      state.open_search_modal ? state.open_search_modal = false : state.open_search_modal = true;
    },
    open_list_modal(state) {
      state.open_list_modal = true
    },
    close_list_modal(state) {
      state.open_list_modal = false
    }
  },
  actions: {},
  getters: {},
};

export default searchModalStore;
