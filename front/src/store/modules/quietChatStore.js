const quietChatStore = {
    namespaced: true,
    state:() => ({
        open_chat_info : false,
        open_chat_id : null,
    }),
    mutations: {
        open_chat_info(state) {
            state.open_chat_info = true
        },
        close_chat_info(state) {
            state.open_chat_info = false
        },
        open_chat_id(state,id) {
            state.open_chat_id = id
        }
    }
};


export default quietChatStore;