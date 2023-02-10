<template>
  <div>
    <h1>어드민 페이지</h1>
    <div class="grid-cols-2">
      <div v-for="(AdminList,i) in 9" :key="i">
        <AdminListItem/>
      </div>

    </div>
  </div>
</template>

<script>
import AdminListItem from "@/components/Accounts/Admin/AdminListItem.vue"
export default {
  name: "AdminInfoView",
  components: {
    AdminListItem,
  },
  // setup () {
  //   const 내가가져온데이터 = computed(
  //     () => store에서가져온데이터
  //   );
  // }
}
</script>

<style>

</style>