<template>
  <div class="stat-title"> 짤BTI: {{ profile_user_data.nickname }} {{ zzalMBTI }} </div>
    <button class="profile-title"  @click="test">
      <font-awesome-icon icon="fa-solid fa-circle-plus" class="text-sm"/>
      상세 스탯 보기 🔍
      <!-- <font-awesome-icon icon="fa-solid fa-magnifying-glass-chart"/> -->
    </button>
    <div>
      <div id="chart" class="mb-10">
        <apexchart
          v-if="chartOptions&&series"
          type="bar"
          :options="chartOptions"
          :series="series"
        ></apexchart>
      </div>
    </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
export default {
data: function() {
    return {
      series:null,
      chartOptions: null,
    //   chartShow: false,
    //   series: [{
    //       data: [
    //         this.stat_dirty.data[0].count,
    //         this.stat_dirty.data[1].count,
    //         this.stat_dirty.data[2].count,
    //         this.stat_dirty.data[3].count,
    //       ],
    //     }],
    //     chartOptions: {
    //             grid: {
    //               show: false,
    //             },
    //             chart: {
    //               type: "bar",
    //               height: 200,
    //               // width: 1,
    //               background: "#AA8AD4",
    //             },
    //             colors: ["#6750A4"],
    //             plotOptions: {
    //               bar: {
    //                 borderRadius: 4,
    //                 horizontal: true,
    //               },
    //             },
    //             dataLabels: {
    //               enabled: true,
    //             },
    //             xaxis: {
    //               categories: [
    //                 this.stat_dirty.data[0].tag,
    //                 this.stat_dirty.data[1].tag,
    //                 this.stat_dirty.data[2].tag,
    //                 this.stat_dirty.data[3].tag,
    //               ],
    //               labels: {
    //                 show: false,
    //                 style: {
    //                   colors: ["#FFFFFF"],
    //                 },
    //               },
    //             },
    //             yaxis: {
    //               labels: {
    //                 style: {
    //                   colors: ["#FFFFFF"],
    //                 },
    //               },
    //             },
    //             title: {
    //               text: '짤 통계',
    //               align: 'left',
    //               margin: 10,
    //               offsetX: 10,
    //               offsetY: 10,
    //               floating: false,
    //               style: {
    //                 fontSize:  '14px',
    //                 fontWeight:  'bold',
    //                 color:  '#FFFFFF'
    //               },
    //           }
    //           }
  
    }
  },
  setup() {
    const store = useStore();
    const zzalMBTI = computed(
      () => store.state.profileStore.profile_user.typeMsg
    );

    const stat_dirty = computed(
      () => store.state.profileStore.profile_user.stats
    );
    const profile_user_data = computed(
      () => store.state.profileStore.profile_user
    );
    
    return {
      stat_dirty,
      zzalMBTI,
      profile_user_data,
    };
  },
  methods: {
    test() {
      this.series= [
        {
          name: "짤 저장 횟수",
          data: [
            this.stat_dirty.data[0].count,
            this.stat_dirty.data[1].count,
            this.stat_dirty.data[2].count,
            this.stat_dirty.data[3].count,
          ],
        },
      ],
      this.chartOptions = {
        grid: {
          show: false,
        },
        chart: {
          type: "bar",
          height: 200,
          // width: 1,
          // background: "#AA8AD4",
        },
        colors: ["#6750A4"],
        plotOptions: {
          bar: {
            borderRadius: 4,
            horizontal: true,
          },
        },
        dataLabels: {
          enabled: true,
        },
        xaxis: {
          categories: [
            this.stat_dirty.data[0].tag,
            this.stat_dirty.data[1].tag,
            this.stat_dirty.data[2].tag,
            this.stat_dirty.data[3].tag,
          ],
          labels: {
            show: false,
            style: {
              colors: ["#6750A4"],
            },
          },
        },
        yaxis: {
          labels: {
            style: {
              colors: ["#AA8AD4"],
              fontSize: '12px',
            },
          },
        },
      }
    },
  },
};
</script>

<style lang="postcss" scoped>
.user-stats {
  @apply bg-zz-s py-2 px-1 rounded mb-6 mt-1;
}

.graph {
  @apply bg-white my-2 px-1 rounded;
}

.stat-hashtag {
  width: 5rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: inline-block;
  @apply text-white font-spoq mr-2 text-xs px-3 my-2 py-1;
}

.result-stat {
  @apply text-white font-spoq font-bold mx-4;
}

.stat-title {
  @apply text-xl font-bold font-spoq dark:text-white
}

.see-stat-button {
  @apply font-spoq font-bold
}
</style>