<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<style>
body{
  margin: 0;
  padding: 0;
  overflow-x: hidden;
  overflow-y: scroll;
  font-family: "Microsoft YaHei UI",sans-serif;
}
a{
  text-decoration: none;
  color: black;
}
li, ol, ul {
  list-style: none;
}
body::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 10px;  /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}
body::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  background-color: #99a9bf;
  background-image: -webkit-linear-gradient(
      45deg,
      rgba(255, 255, 255, 0.2) 25%,
      transparent 25%,
      transparent 50%,
      rgba(255, 255, 255, 0.2) 50%,
      rgba(255, 255, 255, 0.2) 75%,
      transparent 75%,
      transparent
  );
}
body::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #ededed;
  border-radius: 10px;
}
@font-face {
  font-family: MiSans-Light;
  src: url('./assets/fonts/MiSans-Light.ttf');
}
@font-face {
  font-family: MiSans-Normal;
  src: url('./assets/fonts/MiSans-Normal.ttf');
}
@font-face {
  font-family: MiSans-Regular;
  src: url('./assets/fonts/MiSans-Regular.ttf');
}
</style>
