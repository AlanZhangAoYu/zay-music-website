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
        </el-menu>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
      <el-footer>
        <div class="footer">
          <span style="position: relative;top: 35%;left: 35%">Designed by ZAY(z7954221907482@163.com) @All Rights Reserved</span>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script setup>
  import {ref} from 'vue';
  const activeIndex = ref('1');
  const handleSelect = (key, keyPath) => {
    //console.log(key, keyPath);
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