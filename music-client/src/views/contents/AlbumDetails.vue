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
  <div id="main">
    <div style="background-color: #94defc;height: 130px;width: 100%;">
      <el-button type="text" :icon="Back" style="margin: 10px;" @click="backToAlbumView">返回</el-button>
    </div>
    <el-image style="height: 350px;width: 350px;border-radius: 30px;border: 7px solid #ffffff;position: relative;
                      top: -70px;left: 100px;" :src="albumInfo.info.albumImgUrl">
      <template #error>
        <div class="image-slot">
          <img src="../../assets/image/default_image.jpg"/>
        </div>
      </template>
    </el-image>
    <div style="width: 300px;font-weight: bolder;position: relative;left: 50px;top: -50px;">
      <ul>
        <li style="margin: 10px;">歌手:{{albumInfo.info.singerName}}</li>
        <li style="margin: 10px;">发布年份:{{albumInfo.info.year}}</li>
      </ul>
    </div>
    <div style="position: absolute;top: 140px;left: 500px;">
      <div style="width: 200px;font-weight: bolder;font-size: 25px;">{{albumInfo.info.albumName}}</div>
    </div>
  </div>
</template>

<script>
import { useRoute,useRouter } from 'vue-router';
import {ref,reactive,inject,onMounted} from 'vue';
import { Back } from '@element-plus/icons-vue';
import api from "@/router";
import axios from 'axios';
export default {
  name: "AlbumDetail",
  setup(){
    const route = useRoute();
    const router = useRouter();
    const albumId=ref(route.params.albumId);
    //获取全局的播放列表
    let playList = inject('global').playList;
    let albumInfo=reactive({info: {
        albumId: '',
        albumName: '',
        singerName: '',
        year: '',
        albumImgUrl: ''
      }});
    function selectAlbumByAlbumId(albumId){
      axios.get(api.baseUrl.baseUrl+'/selectAlbumByPara',{params:{albumId: albumId,albumName: '',singerId: '',year: ''}})
          .then((response)=>{
            albumInfo.info.albumId = response.data[0].albumId;
            albumInfo.info.albumName = response.data[0].albumName;
            albumInfo.info.singerName = response.data[0].singer.singerName;
            albumInfo.info.year = response.data[0].year;
            albumInfo.info.albumImgUrl = api.baseUrl.baseUrl+'/previewFile/'+response.data[0].albumImgId;
          });
    }
    const backToAlbumView=()=>{
      router.push({
        path: '/AlbumView'
      });
    }
    onMounted(()=>{
      selectAlbumByAlbumId(albumId.value);
    });
    return{albumId,Back,playList,albumInfo,backToAlbumView}
  }
}
</script>

<style scoped>
#particles{
  background-color: #f9f9f9;
}
#main{
  width: 100%;
  margin: auto 0;
  position: absolute;
  top: 60px;
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