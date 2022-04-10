<template>
	<div class="common-layout">
	    <el-container>
	      <el-header style="padding: 0;">
			  <div id="header" style="background-color: black;height: 70px;position: absolute;width: 100%">
				  <el-icon color="#ffffff" size="35px" style="position: relative;top: 15px;left: 10px;"><Menu /></el-icon>
				  <span id="head_title">Zay-music后台管理系统</span>
				  <span style="position: relative;left: 900px;top: 10px">
            <el-dropdown>
              <span class="el-dropdown-link" style="color: #FFFFFF;">
                <span style="font-size: 20px">{{ adminName }}</span>
                <el-avatar :size="40" :src="circleUrl" />
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>个人中心</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </span>
			  </div>
		  </el-header>
	      <el-container style="margin-top: 10px;">
	        <el-aside width="200px">
				<el-scrollbar>
					<el-menu>
						<el-menu-item index="1">
							<template #title>
								<router-link to="/SystemIndex" class="a">
									<el-icon><house /></el-icon>系统首页
								</router-link>
							</template>
						</el-menu-item>
						<el-menu-item index="2">
							<template #title>
								<router-link to="/UserManage" class="a">
									<el-icon><user /></el-icon>用户管理
								</router-link>
							</template>
						</el-menu-item>
						<el-menu-item index="3">
							<template #title>
								<router-link to="/SongManage" class="a">
									<el-icon><headset /></el-icon>歌曲管理
								</router-link>
							</template>
						</el-menu-item>
						<el-menu-item index="4">
							<template #title>
								<router-link to="/SingerManage" class="a">
									<el-icon><mic /></el-icon>歌手管理
								</router-link>
							</template>
						</el-menu-item>
						<el-menu-item index="5">
							<template #title>
								<router-link to="/AlbumManage" class="a">
									<el-icon><wallet /></el-icon>专辑管理
								</router-link>
							</template>
						</el-menu-item>
						<el-menu-item index="6">
							<template #title>
								<router-link to="/CommentManage" class="a">
									<el-icon><chat-line-square /></el-icon>评论管理
								</router-link>
							</template>
						</el-menu-item>
					</el-menu>
				</el-scrollbar>
			</el-aside>
	        <el-main>
				<router-view></router-view>
			</el-main>
	      </el-container>
	    </el-container>
	  </div>
</template>

<script setup>
  import { Menu } from '@element-plus/icons-vue';
  import { House } from '@element-plus/icons-vue';
  import { User } from '@element-plus/icons-vue';
  import { Headset } from '@element-plus/icons-vue';
  import { Mic } from '@element-plus/icons-vue';
  import { Wallet } from '@element-plus/icons-vue';
  import { ChatLineSquare } from '@element-plus/icons-vue';
  import {useRouter} from 'vue-router';

	const router = useRouter();
	const adminName = localStorage.getItem('adminName');
  const circleUrl = 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png';
</script>

<style>
	#head_title{
		color: white;
		font-size: 35px;
		font-family: '幼圆';
		margin: 20px;
		position: relative;
		top: 15px;
	}
	.a{
		text-decoration: none;
	}
</style>
