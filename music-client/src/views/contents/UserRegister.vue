<template>
  <div class="main">
    <div class="font_title">
      <div style="font-style: oblique;font-size: 30px;color: #f9f9f9">Zay-music</div>
      <div style="font-size: 80px;color: #f9f9f9">-&nbsp;用心聆听生活</div>
      <div style="font-size: 80px;color: #f9f9f9">-&nbsp;用爱感受美好</div>
    </div>
    <div class="register_window">
    </div>
  </div>
</template>

<script>
export default {
  name: "userRegister"
}
</script>

<style scoped>
.main{
  background-image: url("../../assets/image/reg_bg_min.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 100%;
  height: 100vh;
}
.register_window{
  background-color: rgba(255,255,255,0.5);
  height: 600px;
  width: 500px;
  border-radius: 10px;
  position: absolute;
  top: 100px;
  left: 60%;
}
</style>