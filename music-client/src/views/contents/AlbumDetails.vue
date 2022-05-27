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
    <div style="position: absolute;top: 250px;left: 500px;">
      <el-row style="font-size: 10px;color: #545c64;padding: 0;position: relative;top: -10px;">
        <el-col :span="8"><div>歌曲名</div></el-col>
        <el-col :span="5"><div>专辑</div></el-col>
        <el-col :span="5"><div>歌曲种类</div></el-col>
        <el-col :span="2"><div>时长</div></el-col>
        <el-col :span="2"><div>操作</div></el-col>
      </el-row>
      <el-scrollbar max-height="340px" class="scrollbar">
        <div v-for="song in songList.songList" :key="song" class="row">
          <el-row>
            <el-col :span="8"><div>{{ song.songName }}</div></el-col>
            <el-col :span="5"><div>{{ song.albumName }}</div></el-col>
            <el-col :span="5"><div>{{ song.songType }}</div></el-col>
            <el-col :span="2"><div>{{ song.songLength }}</div></el-col>
            <el-col :span="1"><div><el-button color="#94defc" :icon="Headset" circle @click="playThisMusic(song)"/></div></el-col>
            <el-col :span="1"><div><el-button color="#94defc" :icon="Plus" circle @click="addSongToPlayList(song)"/></div></el-col>
            <el-col :span="1"><div><el-button color="#94defc" :icon="Download" circle @click=""/></div></el-col>
          </el-row>
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>

<script>
import { useRoute,useRouter } from 'vue-router';
import {ref,reactive,inject,onMounted} from 'vue';
import { Back } from '@element-plus/icons-vue';
import { Headset } from '@element-plus/icons-vue';
import { Plus } from '@element-plus/icons-vue';
import { Download } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import api from "@/router";
import axios from 'axios';
import Play from "@/views/Index";
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
    //专辑中的歌曲表
    let songList=reactive({songList: []});
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
    const selectSongByAlbumId=(albumId)=>{
      axios.get(api.baseUrl.baseUrl+'/selectSongByPara',{params:{singerId: '',songId:'',songName:'',albumId:albumId,songTypeId:''}})
          .then((response)=>{
            songList.songList = [];
            for(let i in response.data){
              songList.songList.push({
                songName: response.data[i].songName,
                albumName: response.data[i].album.albumName,
                songType: response.data[i].songType.songTypeName,
                singerName: response.data[i].singer.singerName,
                songLength: response.data[i].songLength,
                songPlayUrl: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].fileId,
                albumImgUrl: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].album.albumImgId
              });
            }
          });
    }
    const addSongToPlayList=(song)=>{
      playList.push({
        songName: song.songName,
        songLength: song.songLength,
        singerName: song.singerName,
        songPlayUrl: song.songPlayUrl,
        albumImgUrl: song.albumImgUrl
      });
      ElMessage({
        message: '添加成功',
        type: 'success',
      })
    }
    const playThisMusic=(song)=>{
      addSongToPlayList(song);
      Play.setup().Play(playList.length-1);
    }
    onMounted(()=>{
      selectAlbumByAlbumId(albumId.value);
      selectSongByAlbumId(albumId.value);
    });
    return{albumId,Back,playList,albumInfo,songList,Download,Plus,Headset,backToAlbumView,addSongToPlayList,playThisMusic}
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
.scrollbar{
  height: 490px;
  width: 900px;
  border-radius: 20px;
  background-color: rgba(211,220,230,0.5);
  z-index: 0;
}
.row{
  height: 30px;
  padding: 10px;
  border: 2px solid rgba(230,234,239,0.36);
}
.row:hover{
  border: 2px solid rgba(0, 0, 0, 0.36);
}
</style>