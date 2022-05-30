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
    <div id="main">榜单</div>
  </div>
</template>

<script>
import {ref} from 'vue';
export default{
  name: 'ListView',
  setup(){
    return{}
  }
}
</script>

<style scoped>
#main{
  width: 80%;
  height: 500px;
  border: 2px solid black;
  position: absolute;
  left: 10%;
  top: 60px;
}
#particles{
  background-color: #f9f9f9;
}
</style>