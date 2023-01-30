import Vuex from 'vuex';

// 작성한 모듈을 가져오기
import userStore from '@/store/modules/userStore.js';
import titleCompetitionStore from '@/store/modules/titleCompetitionStore.js';
import searchModalStore from '@/store/modules/searchModalStore.js'

const store = new Vuex.Store({
  modules: {
    // 키 : 값 형태로 저장됨
    userStore,
    titleCompetitionStore,
    searchModalStore,
  },
});
export default store;
