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
      {{singerId}}
    </div>
  </div>
</template>

<script setup>
  //注意Router与Route的区别!!!!
  //Router是发送 Route是接收
  import { useRoute } from 'vue-router';
  import {ref} from 'vue';
  const route = useRoute();
  const singerId=ref(route.params.singerId);
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
</style>