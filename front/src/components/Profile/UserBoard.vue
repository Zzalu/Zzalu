<template>
  <div class="profile-title">{{}}님의 보드</div>
  <div class="profile-board">
    <div class="grid grid-cols-2 gap-4 place-items-center">
    <!-- <img src="https://media3.giphy.com/media/3ofSBzfwuwMhLaQNZm/giphy.gif?cid=99fefb40sq0mdhd9vs7c4bvk9ntjiuxto9wo4pzh0gsxe8lr&rid=giphy.gif&ct=g" alt=""/>
    <img src="https://media4.giphy.com/media/KztT2c4u8mYYUiMKdJ/giphy.gif?cid=99fefb40794ob3uvhnn0gdrcpw577jgnr6nkwc7msn64urk8&rid=giphy.gif&ct=g" alt=""/>
    <img src="https://media4.giphy.com/media/KztT2c4u8mYYUiMKdJ/giphy.gif?cid=99fefb40794ob3uvhnn0gdrcpw577jgnr6nkwc7msn64urk8&rid=giphy.gif&ct=g" alt=""/> -->
    <BoardTitleList />
  </div>
</div>
</template>

<script>
import BoardTitleList from "../SaveJjal/BoardTitleList"
export default {
  name: "UserBoard",
  components: {
    BoardTitleList
  }

}
</script>

<style lang="postcss" scoped>

.profile-board {
  @apply mt-2 mb-6
}
</style>