<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            @select="handleSelect">
          <img src="../assets/image/logo.png" width="30" height="30" style="margin-top: 10px;margin-left: 10px;"/>
          <span style="margin: 10px; color: azure; font-size: 30px;font-family: serif; font-style: oblique;">Zay-music</span>
          <el-menu-item index="1"><router-link to="/MainView" class="a">首页</router-link></el-menu-item>
          <el-menu-item index="2"><router-link to="/ListView" class="a">榜单</router-link></el-menu-item>
          <el-menu-item index="3"><router-link to="/SingerView" class="a">歌手</router-link></el-menu-item>
          <el-menu-item index="4"><router-link to="/AlbumView" class="a">专辑</router-link></el-menu-item>
          <el-button type="primary" :icon="Avatar" circle style="position: absolute;margin-top: 15px;right: 20px;"
                     @click="userLoginVisible = true"/>
        </el-menu>
      </el-header>
      <el-main style="width: 100%">
        <router-view/>
      </el-main>
      <el-footer>
        <div class="footer">
          <span style="position: relative;top: 35%;left: 35%">Designed by ZAY(z7954221907482@163.com) @All Rights Reserved</span>
        </div>
      </el-footer>
    </el-container>
  </div>
  <div class="play_bar_div">
    <el-button type="info" @click="PlayBar" :icon="playBarIcon" style="position: relative;left: 95%;"/>
    <div ref="play_bar" style="display: none;width: 100%;height: 80px;background-color: #545c64;">
      <div style="position: relative;margin: 0 auto;width: 960px;height: 80px;">
        <div style="position: absolute;width: 160px;height: 80px;">
          <!--播放条左部分: 上一首 播放/暂停 下一首-->
          <a class="play_bar_icon play_bar_prev" @click="lastMusic(playListIndex)"></a>
          <a class="play_bar_icon play_bar_play" @click="playOrSuspend(playListIndex)" ref="play_bar_play"></a>
          <a class="play_bar_icon play_bar_next" @click="nextMusic(playListIndex)"></a>
        </div>
        <div style="position: absolute;left: 190px;width: 60px;height: 60px;">
          <!--播放条中间部分: 专辑封面 -->
          <a>
            <el-image :src="albumImgUrl" style="height: 60px;width: 60px;margin-top: 10px">
              <template #error>
                <div class="image-slot">
                  <img src="../assets/image/default_image.jpg"/>
                </div>
              </template>
            </el-image>
          </a>
        </div>
        <div style="position: absolute;left: 270px;width: 400px;height: 80px">
          <!--播放条中间上半部分: 歌曲名 长度-->
          <div style="padding-top: 18px;height: 24px;cursor: default;">
            <!--歌曲名-->
            <div style="float: left;width: 290px;height: 24px;overflow: hidden;"><span style="color: #f9f9f9;font-size: 13px">{{songName}}</span></div>
            <!--歌曲长度-->
            <div style="float: right;height: 24px;">
              <span style="color: #f9f9f9;font-size: 13px">
                {{util.formatSongTime(songTime)}}/{{ util.formatSongTime(totalTime) }}
              </span>
            </div>
          </div>
          <!--播放条中间下半部分: 进度条-->
          <div style="margin-top: 11px;height: 5px;">
            <el-slider :max="totalTime" v-model="songTime" style="height: 0" :show-tooltip="false" @input="clickSlider(songTime)"/>
          </div>
        </div>
        <div style="position: absolute;left: 710px;width: 260px;height: 80px;">
          <!--播放条右部分: 音量控制按钮-->
          <div style="position: relative;top: 30px;width: 16px;">
            <span class="play_bar_icon volume_button" @mouseover="volumeAppear"></span>
          </div>
          <!--音量控制面板-->
          <div ref="volume_panel" style="display: none;height: 124px;width: 38px;background-color: #545c64;position: absolute;top: -100px;left: -13px"
               @mouseleave="volumeDisappear">
            <el-slider v-model="volume" vertical height="100px" width="29px"/>
          </div>
          <div style="position: relative;top: 15px;left: 50px;">
            <!--播放模式按钮-->
            <a ref="playback_mode_button" class="play_bar_icon playback_mode_button" @click="playbackModePanelAppear"></a>
            <!--播放模式选项面板(列表循环、单曲循环、随机播放)-->
            <div ref="playback_mode_panel" style="display: none;height: 115px;width: 120px;background-color: #545c64;position: absolute;top: -130px;">
              <div style="padding: 10px;margin: 0">
                <div style="height: 30px;">
                  <div>
                    <span class="play_bar_icon" style="margin: 5px;background-position: -64px -179px;height: 16px;width: 16px;float: left"></span>
                    <el-button style="float: left;color: #f9f9f9;" type="text" @click="selectPlayMode0" plain>列表循环</el-button>
                  </div>
                </div>
                <div style="height: 30px;">
                  <div>
                    <span class="play_bar_icon" style="margin: 5px;background-position: 0 -179px;height: 16px;width: 16px;float: left"></span>
                    <el-button style="float: left;color: #f9f9f9;" type="text" @click="selectPlayMode1" plain>单曲循环</el-button>
                  </div>
                </div>
                <div style="height: 30px;">
                  <div>
                    <span class="play_bar_icon" style="margin: 5px;background-position: -128px -179px;height: 16px;width: 16px;float: left"></span>
                    <el-button style="float: left;color: #f9f9f9;" type="text" @click="selectPlayMode2" plain>随机播放</el-button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div style="position: relative;top: 0;left: 100px;">
            <!--歌曲下载按钮-->
            <a class="play_bar_icon" style="width: 16px;height: 16px;background-position: -240px -32px;" href="javascript:void(0);"></a>
          </div>
          <div style="position: relative;top: -23px;left: 150px;">
            <!--播放列表按钮-->
            <el-button :icon="Fold" @click="itemKey = Math.random();playListVisible = true" style="height: 35px;width: 35px;" circle></el-button>
            <!--播放列表抽屉主窗口-->
            <el-drawer
                v-model="playListVisible"
                title="播放列表"
                :direction="direction">
              <el-table :data="playList" :key="itemKey" stripe style="width: 100%">
                <el-table-column prop="songName" label="歌曲名" />
                <el-table-column prop="singerName" label="歌手" width="110"/>
                <el-table-column prop="songLength" label="时长" width="60"/>
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button color="#94defc" :icon="Headset" circle @click="Play(scope.$index)" size="small"/>
                    <el-button color="#94defc" :icon="Download" circle @click="" size="small"/>
                    <el-button color="#94defc" :icon="Delete" circle @click="deleteSongFromPlayList(scope.$index)" size="small"/>
                  </template>
                </el-table-column>
              </el-table>
            </el-drawer>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div style="display: none">
    <audio id="MyAudio_1" ref="myAudio" :src="playUrl" preload="auto" @ended="songEnded(curSongIndex)" @timeupdate="changeSongTime"></audio>
  </div>

  <el-dialog v-model="userLoginVisible" title="用户登录" width="30%" @opened="drawPic('12x5')">
    <el-form v-model="userLoginList">
      <el-form-item label="账号">
        <el-input v-model="userLoginList.userName" placeholder="请输入账号"/>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userLoginList.password" type="password" placeholder="请输入密码" show-password/>
      </el-form-item>
      <el-form-item label="验证码">
        <el-input v-model="userLoginList.verification" placeholder="请输入验证码"/>
        <div>
          <canvas ref="code_picture" width="117" height="32"></canvas>
        </div>
      </el-form-item>
    </el-form>
    <div>没有账号?<el-link type="primary" @click="gotoUserRegister">点我注册</el-link></div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="userLoginVisible = false">取消</el-button>
        <el-button type="primary" @click="userLogin">登录</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
  import {ref,watch,reactive,onMounted} from 'vue';
  import {useRouter} from 'vue-router';
  import axios from 'axios';
  import {Avatar} from '@element-plus/icons-vue';
  import {ArrowUpBold} from '@element-plus/icons-vue';
  import {ArrowDownBold} from '@element-plus/icons-vue';
  import { ElMessage } from 'element-plus';
  import util from '../util/util';
  import { Picture as IconPicture } from '@element-plus/icons-vue';
  import {Fold} from '@element-plus/icons-vue';
  import { Headset } from '@element-plus/icons-vue';
  import { Delete } from '@element-plus/icons-vue';
  import { Download } from '@element-plus/icons-vue';
  import { inject } from 'vue';
  export default {
    setup(){
      const activeIndex = ref('1');
      const play_bar = ref(null);
      const play_bar_play = ref(null);
      const volume_panel = ref(null);
      const playback_mode_panel = ref(null);
      const playback_mode_button = ref(null);
      const code_picture = ref(null);
      const direction = ref('rtl');
      const playListVisible = ref(false);
      const userLoginVisible = ref(false);
      let userLoginList=reactive({
        userName: '',
        password: '',
        verification: ''
      });
      //创建一个网页播放器
      const myAudio= ref(null);
      //获取到的全局播放列表
      let playList = inject('global').playList;
      //当前播放音频地址
      let playUrl = ref('');
      //当前播放的项目在播放列表的索引
      let playListIndex = ref(0);
      //当前播放歌曲名
      let songName = ref('暂无歌曲');
      //当前显示还是隐藏播放栏
      let playBarIcon = ref(ArrowUpBold);
      //当前播放到的歌曲时间
      let songTime = ref(0);
      //当前音量
      let volume = ref(50);
      //当前播放状态 0暂停/1播放
      let playState = ref(0);
      //当前播放模式 0列表循环/1单曲循环/2随机播放
      let playbackMode = ref(0);
      //当前播放歌曲总时长
      let totalTime = ref(0);
      //当前播放歌曲在播放列表中的索引值
      let curSongIndex = ref(0);
      let albumImgUrl = ref('');
      let itemKey = ref(Math.random());
      const imgForm = reactive({
        fileList: []
      });
      const handleSelect = (key, keyPath) => {
        //console.log(key, keyPath);
      }
      //播放条显示或隐藏
      const PlayBar=()=>{
        if(play_bar.value.style.display === 'none'){
          play_bar.value.style.display = 'block';
          playBarIcon.value = ArrowDownBold;
        }else {
          play_bar.value.style.display = 'none';
          playBarIcon.value = ArrowUpBold;
        }
      }
      //当前音频播放或暂停时的动作
      const playOrSuspend=(index)=>{
        if(playState.value === 0){
          //暂停时
          Play(index);
        }else {
          //播放时
          Suspend();
        }
      }
      //当播放音频时要执行的函数(index为要播放播放列表的哪一项的索引)
      const Play=(index)=>{
        //获取当前要播放的歌曲信息
        curSongIndex.value = index;
        const curSongInfo = playList[index];
        playListIndex.value = index;
        songName.value = curSongInfo.songName;
        albumImgUrl.value = curSongInfo.albumImgUrl;
        playUrl.value = curSongInfo.songPlayUrl;
        playState.value = 1;
        play_bar_play.value.style.backgroundPosition = '-60px -60px';
        setTimeout(()=>{
          totalTime.value = myAudio.value.duration;
          myAudio.value.play();
        },myAudio.value.duration);
      }
      //添加监听器监听播放器的改变并实时更新进度条和当前时间
      const changeSongTime=()=>{
        songTime.value = myAudio.value.currentTime;
      }
      //添加监听器监听播放结束时的播放模式并作出相应改变
      const songEnded=(index)=>{
        if(playbackMode.value === 0){
          Play((index+1)%playList.length);
        }else if(playbackMode.value === 1){
          Play(index);
        }
      }
      //当音频暂停时要执行的函数
      const Suspend=()=>{
        songTime.value = myAudio.value.currentTime;
        myAudio.value.pause();
        playState.value = 0;
        play_bar_play.value.style.backgroundPosition = '0 0';
      }
      //监听函数，监听音量滑动条的改变来动态设置音量大小
      watch(volume,()=>{
        myAudio.value.volume = volume.value/100;
      });
      //从播放列表中删除项目
      const deleteSongFromPlayList=(index)=>{
        playList.splice(index,1);
        itemKey.value = Math.random();
        ElMessage({
          message: '移除成功',
          type: 'success',
        })
      }
      //播放当前index的上一首歌曲
      const lastMusic=(index)=>{
        if(index-1 < 0){
          Play(playList.length - 1);
        }else{
          Play(index - 1);
        }
      }
      //播放当前index的下一首歌曲
      const nextMusic=(index)=>{
        Play((index+1)%playList.length);
      }
      //点击进度条改变当前歌曲播放位置
      const clickSlider=(now,index)=>{
        //myAudio.value.play();
      }
      //音量控制面板出现
      const volumeAppear=()=>{
        volume_panel.value.style.display = 'block';
      }
      //音量控制面板消失
      const volumeDisappear=()=>{
        volume_panel.value.style.display = 'none';
      }
      //播放模式面板出现
      const playbackModePanelAppear=()=>{
        playback_mode_panel.value.style.display = 'block';
      }
      //选择了列表循环模式
      const selectPlayMode0=()=>{
        playbackMode.value = 0;
        playback_mode_button.value.style.backgroundPosition = '-64px -179px';
        playback_mode_panel.value.style.display = 'none';
      }
      //选择了单曲循环模式
      const selectPlayMode1=()=>{
        playbackMode.value = 1;
        playback_mode_button.value.style.backgroundPosition = '0 -179px';
        playback_mode_panel.value.style.display = 'none';
      }
      //选择了随机播放模式
      const selectPlayMode2=()=>{
        playbackMode.value = 2;
        playback_mode_button.value.style.backgroundPosition = '-128px -179px';
        playback_mode_panel.value.style.display = 'none';
      }
      const router=useRouter();
      const userRegister=router.resolve({
        path: '/UserRegister'
      });
      const gotoUserRegister=()=>{
        window.open(userRegister.href);
      }
      const randomNum=(min, max)=>{
        return Math.floor(Math.random() * (max - min) + min);
      }
      // 生成一个随机的颜色
      const randomColor=(min, max)=>{
        let r = randomNum(min, max);
        let g = randomNum(min, max);
        let b = randomNum(min, max);
        return 'rgb(' + r + ',' + g + ',' + b + ')';
      }
      //根据后端传入的codeStr画图片
      const drawPic=(codeStr) =>{
        let ctx = code_picture.value.getContext('2d');
        ctx.textBaseline = 'bottom';
        // 绘制背景
        ctx.fillStyle = '#e6ecfd';
        ctx.fillRect(0, 0, 117, 32);
        // 绘制文字
        for (let i = 0; i < codeStr.length; i++) {
          drawText(ctx, codeStr[i], i,codeStr);
        }
        drawLine(ctx);
        drawDot(ctx);
      }
      const drawText=(ctx, txt, i,codeStr)=>{
        // 随机生成字体颜色
        ctx.fillStyle = randomColor(50, 160);
        // 随机生成字体大小
        ctx.font = randomNum(25, 35) + 'px SimHei';
        let x = (i + 1) * (117 / (codeStr.length + 1));
        let y = randomNum(35, 25 - 5);
        let deg = randomNum(-30, 30);
        // 修改坐标原点和旋转角度
        ctx.translate(x, y);
        ctx.rotate(deg * Math.PI / 180);
        ctx.fillText(txt, 0, 0);
        // 恢复坐标原点和旋转角度
        ctx.rotate(-deg * Math.PI / 180);
        ctx.translate(-x, -y);
      }
      const drawLine =(ctx)=> {
        // 绘制干扰线
        for (let i = 0; i < 4; i++) {
          ctx.strokeStyle = randomColor(100, 200);
          ctx.beginPath();
          ctx.moveTo(randomNum(0, 117), randomNum(0, 32));
          ctx.lineTo(randomNum(0, 117), randomNum(0, 32));
          ctx.stroke();
        }
      }
      const drawDot =(ctx) =>{
        // 绘制干扰点
        for (let i = 0; i < 30; i++) {
          ctx.fillStyle = randomColor(0, 255);
          ctx.beginPath();
          ctx.arc(randomNum(0, 117), randomNum(0, 32), 1, 0, 2 * Math.PI);
          ctx.fill();
        }
      }
      const userLogin=()=>{

      }
      return{
        activeIndex,play_bar,play_bar_play,volume_panel,playback_mode_panel,playback_mode_button,
        direction,playListVisible,myAudio,playList,playUrl,playListIndex,songName,playBarIcon,songTime,
        volume,playState,playbackMode,totalTime,albumImgUrl,itemKey,handleSelect,PlayBar,playOrSuspend,
        Play,Suspend,deleteSongFromPlayList,lastMusic,nextMusic,clickSlider,volumeAppear,playbackModePanelAppear,
        selectPlayMode0,selectPlayMode1,selectPlayMode2,volumeDisappear,songEnded,changeSongTime,util,
        Download,Delete,Headset,Fold,ArrowDownBold,ArrowUpBold,Avatar,IconPicture,curSongIndex,userLoginVisible,
        userLoginList,gotoUserRegister,randomNum,randomColor,drawPic,drawText,drawLine,drawDot,code_picture
      }
    },
    methods:{
      drawPic(codeStr){
        this.$nextTick(()=>{
          let ctx = this.code_picture.getContext('2d');
          ctx.textBaseline = 'bottom';
          // 绘制背景
          ctx.fillStyle = '#e6ecfd';
          ctx.fillRect(0, 0, 117, 32);
          // 绘制文字
          for (let i = 0; i < codeStr.length; i++) {
            this.drawText(ctx, codeStr[i], i,codeStr);
          }
          this.drawLine(ctx);
          this.drawDot(ctx);
        });
      }
    }
  }
