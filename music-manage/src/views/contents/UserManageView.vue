<template>


	<div>
    <div>用户管理</div>
    <span><el-button type="primary" @click="searchUserVisible = true">搜索用户</el-button></span>
    <span><el-button type="primary" @click="addUserVisible = true">添加新用户</el-button></span>
    <div id="user_table">
      <el-table :data="tableData" height="350" border stripe style="width: 100%">
        <el-table-column prop="userAccount" label="用户账号" width="180" />
        <el-table-column prop="userName" label="用户名" width="180" />
        <el-table-column prop="userSex" label="性别" width="80" />
        <el-table-column prop="phoneNumber" label="电话号码" width="180" />
        <el-table-column prop="email" label="Email" width="180" />
        <el-table-column prop="createTime" label="注册时间" width="180" />
        <el-table-column prop="updateTime" label="上次修改时间" width="180" />
        <el-table-column fixed="right" label="操作" width="270">
          <template #default>
            <el-button type="primary" size="small" @click="">详细信息</el-button>
            <el-button type="warning" size="small">编辑信息</el-button>
            <el-button type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination background layout="prev, pager, next" :total="10" />
  </div>


  <el-dialog v-model="searchUserVisible" title="请选择搜索条件">
    <el-form :model="searchForm">
      <el-form-item label="用户账号">
        <el-input v-model="searchForm.userAccount" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="searchForm.userName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户性别">
        <el-select v-model="searchForm.userSex" placeholder="选择用户性别">
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
          <el-option label="保密" value="0" />
        </el-select>
      </el-form-item>
      <el-form-item label="用户手机号">
        <el-input type="number" v-model="searchForm.phoneNumber" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户Email">
        <el-input v-model="searchForm.email" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户所在地">
        <el-input v-model="searchForm.userLocation" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="searchUserVisible = false">取消</el-button>
        <el-button type="primary" @click="searchUserVisible = false">查询</el-button>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="addUserVisible" title="请填写用户信息">
    <el-form :model="addForm">
      <el-form-item label="用户账号">
        <el-input v-model="addForm.userAccount" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="addForm.userName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="addForm.userPassword" type="password" placeholder="请输入密码" show-password/>
      </el-form-item>
      <el-form-item label="再次输入密码">
        <el-input v-model="again_password" type="password" placeholder="请输入密码" show-password/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addUserVisible = false">取消</el-button>
        <el-button type="primary" @click="addUserVisible = false">插入</el-button>
      </span>
    </template>
  </el-dialog>


</template>

<script setup>
  import {reactive,ref} from "vue";

  const searchUserVisible = ref(false);
  const addUserVisible = ref(false);
  const searchForm = reactive({
    userAccount: '',
    userName: '',
    userSex: '',
    phoneNumber: '',
    email: '',
    userLocation: ''
  });
  const again_password = ref('');
  const addForm = reactive({
    email: '',
    userName: '',
    userPassword: '',
    //以下内容注册时不用填写，插入数据库时用默认值，注册好后进入个人中心可修改
    userSex: 0,
    userBirthday: '',
    phoneNumber: '',
    userIntroduction: '',
    userLocation: '',
    userImgId: '',
    createTime: '',
    updateTime: ''
  });
  const tableData=[
    {
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      userImageId: "dhjsakdh",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    },{
      userAccount: "z795485146@qq.com",
      userName: "凌晨两点半",
      userPassword: "ewqedafdczfcaf",
      salt: "wsdftg",
      userSex: "男",
      userBirthday: "2001-04-20",
      phoneNumber: "145****1985",
      email: "z795482423@qq.com",
      userLocation: "四川·成都",
      createTime: "2022-04-09 12:26:30",
      updateTime: "2022-04-09 12:26:30"
    }
  ];
</script>

<style>
</style>
