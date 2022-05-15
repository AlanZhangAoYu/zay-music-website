<template>
	<div>专辑管理</div>
  <span><el-button type="primary" @click="">搜索专辑</el-button></span>
  <span><el-button type="primary" @click="">添加专辑</el-button></span>
  <div id="album_table">
    <el-table :data="albumTableData.list" height="500" border stripe style="width: 100%">
      <el-table-column prop="albumId" label="专辑ID" width="100" />
      <el-table-column prop="albumName" label="专辑名称" width="180" />
      <el-table-column prop="singerName" label="所属歌手" width="100" />
      <el-table-column prop="year" label="发行年份" width="100" />
      <el-table-column label="专辑图片" width="230">
        <template #default={row}>
          <el-image style="width: 100px; height: 100px"
                    :src="row.albumImgId">
            <template #error>
              <div class="image-slot">
                <el-icon><icon-picture /></el-icon>
              </div>
            </template>
          </el-image>
          <el-button type="primary" size="small" @click="editAlbum(row.albumId);uploadAlbumImgVisible = true">上传图片</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="270">
        <template #default={row}>
          <el-button type="primary" size="small" @click="editAlbum(row.albumId);editAlbumVisible = true">编辑信息</el-button>
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


  <el-dialog v-model="uploadAlbumImgVisible" title="上传专辑图像" width="30%">
    <el-upload
        class="upload-demo"
        :auto-upload="false"
        :file-list="imgForm.fileList"
        :http-request="addImgList"
        :headers="{'Content-Type': 'multipart/form-data'}"
        limit="1">
      <el-button type="primary">点击上传图片</el-button>
      <template #tip>
        <div class="el-upload__tip">
          jpg/png 文件小于 500KB.
        </div>
      </template>
    </el-upload>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="uploadAlbumImgVisible = false">取消</el-button>
        <el-button type="primary" @click="uploadAlbumImg">提交修改</el-button>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="editAlbumVisible" title="编辑专辑信息(不改的项请不要填)" width="30%">
    <el-form :model="editAlbumList" :label-position="labelPosition">
      <el-form-item label="专辑ID">
        <el-input v-model="editAlbumList.albumId" disabled :placeholder="editAlbumList.albumId"/>
      </el-form-item>
      <el-form-item label="专辑名">
        <el-input v-model="editAlbumList.albumName"/>
      </el-form-item>
      <el-form-item label="发行年份">
        <el-input type="number" v-model="editAlbumList.year"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editAlbumVisible = false">取消</el-button>
        <el-button type="primary" @click="commitEditList">提交修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
  import {reactive, ref} from "vue";
  import axios from "axios";
  import { Picture as IconPicture } from '@element-plus/icons-vue';
  import { Plus } from '@element-plus/icons-vue';
  import {ElMessage} from "element-plus";
  import api from "@/router";

  let albumTableData = reactive({list: []});
  let currentPage = ref(1);
  let totalLength = reactive({total:''});
  const editAlbumVisible = ref(false);
  const uploadAlbumImgVisible = ref(false);
  const labelPosition = ref('top');
  let editAlbumList = reactive({
    albumId: '',
    albumName: '',
    year: ''
  });
  selectAllAlbumCount();
  selectAllAlbum(currentPage.value);
  function selectAllAlbum(pageNum){
    axios.get(api.baseUrl.baseUrl+'/selectAllAlbum',{params:{pageNum : pageNum, pageSize : 10}})
        .then((response) => {
          albumTableData.list = [];
          for(let i in response.data){
            albumTableData.list.push({
              albumId: response.data[i].albumId,
              albumName: response.data[i].albumName,
              singerName: response.data[i].singer.singerName,
              year: response.data[i].year,
              albumImgId: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].albumImgId
            });
          }
        });
  }
  function selectAllAlbumCount(){
    axios.get(api.baseUrl.baseUrl+'/selectAllAlbumCount').then((response) => {
      totalLength.total = response.data.AllAlbumCount;
    });
  }
  function uploadAlbumImg(){
    let parma = new FormData();
    parma.append('albumId',editAlbumList.albumId);
    if(imgForm.fileList.length !== 0){
      imgForm.fileList.forEach((value,index) => {
        parma.append('file',value.raw);
      });
    }else{
      parma.append('file',null);
    }
    axios.post(api.baseUrl.baseUrl+'/updateAlbumImg',parma).then((response) => {
      if(response.data.msg === 'failed'){
        ElMessage.error('更改失败');
      }else {
        ElMessage({
          message: '更改成功',
          type: 'success',
        });
      }
    });
  }
  function commitEditList(){
    let parma = {};
    parma.albumId = editAlbumList.albumId;
    parma.albumName = editAlbumList.albumName;
    parma.year = editAlbumList.year;
    axios.post(api.baseUrl.baseUrl+'/updateAlbumInfo',parma).then((response) => {
      if(response.data.msg === 'failed'){
        ElMessage.error('更改失败');
      }else {
        ElMessage({
          message: '更改成功',
          type: 'success',
        });
      }
    });
  }
  function editAlbum(index){
    editAlbumList.albumId = index;
  }
  const imgForm = reactive({
    fileList: []
  });
  function addImgList(option){
    //将要上传的文件暂时放在imgForm.fileList中
    //此函数替代默认的<el-upload>上传行为
    imgForm.fileList.push(option);
    console.log(imgForm.fileList);
  }
  function handleCurrentChange (val) {
    currentPage.value = val;
    selectAllAlbum(currentPage.value);
  }
  function editSinger(index){
    editAlbumList.albumId = index;
  }
</script>

<style>
</style>
