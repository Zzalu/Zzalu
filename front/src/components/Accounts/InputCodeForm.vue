<template>
  <!-- <h1 class="account-title mb-10">Sign Up</h1> -->
  <!-- <h1 class="account-title mb-10" v-if="$route.name == 'signup'">Sign Up</h1> -->
  <div class="text-center-container">
    <div class="almost-done">
      <div class="error-sub-title" v-if="$route.name == 'signup'">거의 다 끝났습니다!</div>
      <div class="error-sub-title">이메일로 전송된 인증코드를 입력해주세요.</div>
      <div class="time-remain" :timerStr="timerStr">{{ timerStr }}</div>
    </div>
  </div>
  <div class="flex justify-center">
    <input type="text" class="input-code">
    <input type="text" class="input-code">
    <input type="text" class="input-code">
    <input type="text" class="input-code">
  </div>
  <div class="text-center-container">
    <div class="didnt-get-mail">
      <div class="error-content">메일을 받지 못하셨나요?</div>
      <button class="send-again-button">인증 메일 재전송</button>
      
    </div>

      <button class="submit-button" @click = "signupFinal">인증하기</button>

  </div>
</template>

<script>
export default {
  name: 'InputCodeForm',
  data: function() {
    return {
      timer: null,
      timeCounter: 180,
      timerStr: "03:00"
    }
  },
  mounted: function() {
    if(this.Timer != null){
      this.timerStop(this.Timer);
        this.Timer = null;
    }
    this.Timer = this.timerStart();
  },
  methods : {
    timerStart: function() {
      // 1초에 한번씩 start 호출
      this.TimeCounter = 180;
      var interval = setInterval(() => {
        this.TimeCounter--; //1초씩 감소
        this.TimerStr = this.prettyTime();
        if (this.TimeCounter <= 0) this.timerStop(interval);
      }, 1000);
      return interval;
    },
    timerStop: function(Timer) {
      clearInterval(Timer);
      this.TimeCounter = 0;
    },
    prettyTime: function() {
      // 시간 형식으로 변환 리턴
      let time = this.TimeCounter / 60;
      let minutes = parseInt(time);
      let secondes = Math.round((time - minutes) * 60);
      return (
        minutes.toString().padStart(2, "0") +
        ":" +
        secondes.toString().padStart(2, "0")
      );
    },
    // 최종 회원가입 요청
    signupFinal() {
      
    }
  }
}
</script>

<style>

</style>