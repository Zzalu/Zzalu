<!-- 역대 명예의 전당 Main Page -->
<template>
  <div>
    <!-- logo  얘 로고같은데 왜 밑에 불러도 맨 밑에 붙을까요-->

    <small-logo-top-nav></small-logo-top-nav>

    <!-- 오늘 진행 중인 제목학원  (왜 계속 처 붙음) -->
    <div>
      <p class="focus-text">오늘 진행 중인 제목학원</p>
      <hr class="mb-3 border-0 h-1 bg-zz-light-input dark:bg-zz-dark-div" />

      <!-- 현재 진행중인 제목학원  item-->

      <!-- 현재 진행중인 제목학원 가져와야 될듯함 -->
      <div class="flex justify-center">
        <title-competition-list-big-item
          class="academy-list"
          :title_competition="title_competition"
          @click="goToTitleCompetition(title_competition.openDate)"
        />
      </div>
    </div>

    <div>
      <!-- 역대  제목학원  -->
      <p class="focus-text">역대 제목학원</p>
      <hr class="mb-3 border-0 h-1 bg-zz-light-input dark:bg-zz-dark-div" />

      <!--역대 제목학원 List -->
      <TitleCompetitionListsmall/>
      <div class="pb-8"></div>
    </div>
    <main-bottom-nav />
    <!-- bottom nav bar -->
  </div>
</template>

<script>
import SmallLogoTopNav from "@/components/Common/NavBar/SmallLogoTopNav.vue";
import TitleCompetitionListsmall from "@/components/WholeOfFrame/TitleCompetitionListsmall";
// import TitleCompetitionListItem from "@/components/WholeOfFrame/item/TitleCompetitionListItem.vue";
import MainBottomNav from "@/components/Common/NavBar/MainBottomNav.vue";

import TitleCompetitionListBigItem from "@/components/WholeOfFrame/item/TitleCompetitionListBigItem.vue";
import { getTitleCompetition } from "@/api/titleCompetition";
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
export default {
  components: {
    TitleCompetitionListBigItem,
    TitleCompetitionListsmall,
    SmallLogoTopNav,
    MainBottomNav,
  },

  setup() {
    const router = useRouter();
    // const todayDate = getCurrentDate();

    // console.log(todayDate);
    //현재 진행중인 제목학원 object 조회하기

    let title_competition = ref();

    //현재 날짜 구하기 (yyyy-mm--dd)
    // const getCurrentDate = () => {
    //   let today = new Date();
    //   let year = today.getFullYear();
    //   var month = ("0" + (today.getMonth() + 1)).slice(-2);
    //   let date = today.getDate(); // 일

    //   console.log(year + "-" + month + "-" + date);
    //   return year + "-" + month + "-" + date;
    // };

    getTitleCompetition(
      "2023-02-15",
      (data) => {
        //제목학원 출력
        console.log("[제목학원 respose] " + JSON.stringify(data.data));

        // getTitleCompetition -> reponseDto 에 "openDate가 없음 따라서 여기서 걍 넣어줌"
        title_competition.value = data.data;
        title_competition.value.openDate = "2023-02-15";

        //**생각한대로 데이터가 안넘어감 아마 저 박스가 만들어질때 값을 안주는거같음 **

        //console.log("[제목학원 respose] " + JSON.stringify(title_competition.value));
        //console.log(title_competition.value.openDate);
      },
      (error) => {
        console.log(error);
      }
    );

    //해당 날짜의 제목학원 페이지로 이동
    const goToTitleCompetition = (open_date) => {
      console.log(JSON.stringify(title_competition.value));
      console.log(open_date);
      router.push(`/title-competition/${open_date}`);
    };

    onMounted(() => {
      console.log("on mounted");

      getTitleCompetition(
        "2023-02-15",
        (data) => {
          //제목학원 출력
          console.log(
            "[제목학원 ddddddddddrespose] " + JSON.stringify(data.data)
          );

          // getTitleCompetition -> reponseDto 에 "openDate가 없음 따라서 여기서 걍 넣어줌"
          title_competition.value = data.data;
          title_competition.value.openDate = "2023-02-15";

          //**생각한대로 데이터가 안넘어감 아마 저 박스가 만들어질때 값을 안주는거같음 **

          //console.log("[제목학원 respose] " + JSON.stringify(title_competition.value));
          //console.log(title_competition.value.openDate);
        },
        (error) => {
          console.log(error);
        }
      );
    });

    return {
      title_competition,
      goToTitleCompetition,
      // getCurrentDate,
      onMounted,
      // todayDate,
    };
  },
};
</script>

<style scoped lang="postcss">
.focus-text {
  @apply font-bhs text-2xl line-clamp-1 mt-5 dark:text-white;
}

.academy-list {
  box-shadow: 0 0 7px black;
  @apply mt-2;
}
</style>
