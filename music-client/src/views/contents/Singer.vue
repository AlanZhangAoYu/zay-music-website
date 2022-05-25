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
      <div>共{{totalLength.total}}位歌手</div>
      <div id="singerList" style="width: 100%">
        <div v-for="singer in singerList.singerList" style="float: left;width: 200px;margin: 20px;">
          <el-card class="card" :body-style="{ padding: '0px' }" shadow="hover">
            <a href="javascript:void(0);" @click="gotoSingerDetail(singer.singerId)">
              <el-image :src="singer.singerImg" style="width: 200px;height: 200px;">
                <template #error>
                  <div class="image-slot">
                    <el-icon><icon-picture /></el-icon>
                  </div>
                </template>
              </el-image>
              <div style="padding: 14px">
                <span style="color: #475669">{{singer.singerName}}</span>
              </div>
            </a>
          </el-card>
        </div>
      </div>
      <div class="pagination" style="position: absolute;left: 40%;top: 650px;">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="currentPage.value"
            :page-size="10"
            layout="prev, pager, next, jumper"
            :total="totalLength.total"/>
      </div>
    </div>
  </div>
</template>

<script setup>
  import {ref ,reactive} from 'vue';
  import axios from 'axios';
  import api from '../../router/index';
  import { useRouter } from 'vue-router';
  import { Picture as IconPicture } from '@element-plus/icons-vue';
  const singerList=reactive({singerList: []});
  const router = useRouter();
  let currentPage = ref(1);
  let totalLength = reactive({total:''});
  selectAllSingerCount();
  selectAllSinger(currentPage.value);
  function selectAllSinger(pageNum){
    axios.get(api.baseUrl.baseUrl+'/selectAllSinger',{params:{pageNum : pageNum, pageSize : 10}})
        .then((response) => {
          singerList.singerList = [];
          for(let i in response.data){
            singerList.singerList.push({
              singerId: response.data[i].singerId,
              singerName: response.data[i].singerName,
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
  function handleCurrentChange (val) {
    currentPage.value = val;
    selectAllSinger(currentPage.value);
  }
  function gotoSingerDetail(singerId){
    router.push({
      path: `/SingerDetail/${singerId}`
    });
  }
</script>

<style scoped>
#main{
  width: 80%;
  position: absolute;
  left: 10%;
  top: 60px;
}
#particles{
  background-color: #f9f9f9;
}
.card {
  -webkit-transition: all 250ms cubic-bezier(0.02, 0.01, 0.47, 1);
  transition: all 250ms cubic-bezier(.02, .01, .47, 1);
}

.card:hover {
  transform: translate(0,-10px);
  transition-delay: 0s !important;
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