<template>
  <div>
    진짜 갈거...? 가지마가지마가지마아ㅏㅏ
    <input type="text" v-model="state.password"> 마지막으로 비번 함 입력해
    <button @click="deleteAccount">그래도 갈거임 ㅃㅇ</button>
  </div>
</template>

<script>
import { useStore } from 'vuex';
import { reactive } from 'vue'
export default {
  name: "AccountDeleteView",
  setup () {
    const store = useStore();
    const state = reactive({
        password: ""
    })
    const deleteAccount = async function () {
      const result = await store.dispatch('userStore/userDeleteAction', state.password)
      if (result == 400) {
        alert("다시해")
      }
    }


    return {
      state,
      deleteAccount
    }
  }

}
</script>

<style>

</style>