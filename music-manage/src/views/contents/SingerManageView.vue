<template>
	<div>歌手管理</div>
  <span><el-button type="primary" @click="">搜索歌手</el-button></span>
  <span><el-button type="primary" @click="">添加歌手</el-button></span>
  <div id="singer_table">
    <el-table :data="singerTableData.list" height="500" border stripe style="width: 100%">
      <el-table-column prop="singerId" label="歌手ID" width="100" />
      <el-table-column prop="singerName" label="歌手名称" width="180" />
      <el-table-column prop="singerBirth" label="歌手生日" width="140" />
      <el-table-column prop="singerIntroduction" label="歌手简介" width="140" />
      <el-table-column prop="singerLocation" label="歌手国籍" width="100" />
      <el-table-column prop="singerImgId" label="歌手图片" width="180" />
      <el-table-column fixed="right" label="操作" width="270">
        <template #default="scope">
          <el-button type="primary" size="small" @click="">编辑信息</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
</script>

<style>
</style>
