<template>
  <div>
    <div>歌曲管理</div>
    <span><el-button type="primary" @click="searchSongVisible = true">搜索歌曲</el-button></span>
    <span><el-button type="primary" @click="addSongVisible = true">添加歌曲</el-button></span>
    <div id="song_table">
      <el-table :data="songTableData.list" height="500" border stripe style="width: 100%">
        <el-table-column prop="songId" label="歌曲ID" width="100" />
        <el-table-column prop="songName" label="歌曲名" width="180" />
        <el-table-column prop="singerName" label="歌手" width="140" />
        <el-table-column prop="albumName" label="所属专辑" width="140" />
        <el-table-column prop="songType" label="歌曲种类" width="100" />
        <el-table-column prop="songLength" label="歌曲长度" width="100" />
        <el-table-column prop="createTime" label="上传时间" width="180" />
        <el-table-column fixed="right" label="操作" width="270">
          <template #default="scope">
            <el-button type="primary" size="small" @click="showDetails(scope.$index);detailVisible = true">歌曲详情</el-button>
            <el-button type="warning" size="small" @click="">下载歌曲</el-button>
            <el-button type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination">
      <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page="currentPage.value"
          :page-size="10"
          layout="total,prev, pager, next, jumper"
          :total="totalLength.total"/>
    </div>
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
          <el-option v-for="item in songTypeList" :key="item.songTypeId" :label="item.songTypeName" :value="item.songTypeId"/>
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
          <el-option v-for="item in songTypeList" :key="item.songTypeId" :label="item.songTypeName" :value="item.songTypeId"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-upload class="upload-demo" drag multiple :limit="1" :auto-upload="false"
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


  <el-dialog v-model="detailVisible" title="歌曲详情" width="30%" draggable>
    <el-form :label-position="labelPosition"
        label-width="100px"
        style="max-width: 460px">
      <el-form-item label="歌曲ID">
        {{detailTable.songId}}
      </el-form-item>
      <el-form-item label="歌曲名">
        {{detailTable.songName}}
      </el-form-item>
      <el-form-item label="所属专辑">
        {{detailTable.albumName}}
      </el-form-item>
      <el-form-item label="歌手">
        {{detailTable.singerName}}
      </el-form-item>
      <el-form-item label="歌曲种类">
        {{detailTable.songType}}
      </el-form-item>
      <el-form-item label="歌曲长度">
        {{detailTable.songLength}}
      </el-form-item>
      <el-form-item label="上传时间">
        {{detailTable.createTime}}
      </el-form-item>
      <el-form-item label="文件ID">
        {{detailTable.fileId}}<el-button type="text" @click="previewSongFile()">预览歌曲</el-button>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="detailVisible = false">取消</el-button>
        <el-button type="primary" @click="detailVisible = false">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
  import {reactive, ref} from 'vue';
  import axios from 'axios'
  import {ElMessage} from 'element-plus';
  import { UploadFilled } from '@element-plus/icons-vue';

  let searchSongVisible = ref(false);
  let addSongVisible = ref(false);
  let detailVisible = ref(false);
  let songTypeList = [];
  selectSongType();
  let totalLength = reactive({
    total:''
  });
  selectAllSongCount();
  console.log(totalLength.value);
  let currentPage = ref(1);
  let songTableData = reactive({list:[]});
  selectAllSong(currentPage.value);
  const labelPosition = ref('right');
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
    parma.append('songTypeId',addForm.songType);
    addForm.fileList.forEach((value,index) => {
      parma.append('file',value.raw);
    });
    axios.post('http://127.0.0.2:8081/uploadSongFile',parma).then(function (response){
      if(response.data.msg === 'Upload Successfully'){
        ElMessage({
          showClose: true,
          message: '上传成功',
          type: 'success',
        })
      }else {
        ElMessage.error('上传失败');
      }
    }).catch(function (error){
      ElMessage.error('上传失败');
      console.log(error);
    });
  }
  function selectSongType(){
    axios.get('http://127.0.0.2:8081/selectAllSongType').then((response) =>{
      for(let i in response.data){
        songTypeList.push({
          songTypeId: response.data[i].songTypeId,
          songTypeName: response.data[i].songTypeName
        });
      }
    });
  }
  function selectAllSongCount(){
    axios.get('http://127.0.0.2:8081/selectAllSongCount').then((response) =>{
      totalLength.total = response.data.AllSongCount;
      console.log(totalLength.total);
    });
  }
  function selectAllSong(pageNum){
    axios.get('http://127.0.0.2:8081/selectAllSong',{params:{pageNum : pageNum, pageSize : 10}})
        .then((response) =>{
          songTableData.list = [];
          for(let i in response.data){
            songTableData.list.push({
              songId: response.data[i].songId,
              songName: response.data[i].songName,
              singerName: response.data[i].singer.singerName,
              albumName: response.data[i].album.albumName,
              songType: response.data[i].songType.songTypeName,
              songLength: response.data[i].songLength,
              createTime: dateFormat(response.data[i].createTime),
              fileId: response.data[i].fileId
            });
          }
        });
  }
  let detailTable = reactive({
    songId: '',
    songName: '',
    singerName: '',
    albumName: '',
    songType: '',
    songLength: '',
    createTime: '',
    fileId: ''
  });
  function showDetails (index){
    detailTable.songId = songTableData.list[index].songId;
    detailTable.songName = songTableData.list[index].songName;
    detailTable.singerName = songTableData.list[index].singerName;
    detailTable.albumName = songTableData.list[index].albumName;
    detailTable.songType = songTableData.list[index].songType;
    detailTable.songLength = songTableData.list[index].songLength;
    detailTable.createTime = songTableData.list[index].createTime;
    detailTable.fileId = songTableData.list[index].fileId;
    detailVisible = true;
  }
  function previewSongFile(){
    window.open("http://127.0.0.2:8081/previewFile/"+detailTable.fileId);
  }
  function handleCurrentChange (val) {
    currentPage.value = val;
    selectAllSong(currentPage.value);
  }
  function dateFormat(time){
    let date = new Date(time);
    let year = date.getFullYear();
    let month = date.getMonth()+1;
    let day = date.getDate();
    let hour = date.getHours();
    let min = date.getMinutes();
    let second = date.getSeconds();
    return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
  }
</script>

<style>
</style>
