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
  </div>
  <div id="main">专辑</div>
</template>

<script>
export default {
  name: "AlbumDetails",
  setup(){
    return{}
  }
}
</script>

<style scoped>
#particles{
  background-color: #f9f9f9;
}
#main{
  position: absolute;
  top: 60px;
}
</style>