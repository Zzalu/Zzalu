<template>
  <div class="navbar-main">
    <div class="navbar-input-box">
      <font-awesome-icon
        icon="fa-solid fa-magnifying-glass"
        class="navbar-icon"
      />
      <input type="text" placeholder="Search..." class="navbar-input" />
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
};
</script>

<style scoped lang="postcss">
.navbar-main {
  
  @apply h-nav-height fixed inset-x-0 top-24 flex items-center justify-center;
}
.navbar-input-box {
  @apply bg-zz-light-input w-10/12 h-9 flex items-center px-5 py-1 rounded-lg;
}
.navbar-icon {
  @apply text-zz-darkgray mr-2;
}
.navbar-input {
  @apply bg-transparent text-zz-darkgray w-full;
}
</style>