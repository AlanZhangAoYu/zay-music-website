<template>
  <div class="common-layout">

    <el-container>
      <el-header>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="handleSelect">
          <img src="../assets/logo.png" width="30" height="30" style="margin-top: 10px;margin-left: 10px;"/>
          <span style="margin: 10px; color: azure; font-size: 30px;font-family: serif; font-style: oblique;">Zay-music</span>
          <el-menu-item index="1"><router-link to="/MainView" class="a">首页</router-link></el-menu-item>
          <el-menu-item index="2"><router-link to="/ListView" class="a">榜单</router-link></el-menu-item>
          <el-menu-item index="3"><router-link to="/SingerView" class="a">歌手</router-link></el-menu-item>
          <el-menu-item index="4"><router-link to="/AlbumView" class="a">专辑</router-link></el-menu-item>
          <el-button type="primary" :icon="Avatar" circle style="position: absolute;margin-top: 15px;right: 20px;"/>
        </el-menu>
      </el-header>
      <el-main style="width: 100%">
        <router-view/>
      </el-main>
      <el-footer>
        <div class="footer">
          <span style="position: relative;top: 35%;left: 35%">Designed by ZAY(z7954221907482@163.com) @All Rights Reserved</span>
        </div>
      </el-footer>
    </el-container>
  </div>
  <el-affix position="bottom" :offset="0">
    <el-button type="primary" @click="PlayBar">{{ playBarTitle }}</el-button>
    <div ref="play_bar" style="display: block;width: 100%;background-color: #42b983">
      播放栏
    </div>
  </el-affix>
</template>

<script setup>
  import {ref} from 'vue';
  import {Avatar} from '@element-plus/icons-vue';
  const activeIndex = ref('1');
  let playBarTitle = ref('隐藏播放栏');
  const play_bar = ref(null);
  const handleSelect = (key, keyPath) => {
    //console.log(key, keyPath);
  }
  function PlayBar(){
    if(playBarTitle.value === '显示播放栏'){
      play_bar.value.style.display = 'block';
      playBarTitle.value = '隐藏播放栏';
    }else {
      play_bar.value.style.display = 'none';
      playBarTitle.value = '显示播放栏';
    }
  }
</script>

<style scoped>
  .a{
    text-decoration: none;
  }
  .footer{
    color: #f9f9f9;
    background-color: #545c64;
    height: 70px;
    width: 100%;
  }
  .el-header{
    padding: 0;
  }
  .el-main{
    padding: 0;
  }
  .el-footer{
    padding: 0;
  }
</style>