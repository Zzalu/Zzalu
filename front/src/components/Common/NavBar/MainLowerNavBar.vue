<template>
  <div>
    <ul class="nav_list">
      <li class="nav_item">
        <span>
          <font-awesome-icon icon="fa-solid fa-user-secret" />
        </span>
      </li>
      <li class="nav_item">
        <span><font-awesome-icon icon="fa-regular fa-house" /></span>
      </li>
      <li class="nav_item">
        <span class="search">홈</span>
      </li>
      <li class="nav_item">
        <span>홈</span>
      </li>
      <li class="nav_item">
        <span>홈</span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'MainLowerNavBar',
};
</script>

<style scoped>
.nav_list {
  @apply flex flex-wrap;
}
.nav_item {
  @apply flex-1 cursor-pointer text-center;
}
.search {
  @apply bg-zz-p rounded-xl text-white p-1;
}
</style>