</script>

<style scoped>
  .a{
    text-decoration: none;
  }
  .footer{
    color: #f9f9f9;
    background-color: #545c64;
    height: 70px;
    width: 100%;
  }
  .play_bar_div{
    width: 100%;
    position: fixed;
    bottom: 0;
    z-index: 2000;
  }
  .play_bar_icon {
    display: block;
    cursor: pointer;
    background: url(../assets/image/btn.png) no-repeat;
  }
  .play_bar_prev{
    width: 36px;
    height: 36px;
    background-position: 0 -143px;
    position: absolute;
    margin-left: -7px;
    margin-top: 22px;
  }
  .play_bar_prev:hover{
    background-position: -36px -143px;
  }
  .play_bar_play{
    width: 60px;
    height: 60px;
    position: absolute;
    margin-left: 43px;
    margin-top: 10px;
  }
  .play_bar_play:hover{
    background-position: -60px 0;
  }
  .play_bar_next{
    width: 36px;
    height: 36px;
    background-position: -144px -143px;
    position: absolute;
    margin-left: 117px;
    margin-top: 22px;
  }
  .play_bar_next:hover{
    background-position: -180px -143px;
  }
  .volume_button{
    width: 16px;
    height: 16px;
    background-position: -64px -195px;
  }
  .volume_button:hover{
    background-position: -80px -195px;
  }
  .playback_mode_button{
    background-position: -64px -179px;
    width: 16px;
    height: 16px;
    line-height: 16px;
  }
  .playback_mode_button:hover{
    background-position: -80px -179px;
  }
  .play_list_item{
    height: 50px;
    margin: 2px;
    padding: 5px;
    border: 2px solid #ffffff;
  }
  .play_list_item:hover{
    border: 2px solid #545c64;
  }
  .font_hover{
    color: #f9f9f9;
  }
  .font_hover:hover{
    color: #01a7fb;
  }
  .el-header{
    padding: 0;
  }
  .el-main{
    padding: 0;
  }
  .el-footer{
    padding: 0;
  }
  .image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    font-size: 50px;
  }
</style>