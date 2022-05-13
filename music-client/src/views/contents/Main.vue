<template>
  <div id="particles">
    <vue-particles
        id="particles-js"
        color="#C0C0C0"
        :particleOpacity="0.7"
        :particlesNumber="130"
        shapeType="circle"
        :particleSize="4"
        linesColor="#C0C0C0"
        :linesWidth="1"
        :lineLinked="true"
        :lineOpacity="0.4"
        :linesDistance="150"
        :moveSpeed="4"
        :hoverEffect="true"
        hoverMode="grab"
        :clickEffect="true"
        clickMode="push">
    </vue-particles>
    <div id="main">
      <div id="carousel">
        <el-carousel height="300px">
          <el-carousel-item v-for="item in 4" :key="item">
            <h3 text="2xl">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="position: relative;top: 3%;left: 14%">
        <div id="recommend_singer">
          推荐歌手
        </div>
        <div id="recommend_album">
          推荐专辑
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
#particles-js{
  width: 100%;
}
#main{
  position: absolute;
  top: 60px;
  width: 100%;
  height: 100%;
}
#particles{
  background-color: #f9f9f9;
}
#carousel{
  width: 70%;
  margin-left: 14%;
  background-color: rgba(211,220,230,0.5);
  padding: 20px;
  border-radius: 20px;
}
#recommend_singer{
  height: 300px;
  width: 500px;
  background-color: rgba(211,220,230,0.5);
  padding: 20px;
  border-radius: 20px;
  float: left;
  margin-right: 20px;
  margin-top: 5px;
}
#recommend_album{
  height: 300px;
  width: 500px;
  background-color: rgba(211,220,230,0.5);
  padding: 20px;
  border-radius: 20px;
  float: left;
  margin-top: 5px;
}
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>