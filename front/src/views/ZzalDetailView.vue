<template>
    <only-go-back-top-nav></only-go-back-top-nav>
    <!-- <kor-go-back-top-nav-bar></kor-go-back-top-nav-bar> -->
  <div class="h-screen">
    <div class="mt-6">
      <zzal-list-item></zzal-list-item>
      <zzal-info></zzal-info>
      <zzal-source-video></zzal-source-video>
    </div>
  </div>
  <main-bottom-nav></main-bottom-nav>
</template>

<script>
// import KorGoBackTopNavBar from '../components/Common/NavBar/KorGoBackTopNavBar.vue'
import OnlyGoBackTopNav from '../components/Common/NavBar/OnlyGoBackTopNav.vue'
import MainBottomNav from '../components/Common/NavBar/MainBottomNav.vue'
import ZzalListItem from '../components/ZzalDetail/ZzalListItem.vue'
import ZzalInfo from '@/components/ZzalDetail/ZzalInfo.vue'
import ZzalSourceVideo from '../components/ZzalDetail/ZzalSourceVideo.vue'

export default {
  name: "ZzalDetailView",
  components: { 
    ZzalListItem, 
    ZzalInfo,
    ZzalSourceVideo,
    MainBottomNav,
    // KorGoBackTopNavBar,
    OnlyGoBackTopNav,
    },
}
</script>

<style>

</style>
