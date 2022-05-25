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
      <div>共{{totalLength.total}}张专辑</div>
      <div id="album_list">
        <div v-for="album in albumList.albumList" class="album_list_item">
          <div style="position: absolute">
            <el-image style="width: 100px;height: 100px;margin: 10px;" :src="album.albumImgUrl" fit="fill">
              <template #error>
                <div class="image-slot">
                  <img src="../../assets/image/default_image.jpg"/>
                </div>
              </template>
            </el-image>
            <div style="position: relative;left: 115px;top: -100px;">{{album.albumName}}</div>
            <div style="font-size: 10px;color: #99a9bf;position: relative;left: 115px;top: -70px;">发行年份:{{album.year}}</div>
          </div>
        </div>
      </div>
      <div class="pagination" style="position: absolute;left: 40%;top: 650px;">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="prev, pager, next, jumper"
            :total="totalLength.total"/>
      </div>
    </div>
  </div>
</template>

<script>
import {ref ,reactive,onMounted} from 'vue';
import {useRouter} from 'vue-router';
import axios from 'axios';
import api from '../../router/index';
import { Picture as IconPicture } from '@element-plus/icons-vue';
export default {
  name: "Album",
  setup(){
    const router = useRouter();
    let currentPage = ref(1);
    let totalLength = reactive({total:''});
    //selectAllSingerCount();
    let albumList=reactive({albumList:[]});
    //selectAllAlbum(currentPage.value);
    const selectAllSingerCount=()=>{
      axios.get(api.baseUrl.baseUrl+'/selectAllAlbumCount').then((response) => {
        totalLength.total = response.data.AllAlbumCount;
      });
    }
    const selectAllAlbum = (pageNum)=>{
      axios.get(api.baseUrl.baseUrl+'/selectAllAlbum',{params:{pageNum: pageNum,pageSize: 10}})
            .then((response) => {
              albumList.albumList = [];
              for(let i in response.data){
                albumList.albumList.push({
                  albumId: response.data[i].albumId,
                  albumName: response.data[i].albumName,
                  year: response.data[i].year,
                  singerName: response.data[i].singer.singerName,
                  albumImgUrl: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].albumImgId
                });
              }
            });
    };
    const handleCurrentChange=(val)=>{
      currentPage.value = val;
      selectAllAlbum(currentPage.value);
    };
    onMounted(()=>{
      selectAllSingerCount();
      selectAllAlbum(currentPage.value);
    });
    return{albumList,currentPage,totalLength,handleCurrentChange,IconPicture}
  }
}
</script>

<style scoped>
#main{
  width: 80%;
  height: 300px;
  margin: auto 0;
  position: absolute;
  left: 10%;
  top: 60px;
}
#particles{
  background-color: #f9f9f9;
}
.album_list_item{
  width: 250px;
  height: 150px;
  float: left;
  margin: 25px;
  border-radius: 10px;
  background-color: rgba(211,220,230,0.5);
  border: 2px solid #f9f9f9;
}
.album_list_item:hover{
  border: 2px solid #99a9bf;
}
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