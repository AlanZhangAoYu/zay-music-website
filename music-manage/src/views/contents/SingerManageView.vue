<template>
	<div>歌手管理</div>
  <span><el-button type="primary" @click="">搜索歌手</el-button></span>
  <span><el-button type="primary" @click="">添加歌手</el-button></span>
  <div id="singer_table">
    <el-table :data="singerTableData.list" height="500" border stripe style="width: 100%">
      <el-table-column prop="singerId" label="歌手ID" width="100" />
      <el-table-column prop="singerName" label="歌手名称" width="180" />
      <el-table-column prop="singerBirth" label="歌手生日" width="100" />
      <el-table-column prop="singerIntroduction" label="歌手简介" width="260" />
      <el-table-column prop="singerLocation" label="歌手国籍" width="100" />
      <el-table-column label="歌手图片" width="230">
        <template #default={row}>
          <el-image style="width: 100px; height: 100px"
                    :src="row.singerImg">
            <template #error>
              <div class="image-slot">
                <el-icon><icon-picture /></el-icon>
              </div>
            </template>
          </el-image>
          <el-button type="primary" size="small" @click="editSinger(row.singerId);uploadSingerImgVisible = true">上传图片</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="270">
        <template #default={row}>
          <el-button type="primary" size="small" @click="editSinger(row.singerId);editSingerVisible = true">编辑信息</el-button>
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


  <el-dialog v-model="uploadSingerImgVisible" title="上传歌手图像" width="30%">
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
        <el-button @click="uploadSingerImgVisible = false">取消</el-button>
        <el-button type="primary" @click="uploadSingerImg">提交修改</el-button>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="editSingerVisible" title="编辑歌手信息(不改的项请不要填)">
    <el-form :model="editSingerList" :label-position="labelPosition">
      <el-form-item label="歌手ID">
        <el-input v-model="editSingerList.singerId" disabled :placeholder="editSingerList.singerId" />
      </el-form-item>
      <el-form-item label="歌手名" >
        <el-input v-model="editSingerList.singerName" />
      </el-form-item>
      <el-form-item label="歌手生日" >
        <el-col :span="11">
          <el-date-picker
              v-model="editSingerList.singerBirth"
              type="date"
              placeholder="选择日期"
              style="width: 100%"/>
        </el-col>
      </el-form-item>
      <el-form-item label="歌手简介" >
        <el-input v-model="editSingerList.singerIntroduction" type="textarea" />
      </el-form-item>
      <el-form-item label="歌手国籍" >
        <el-input v-model="editSingerList.singerLocation" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editSingerVisible = false">取消</el-button>
        <el-button type="primary" @click="commitEditList">提交修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
  import {reactive, ref} from 'vue';
  import axios from 'axios';
  import { Picture as IconPicture } from '@element-plus/icons-vue';
  import { Plus } from '@element-plus/icons-vue';
  import {ElMessage} from 'element-plus';
  import api from '../../router/index';

  const labelPosition = ref('top');
  let singerTableData=reactive({list:[]});
  let currentPage = ref(1);
  let totalLength = reactive({total:''});
  selectAllSingerCount();
  const editSingerVisible = ref(false);
  const uploadSingerImgVisible = ref(false);
  selectAllSinger(currentPage.value);
  let editSingerList=reactive({
    singerId: '',
    singerName: '',
    singerBirth: '',
    singerIntroduction: '',
    singerLocation: '',
  });
  function selectAllSinger(pageNum){
    axios.get(api.baseUrl.baseUrl+'/selectAllSinger',{params:{pageNum : pageNum, pageSize : 10}})
        .then((response) => {
          singerTableData.list = [];
          for(let i in response.data){
            singerTableData.list.push({
              singerId: response.data[i].singerId,
              singerName: response.data[i].singerName,
              singerBirth: dateFormat(response.data[i].singerBirth),
              singerIntroduction: response.data[i].singerIntroduction,
              singerLocation: response.data[i].singerLocation,
              singerImg: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].singerImgId
            });
          }
    });
  }
  function selectAllSingerCount(){
    axios.get(api.baseUrl.baseUrl+'/selectAllSingerCount').then((response) => {
      totalLength.total = response.data.AllSingerCount;
    });
  }
  function editSinger(index){
    editSingerList.singerId = index;
  }
  function uploadSingerImg(){
    let parma = new FormData();
    parma.append('singerId',editSingerList.singerId);
    if(imgForm.fileList.length !== 0){
      imgForm.fileList.forEach((value,index) => {
        parma.append('file',value.raw);
      });
    }else{
      parma.append('file',null);
    }
    axios.post(api.baseUrl.baseUrl+'/updateSingerImg',parma).then((response) => {
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
    parma.singerId = editSingerList.singerId;
    parma.singerName = editSingerList.singerName;
    if(editSingerList.singerBirth !== ''){
      let newBirthday=editSingerList.singerBirth.getFullYear() + '-'
          + (editSingerList.singerBirth.getMonth() + 1) + '-'
          + editSingerList.singerBirth.getDate() + ' '
          + editSingerList.singerBirth.getHours() + ':'
          + editSingerList.singerBirth.getMinutes() + ':'
          + editSingerList.singerBirth.getSeconds();
      parma.singerBirth = newBirthday;
    }
    parma.singerIntroduction = editSingerList.singerIntroduction;
    parma.singerLocation = editSingerList.singerLocation;
    console.log(parma);
    axios.post(api.baseUrl.baseUrl+'/updateSingerInfo',parma).then((response) => {
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
  function handleCurrentChange (val) {
    currentPage.value = val;
    selectAllSinger(currentPage.value);
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
  function beforeAvatarUpload(rawFile) {
    if (rawFile.type !== 'image/jpeg') {
      ElMessage.error('Avatar picture must be JPG format!')
      return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
      ElMessage.error('Avatar picture size can not exceed 2MB!')
      return false
    }
    return true
  }
  function dateFormat(time){
    let date = new Date(time);
    let year = date.getFullYear();
    let month = date.getMonth()+1;
    let day = date.getDate();
    return year + "-" + month + "-" + day;
  }
</script>

<style>
.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}
</style>
