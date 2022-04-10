<template>
  <div>
    <div>歌曲管理</div>
    <span><el-button type="primary" @click="searchSongVisible = true">搜索歌曲</el-button></span>
    <span><el-button type="primary" @click="addSongVisible = true">添加歌曲</el-button></span>
    <div id="song_table">
      <el-table :data="tableData" height="350" border stripe style="width: 100%">
        <el-table-column prop="songId" label="歌曲ID" width="180" />
        <el-table-column prop="songName" label="歌曲名" width="180" />
        <el-table-column prop="singerName" label="歌手" width="80" />
        <el-table-column prop="albumName" label="所属专辑" width="180" />
        <el-table-column prop="songType" label="歌曲种类" width="180" />
        <el-table-column prop="createTime" label="歌曲上传时间" width="180" />
        <el-table-column fixed="right" label="操作" width="270">
          <template #default>
            <el-button type="primary" size="small" @click="">歌曲详情</el-button>
            <el-button type="primary" size="small" @click="">下载歌曲</el-button>
            <el-button type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination background layout="prev, pager, next" :total="10" />
  </div>


  <el-dialog v-model="searchSongVisible" title="请选择搜索条件" width="50%" style="text-align: center">
    <el-form :model="searchForm">
      <el-form-item label="歌曲ID">
        <el-input v-model="searchForm.songId" autocomplete="off" />
      </el-form-item>
      <el-form-item label="歌曲名">
        <el-input v-model="searchForm.songName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="歌手">
        <el-input v-model="searchForm.singerName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="所属专辑">
        <el-input v-model="searchForm.albumName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="歌曲种类">
        <el-select v-model="searchForm.songType" class="m-2" placeholder="请选择歌曲种类" size="large">
          <el-option v-for="item in songType" :key="item.songTypeId" :label="item.songTypeName" :value="item.songTypeId"/>
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="searchSongVisible = false">取消</el-button>
        <el-button type="primary" @click="searchSongVisible = false">查询</el-button>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="addSongVisible" title="请上传文件" width="50%" style="text-align: center">
    <el-form>
      <el-form-item label="歌曲种类">
        <el-select v-model="addForm.songType" class="m-2" placeholder="请选择歌曲种类" size="large">
          <el-option v-for="item in songType" :key="item.songTypeId" :label="item.songTypeName" :value="item.songTypeId"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-upload class="upload-demo" drag multiple :limit="3" :auto-upload="false"
        :http-request="addFileList" :file-list="addForm.fileList" :headers="{'Content-Type': 'multipart/form-data'}">
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            拖拽文件到此区域或<em>点击上传</em>
          </div>
          <template #tip>
            <div class="el-upload__tip">mp3文件大小应小于16MB</div>
          </template>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addSongVisible = false">取消</el-button>
        <el-button type="primary" @click="uploadMp3File">上传</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
  import {reactive, ref} from "vue";
  import { UploadFilled } from '@element-plus/icons-vue'
  import axios from 'axios'
  import {ElMessage} from "element-plus";

  const searchSongVisible = ref(false);
  const addSongVisible = ref(false);
  const searchForm = reactive({
    songId: '',
    songName: '',
    singerName: '',
    albumName: '',
    songType: ''
  });
  const addForm = reactive({
    songType: '',
    fileList: []
  });
  function addFileList(option){
    //将要上传的文件暂时放在addForm.fileList中
    //此函数替代默认的<el-upload>上传行为
    addForm.fileList.push(option);
  }
  function uploadMp3File(){
    let parma = new FormData();
    parma.append('songType',addForm.songType);
    addForm.fileList.forEach((value,index) => {
      parma.append('file',value.raw);
    });
    axios.post('http://127.0.0.2:8081/uploadSongFile',parma).then(function (response){
      ElMessage({
        showClose: true,
        message: '上传成功',
        type: 'success',
      })
    }).catch(function (error){
      ElMessage.error('上传失败');
      console.log(error);
    });
  }
  const songType = [
    {
      songTypeId: 0,
      songTypeName: '未知分类'
    },{
      songTypeId: 1,
      songTypeName: '华语'
    },{
      songTypeId: 2,
      songTypeName: '粤语'
    },{
      songTypeId: 3,
      songTypeName: '欧美'
    },{
      songTypeId: 4,
      songTypeName: '日韩'
    },{
      songTypeId: 5,
      songTypeName: '轻音乐'
    },{
      songTypeId: 6,
      songTypeName: '古典'
    },{
      songTypeId: 7,
      songTypeName: '其他'
    }
  ];
</script>

<style>
</style>
