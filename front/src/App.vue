<template>
  <div>
    <div class="main-margin">
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
window.oncontextmenu = function(event) {
  event.preventDefault(); // 기본 태그 기능 막기
  event.stopPropagation(); // 이벤트 전달 막기
  return false;
};

import { useDark } from '@vueuse/core';

const isDark = useDark();
export default {
  name: 'App',
  data() {
    return {
      isDark,
    };
  },
  components: {},
  methods: {},
  mounted() {},
};
</script>

<style scoped lang="postcss">
.main-margin {
  padding: 0 1.25rem;
  margin-bottom: 3.2rem;
  margin-top: 3.2rem;
  @apply px-5 dark:bg-zz-bd;
}
</style>
