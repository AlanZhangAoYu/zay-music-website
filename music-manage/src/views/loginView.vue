<template>
	<div id="login_window">
		<div id="login_form">
			<div id="text">Zay-music后台管理系统</div>
			<el-form
			    label-width="100px"
			    style="max-width: 460px;margin: 20px;">
			    <el-form-item label="管理员账号">
			      <el-input v-model="formLabelAlign.admin_account" />
			    </el-form-item>
			    <el-form-item label="密码">
			      <el-input v-model="formLabelAlign.admin_password" />
			    </el-form-item>
				<el-button type="primary" style="width: 150px;margin-left: 50px;">登录</el-button>
				<el-button style="width: 150px;margin-left: 20px;">取消</el-button>
			  </el-form>
		</div>
	</div>
</template>

<script setup>
import { reactive, ref } from 'vue'

const labelPosition = ref('right')

const formLabelAlign = reactive({
  admin_account: '',
  admin_password: '',
})
</script>

<style>
	#login_window {
		margin: 0px;
		height: 100vh;
		background-image: url(../assets/image/登录界面背景.png);
	}
	#login_form {
		border-radius: 5px;
		position: absolute;
		top: 270px;
		left: 500px;
		height: auto;
		width: 450px;
		background-color: rgba(255,255,255,0.5);
	}
	#text{
		font-size: 30px;
		position: relative;
		left: 60px;
		margin: 10px;
	}
</style>
