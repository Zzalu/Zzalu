<template>
  <div class="profile-title mb-1">스탯</div>
  <div id="chart" class="rounded-lg">
    <div>{{ stat_dirty }}</div>
    <apexchart type="bar" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
// import { useStore } from "vuex";
// import { computed, onBeforeMount } from "@vue/runtime-core";
// import { watch } from 'vue'
export default {
  name: "UserStats",
  // components: {
  //   apexcharts: VueApexCharts,
  // },
  data: function () {
    return {
      series: 
      [{
        data: this.nums_data
        }],
      chartOptions: {
        grid: {
          show: false,
        },
        chart: {
          type: "bar",
          height: 200,
          // width: 1,
          background: "#AA8AD4",
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
          categories: this.title_data,
          labels: {
            show: false,
            style: {
              colors: ["#FFFFFF"],
            },
          },
        },
        yaxis: {
          labels: {
            style: {
              colors: ["#FFFFFF"],
            },
          },
        },
      },
    };
  },
  setup() {
    // const store = useStore();
    // const user_id = window.localStorage.getItem("profile_id");
    // onBeforeMount(() => {
    //   store.dispatch("profileStore/getProfileStats", user_id);
      // var titles = new Array();
      // var nums = new Array();
      // stats.data.forEach( stat => {
      //   console.log(stat)
      //   // stat.tag.push(titles)
      //   // stat.count.push(nums)
      // });
      // console.log(titles)
      // console.log(nums)
    // });
    // const stat_dirty = computed(
    //   () => store.state.profileStore.profile_user.stats
    // );

    // var titles = new Array();
    // var nums = [];

    // watch(stat_dirty, (nv) => {
    //   if (nv) {
    //     nv.data.forEach(element => {
    //       titles.push(element.tag)
    //       nums.push(element.count)
    //     });
    //   }
    // })
    
    // const title_data = localStorage.getItem('stat_title').split(',')
    // console.log(title_data)
    // const nums_data = localStorage.getItem('stat_nums').split(',')
    return {
      // stat_dirty,
      // title_data,
      // nums_data

    };
  },
  watch: {
    stat_dirty: function (nv) {
  //     console.log(nv.data,"test");
      var titles = new Array();
      var nums = new Array();
      if (nv) {
        nv.data.forEach(element => {
          titles.push(element.tag)
          nums.push(element.count)
        });

  //       console.log(nv, "ㅇ게ㅜ")
  //       nv.data.forEach((value, index, array) => {
  //         console.log(array);
  //         console.log(`${index} ${value}`, "이건뭐야");
  //         titles.push(value.tag);
  //         nums.push(value.count)
          // console.log(titles,'titles');
          // console.log(typeof(titles))
  //         localStorage.setItem('stat_num', nums)
  //         localStorage.setItem('stat_title', titles)
  //         console.log(typeof(localStorage.getItem('stat_title')))
  //       });
  //       for (i in this.stat_dirty) {
  //         titles.push(this.stat_dirty[i].tag)
  //       }
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
</style>
