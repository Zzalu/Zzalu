<template>
  <div>
    <only-small-logo-top-nav></only-small-logo-top-nav>
    <div>
      <!-- 오늘의 제목학원 header -->
      <div>
        <div>
          <span>{{ date }}</span>
          <h1>오늘의 제목학원</h1>
        </div>
        <button>역대 제목학원</button>
        <!-- 짤 -->
        <div><img src="../assets/logo.png" alt="짤" /></div>
      </div>

      <!-- TOP 5 -->
      <div>
        <ol>
          <li>
            <button>
              <span> 1위입니다 </span>
            </button>
            <font-awesome-icon icon="fa-regular fa-heart" />
          </li>
        </ol>
      </div>
      <!-- 댓글 -->
      <div></div>
    </div>
  </div>
</template>

<script>
import OnlySmallLogoTopNav from '../components/Common/NavBar/OnlySmallLogoTopNav.vue';
export default {
  components: { OnlySmallLogoTopNav },
  name: 'TitleCompetitionView',
  data() {
    return {
      date: '23.01.19',
    };
  },
};
</script>

<style></style>
