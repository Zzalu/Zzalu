const searchModalStore = {
  namespace: true,
  state:() => ({
      //하단내브바 서치모달
      open_search_modal: true,
    }),
  mutations: {
    //하단내브바 서치모달
    open_modal(state) {
      state.open_search_modal ? false : true;
    },
  },
  actions: {},
  getters: {},
};

export default searchModalStore;
