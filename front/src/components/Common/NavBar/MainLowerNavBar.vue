<template>
  <div>
    <ul class="nav_list">
      <li class="nav_item">
        <span>
          <font-awesome-icon icon="fa-solid fa-house" />
        </span>
      </li>
      <li class="nav_item">
        <span><font-awesome-icon icon="fa-regular fa-comment-dots" /></span>
      </li>
      <li class="nav_item">
        <span class="search"><font-awesome-icon icon="fa-solid fa-magnifying-glass" /></span>
      </li>
      <li class="nav_item">
        <span><font-awesome-icon icon="fa-regular fa-lightbulb" /></span>
      </li>
      <li class="nav_item">
        <span><font-awesome-icon icon="fa-regular fa-user" /></span>
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
span {
  display: inline-block;
}
.nav_list {
  @apply flex flex-wrap h-nav-height content-center place-items-center;
}
.nav_item {
  @apply flex-1 cursor-pointer text-center;
}
.search {
  @apply h-12 w-12 bg-zz-p rounded-xl text-white p-3;
}
</style>
