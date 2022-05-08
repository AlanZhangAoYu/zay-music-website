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
      {{singerInfo.info.singerName}}
      {{singerInfo.info.singerIntroduction}}
    </div>
  </div>
</template>

<script setup>
  //注意Router与Route的区别!!!!
  //Router是发送 Route是接收
  import { useRoute } from 'vue-router';
  import {ref,reactive} from 'vue';
  import axios from 'axios';
  import api from '../../router/index';
  const route = useRoute();
  const singerId=ref(route.params.singerId);
  let singerInfo=reactive({info: {
      singerId: '',
      singerName: '',
      singerBirth: '',
      singerIntroduction: '',
      singerLocation: '',
      singerImg: ''
    }});
  selectSingerBySingerId(singerId.value);
  function selectSingerBySingerId(singerId){
    axios.get(api.baseUrl.baseUrl+'/selectSingerByPara',{params:{singerId: singerId,singerName: '',singerLocation: ''}}).then((response)=>{
      singerInfo.info.singerId = response.data.singerId;
      singerInfo.info.singerName = response.data.singerName;
      singerInfo.info.singerBirth = dateFormat(response.data.singerBirth);
      singerInfo.info.singerIntroduction = response.data.singerIntroduction;
      singerInfo.info.singerLocation = response.data.singerLocation;
      singerInfo.info.singerImg = api.baseUrl.baseUrl+'/previewFile/'+response.data.singerImgId;
    });
  }
  function dateFormat(time){
    let date = new Date(time);
    let year = date.getFullYear();
    let month = date.getMonth()+1;
    let day = date.getDate();
    return year + "-" + month + "-" + day;
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
</style>