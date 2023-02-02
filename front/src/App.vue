<template>
  <div>
    <div class="my-nav-height px-5">
      <div>
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import { useDark } from "@vueuse/core";

const isDark = useDark();
export default {
  name: 'App',
  data() {
    return {
      isDark
    }
  },
  components: {},
  methods: {},
  mounted() {
    // this.darkmode = this.userInfo.company_name || "";
},
};
</script>

<style>
.main-margin {
  padding: 0 1.25rem;
  margin-bottom: 3.5rem;
  margin-top: 3.5rem;
}
</style>
