<template>
  <div class="main">
    <div style="font-style: oblique;font-size: 30px;color: #f9f9f9">Zay-music</div>
    <div class="font_title">
      <div style="font-size: 60px;color: #f9f9f9;font-family: MiSans-Light;">用心聆听生活，用爱感受美好。</div>
      <div style="font-size: 30px;color: #f9f9f9;font-family: MiSans-Light;
                  font-style: oblique;position: absolute;right: 10px;">——&nbsp;&nbsp;张傲宇</div>
    </div>
    <div class="register_window">
      <div>用户注册</div>
      <div style="margin: 40px">
        <el-form
            label-position="left"
            label-width="70px"
            :model="userRegisterList"
            style="max-width: 400px">
          <el-form-item label="用户名">
            <el-input v-model="userRegisterList.userName" placeholder="请输入用户名"/>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="userRegisterList.phoneNumber" placeholder="请输入手机号"/>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="userRegisterList.email" placeholder="请输入邮箱"/>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="userRegisterList.userPassword1" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item label="再次输入密码">
            <el-input v-model="userRegisterList.userPassword2" placeholder="再次输入密码"/>
          </el-form-item>
          <el-form-item label="验证码">
            <el-input v-model="userRegisterList.verificationCode" placeholder="请输入验证码"/>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {reactive,ref} from 'vue';
export default {
  name: "userRegister",
  setup(){
    const userRegisterList=reactive({
      userName: '',
      phoneNumber: '',
      email: '',
      userPassword1: '',
      userPassword2: '',
      verificationCode: ''
    });
    return{
      userRegisterList
    }
  }
}
</script>

<style scoped>
.main{
  background-image: url("../../assets/image/reg_bg_min.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 100%;
  height: 100vh;
}
.register_window{
  background-color: rgba(255,255,255,0.5);
  height: 600px;
  width: 500px;
  border-radius: 10px;
  position: absolute;
  top: 100px;
  left: 60%;
}
.font_title{
  position: relative;
  top: 200px;
  left: 100px;
  width: 40%;
}
</style>