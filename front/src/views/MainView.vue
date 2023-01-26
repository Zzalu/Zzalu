<template>
  <div>
    <div>
      <p>역대 제목학원 명예의 전당</p>
      <hr>
      <div>
        카드
      </div>
    </div>
    <div>
      <p>user.id님을 위한 추천 짤</p>
      <hr>
      <div>
        이미지
      </div>
    </div>
    <div>
      <p>오늘의 인기 짤</p>
      <hr>
      <div>
        이미지
      </div>
    </div>
    <div>
      <p>요새 핫한 고독방</p>
      <hr>
      <div>
        카드
      </div>
    </div>
    <SearchView/>
  </div>
</template>

<script>
import SearchView from './SearchView'

export default {
  name: "MainView",
  components : {
    SearchView
  }
}
</script>

<style>

</style>
