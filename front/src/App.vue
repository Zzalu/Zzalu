<template>
  <div>
    <div class="main-margin px-5 dark:bg-zz-bd">
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
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
  margin-bottom: 3.5rem;
  margin-top: 3.5rem;
}
</style>
