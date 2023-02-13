<template>
  <div class="profile-title" v-if="stat_dirty">
    스탯 <button @click="test">보기</button>
    <div>
  <div class="profile-title"> {{ profile_user_data.nickname }} {{ zzalMBTI }} </div>
      <div id="chart" class="mb-10">
        <apexchart
          type="bar"
          :options="chartOptions"
          :series="series"
        ></apexchart>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "@vue/runtime-core";
export default {
  data(){
    return {
      chartOptions : null,
      series : null,
    };
  },
  setup() {
    const store = useStore();
    const zzalMBTI = null
    // const profile_user = localStorage.getItem('profile_user')
    // onBeforeMount(() => {
    //   if (this.stat_dirty.data[0].tag == "게임") {
    //       this.zzalMBTI = '는 컴퓨터랑 연애한다'
    //     } else if (this.stat_dirty.data[0].tag == "애니") {
    //       this.zzalMBTI = '정도면... 갓반인이죠'
    //     } else if (this.stat_dirty.data[0].tag == "TV") {
    //       this.zzalMBTI = '뭐해? 나 누워서 TV봐...'
    //     } else if (this.stat_dirty.data[0].tag == "연예인") {
    //       this.zzalMBTI = '는... 우리 최애 보려고 살아요'
    //     } else if (this.stat_dirty.data[0].tag == "일상") {
    //       this.zzalMBTI = '는... 감정표현의 대가'
    //     } else if (this.stat_dirty.data[0].tag == "스포츠") {
    //       this.zzalMBTI = '팀은 언제 이기냐?'
    //     } else if (this.stat_dirty.data[0].tag == "동물") {
    //       this.zzalMBTI = '귀여운 동물들이 세상을 구한다!'
    //     } else if (this.stat_dirty.data[0].tag == "싸피") {
    //       this.zzalMBTI = '는 진정한 개발자... 제법 멋져'
    //     } else if (this.stat_dirty.data[0].tag == "음식") {
    //       this.zzalMBTI = '는 살려고 먹는게 아니라 먹으려고 살아'
    //     }
    // });
    const stat_dirty = computed(
      () => store.state.profileStore.profile_user.stats
    );
    const profile_user_data = computed(
      () => store.state.profileStore.profile_user
    );


    return {
      stat_dirty,
      zzalMBTI,
      profile_user_data
    };
  },
  methods: {
    test() {
      this.series= [
        {
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
          categories: [
            this.stat_dirty.data[0].tag,
            this.stat_dirty.data[1].tag,
            this.stat_dirty.data[2].tag,
            this.stat_dirty.data[3].tag,
          ],
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