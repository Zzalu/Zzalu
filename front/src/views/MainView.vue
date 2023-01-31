<template>
  <div>
    <div class="body"
    v-if="check_search_modal"
    @click="close_modal"
    ></div>
    <OnlyBigLogoTopNav/>
    <div>
      <p>역대 제목학원 명예의 전당</p>
      <hr>
      <div>
        카드
      </div>
    </div>
    <div>
      <p>user.id님을 위한 추천 짤</p>
      <hr>
      <div>
        이미지
      </div>
    </div>
    <div>
      <p>오늘의 인기 짤</p>
      <hr>
      <div>
        이미지
      </div>
    </div>
    <div>
      <p>요새 핫한 고독방</p>
      <hr>
      <div>
        카드
      </div>
    </div>
    <SearchView/>
    <MainBottomNav/>
  </div>
</template>

<script>
import SearchView from './SearchView'
import MainBottomNav from "../components/Common/NavBar/MainBottomNav"
import OnlyBigLogoTopNav from "../components/Common/NavBar/OnlyBigLogoTopNav"
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";

export default {
  name: "MainView",
  setup() {
    const store = useStore();

    const check_search_modal = computed(
      () => store.state.searchModalStore.open_search_modal
    );
    const close_search_modal = () => {
      store.commit('searchModalStore/open_search_modal')
    };
    return {
      check_search_modal,
      close_search_modal
    }
  },
  components : {
    SearchView,
    MainBottomNav,
    OnlyBigLogoTopNav,
  },
  methods: {
    close_modal() {
      if (this.check_search_modal == true) {
        this.close_search_modal()
      } 
    }
  }
}
</script>

<style scoped lang="postcss">
.body {
  height:4rem;
  @apply fixed inset-x-0 top-0 z-10
}
</style>
