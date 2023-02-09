<template>
<!-- <div class="profile-title">스탯</div>
  <div class="user-stats">
    <div class="flex">
      <div class="stat-hashtag">#고양이들</div>
      <div class="graph">100%</div>
    </div>
    <div class="flex">
      <div class="stat-hashtag">#겁나긴해시태그</div>
      <div class="graph">10%</div>
    </div>
    <div class="flex">
      <div class="stat-hashtag">#hello~</div>
      <div class="graph">1%</div>
    </div>
    <div class="flex justify-end">
      <div class="result-stat">{{}}님은 00 입니다</div>
    </div>
  </div> -->
    <div id="chart" class="rounded-lg">
      <apexchart type="bar" :options="chartOptions" :series="series"></apexchart>
    </div>
</template>

<script>
// import { useStore } from "vuex";
// import { onBeforeMount } from "@vue/runtime-core";
export default {
  name: "UserStats",
  // components: {
  //   apexcharts: VueApexCharts,
  // },
  data: function() {
    return {
          series: [{
            data: [15, 11, 9, 3]
          }],
          chartOptions: {
            grid: {
              show: false,
            },
            chart: {
              type: 'bar',
              height: 350,
              // width: 1,
              background: '#AA8AD4'
            },
            colors:['#6750A4'],
            plotOptions: {
              bar: {
                borderRadius: 4,
                horizontal: true,
              }
            },
            dataLabels: {
              enabled: true,
            },
            xaxis: {
              categories: ['게임', '고양이', '스폰지밥', 'ㅋㅋㅋ'],
              labels: {
                show: false,
                style: {
                    colors: ['#FFFFFF'],
                  }
              }

            },
            yaxis: {

              labels: {
              style: {
                  colors: ['#FFFFFF'],
                }
              }

            }
          },
    }
  },
  setup() {
    // const store = useStore();
    // const user_id = window.localStorage.getItem("profile_id");


    // const stats = store.dispatch("profileStore/getProfileStats", user_id);
    // return {
    //   user_id,
    //   stats
    // };
  },
}
</script>

<style lang="postcss" scoped>
.user-stats {
  @apply bg-zz-s py-2 px-1 rounded mb-6 mt-1
}

.graph {
  @apply bg-white my-2 px-1 rounded
}

.stat-hashtag {
  width: 5rem;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
  display:inline-block;
  @apply text-white font-spoq mr-2 text-xs px-3 my-2 py-1
}

.result-stat {
  @apply text-white font-spoq font-bold mx-4
}
</style>