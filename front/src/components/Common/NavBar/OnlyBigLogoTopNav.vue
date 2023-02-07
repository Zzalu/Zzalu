<template>
  <!-- 큰 로고만있는 위쪽 nav -->
  <div
    class="
      h-nav-height
      fixed
      inset-x-0
      top-0
      bg-white
      flex
      items-center
      justify-center
      dark:bg-zz-bd
    "
  >
    <div class="w-28">
      <img
        v-if="this.isDark"
        alt="ZZalu Light logo"
        class="logo"
        src="../../../assets/zzalu_logo_dark.png"
      />
      <img
        v-else
        alt="ZZalu Light logo"
        class="logo"
        src="../../../assets/zzalu_logo_light.png"
      />
    </div>
  </div>
</template>

<script>
import { useDark } from "@vueuse/core";

const isDark = useDark();
export default {
  name: "OnlyBigLogoTopNav",
  data() {
    return {
      isDark,
    };
  },
};
</script>

<style></style>
