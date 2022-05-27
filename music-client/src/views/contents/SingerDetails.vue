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
      <div style="background-color: #94DEFC;height: 130px;width: 100%;">
        <el-button type="text" :icon="Back" style="margin: 10px;" @click="backToSingerView">返回</el-button>
      </div>
      <el-image style="height: 350px;width: 350px;border-radius: 30px;border: 7px solid #ffffff;position: relative;
                      top: -70px;left: 100px;" :src="singerInfo.info.singerImg">
        <template #error>
          <div class="image-slot">
            <el-icon><icon-picture /></el-icon>
          </div>
        </template>
      </el-image>
      <div style="width: 300px;font-weight: bolder;position: relative;left: 50px;top: -50px;">
        <ul>
          <li style="margin: 10px;">生日:{{singerInfo.info.singerBirth}}</li>
          <li style="margin: 10px;">国籍:{{singerInfo.info.singerLocation}}</li>
        </ul>
      </div>
      <div style="position: absolute;top: 210px;left: 500px;">
        <div style="width: 200px;font-weight: bolder;font-size: 25px;margin-bottom: 10px;">{{singerInfo.info.singerName}}</div>
        <div style="width: 600px;font-size: 15px;color: #545c64;font-family: MiSans sans-serif">
          <el-scrollbar max-height="100px">
            {{singerInfo.info.singerIntroduction}}
          </el-scrollbar>
        </div>
      </div>
      <el-tabs v-model="activeName" style="position: absolute;top: 350px;left: 500px;">
        <el-tab-pane label="单曲" name="first">
          <el-row style="font-size: 10px;color: #545c64;padding: 10px;position: relative;top: -10px;">
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
        </el-tab-pane>
        <el-tab-pane label="专辑" name="second">
          <el-scrollbar height="340px" class="scrollbar" style="top: 0">
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
          </el-scrollbar>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script setup>
  //注意Router与Route的区别!!!!
  //Router是发送 Route是接收
  import { useRoute,useRouter } from 'vue-router';
  import {ref,reactive,inject} from 'vue';
  import axios from 'axios';
  import api from '../../router/index';
  import util from '../../util/util';
  import { ElMessage } from 'element-plus';
  import Play from '../Index';
  import { Picture as IconPicture } from '@element-plus/icons-vue';
  import { Back } from '@element-plus/icons-vue';
  import { Headset } from '@element-plus/icons-vue';
  import { Plus } from '@element-plus/icons-vue';
  import { Download } from '@element-plus/icons-vue';
  const route = useRoute();
  const router = useRouter();
  const singerId=ref(route.params.singerId);
  const activeName = ref('first');
  //获取全局的播放列表
  let playList = inject('global').playList;
  let singerInfo=reactive({info: {
      singerId: '',
      singerName: '',
      singerBirth: '',
      singerIntroduction: '',
      singerLocation: '',
      singerImg: ''
    }});
  selectSingerBySingerId(singerId.value);
  let songList=reactive({songList: []});
  selectSongBySingerId(singerId.value);
  let albumList = reactive({albumList: []});
  selectAlbumBySingerId(singerId.value);
  function selectSingerBySingerId(singerId){
    axios.get(api.baseUrl.baseUrl+'/selectSingerByPara',{params:{singerId: singerId,singerName: '',singerLocation: ''}})
        .then((response)=>{
          singerInfo.info.singerId = response.data[0].singerId;
          singerInfo.info.singerName = response.data[0].singerName;
          singerInfo.info.singerBirth = util.dateFormat(response.data[0].singerBirth);
          singerInfo.info.singerIntroduction = response.data[0].singerIntroduction;
          singerInfo.info.singerLocation = response.data[0].singerLocation;
          singerInfo.info.singerImg = api.baseUrl.baseUrl+'/previewFile/'+response.data[0].singerImgId;
    });
  }
  function selectAlbumBySingerId(singerId){
    axios.get(api.baseUrl.baseUrl+'/selectAlbumByPara',{params:{albumId: '',albumName: '',singerId: singerId,year: ''}})
        .then((response)=>{
          albumList.albumList = [];
          for(let i in response.data){
            albumList.albumList.push({
              albumName: response.data[i].albumName,
              year: response.data[i].year,
              albumImgUrl: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].albumImgId
            });
          }
    });
  }
  function selectSongBySingerId(singerId){
    axios.get(api.baseUrl.baseUrl+'/selectSongByPara',{params:{singerId: singerId,songId:'',songName:'',albumId:'',songTypeId:''}})
        .then((response)=>{
          songList.songList = [];
          for(let i in response.data){
            songList.songList.push({
              songName: response.data[i].songName,
              albumName: response.data[i].album.albumName,
              songType: response.data[i].songType.songTypeName,
              songLength: response.data[i].songLength,
              songPlayUrl: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].fileId,
              albumImgUrl: api.baseUrl.baseUrl+'/previewFile/'+response.data[i].album.albumImgId
            });
          }
        });
  }
  function addSongToPlayList(song){
    playList.push({
      songName: song.songName,
      songLength: song.songLength,
      singerName: singerInfo.info.singerName,
      songPlayUrl: song.songPlayUrl,
      albumImgUrl: song.albumImgUrl
    });
    ElMessage({
      message: '添加成功',
      type: 'success',
    })
  }
  function playThisMusic(song){
    addSongToPlayList(song);
    Play.setup().Play(playList.length-1);
  }
  function backToSingerView(){
    router.push({
      path: '/SingerView'
    });
  }
</script>

<style scoped>
#particles-js{
  width: 100%;
}
#main{
  position: absolute;
  top: 60px;
  width: 100%;
  height: 100%;
}
#particles{
  background-color: #f9f9f9;
}
.scrollbar{
  height: 340px;
  width: 900px;
  border-radius: 20px;
  background-color: rgba(211,220,230,0.5);
  z-index: 0;
  position: relative;
  top: -15px;
}
.row{
  height: 30px;
  padding: 10px;
  border: 2px solid rgba(230,234,239,0.36);
}
.album_list_item{
  width: 250px;
  height: 150px;
  float: left;
  margin: 25px;
  border-radius: 10px;
  background-color: rgba(211,220,230,0.5);
  border: 2px solid rgba(211,220,230,0.5);
}
.album_list_item:hover{
  border: 2px solid #99a9bf;
}
.row:hover{
  border: 2px solid rgba(0, 0, 0, 0.36);
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