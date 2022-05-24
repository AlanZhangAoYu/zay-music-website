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
      <!--<div class="pagination" style="position: absolute;left: 40%;top: 650px;">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="currentPage.value"
            :page-size="10"
            layout="prev, pager, next, jumper"
            :total="totalLength.total"/>
      </div>-->
    </div>
  </div>
</template>

<script>
import {ref ,reactive} from 'vue';
import {useRouter} from 'vue-router';
import axios from 'axios';
import api from '../../router/index';
export default {
  name: "Album",
  setup(){
    const router = useRouter();
    let albumList=reactive({albumList:[]});
    const selectAllAlbum = (pageNum)=>{
      router.get(api.baseUrl.baseUrl+'/selectAllAlbum',{params:{pageNum: pageNum,pageSize: 10}})
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
    return{albumList,selectAllAlbum}
  },
  Mount(){}
}
</script>

<style scoped>
#main{
  width: 80%;
  height: 300px;
  margin: auto 0;
  border: 2px solid #545c64;
  position: absolute;
  left: 10%;
  top: 60px;
}
#particles{
  background-color: #f9f9f9;
}
</style>