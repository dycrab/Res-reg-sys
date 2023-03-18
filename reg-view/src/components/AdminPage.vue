<template>
  <div>
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>挂号数据</template>
              <router-link to="/RegisterDataView">
                <el-menu-item  index="/RegisterDataView" >挂号信息</el-menu-item>
              </router-link>

              <el-submenu index="1-2">
                <template slot="title"><i class="el-icon-search"></i>信息查询</template>
                <router-link to="/RegisterDataByDate">
                  <el-menu-item   >按日期</el-menu-item>
                </router-link>
                <router-link to="/RegisterDataByIdCard">
                  <el-menu-item   >按身份证</el-menu-item>
                </router-link>
                <router-link to="/RegisterDataByDoctor">
                  <el-menu-item   >按医生</el-menu-item>
                </router-link>

              </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>医生数据</template>
            <router-link to="/DoctorInfoView">
              <el-menu-item >医生信息</el-menu-item>
            </router-link>
            <el-submenu index="2-2">
              <template slot="title"><i class="el-icon-setting"></i>管理医生</template>
              <router-link to="/DoctorInfoSearch">
                <el-menu-item   >搜索</el-menu-item>
              </router-link>
              <router-link to="/DoctorInfoAdd">
                <el-menu-item   >添加</el-menu-item>
              </router-link>
              <router-link to="/DoctorInfoModify">
                <el-menu-item   >修改</el-menu-item>
              </router-link>
              <router-link to="/DoctorInfoDelete">
                <el-menu-item   >删除</el-menu-item>
              </router-link>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <router-view></router-view>
      </el-container>
    </el-container>
  </div>

</template>

<script>
import {isLogin} from "@/store/getters";

export default {
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(20).fill(item)
    }
  },
  methods:{
    //判断是否已经登录状态
    isLogin() {
      //判断sessionStorage中是否有登录信息
      if (sessionStorage.getItem("admin") != null && sessionStorage.getItem("adminToken")) {
        //存在登录信息就从sessionStorage中提取状态再传值给vuex中
        this.$store.commit("AdminStatus", sessionStorage.getItem("admin"));
      } else {
        //登录不成功就将vuex中的state清空
        this.$store.commit("AdminStatus", null);
      }
      //返回登录状态islogin
      return this.$store.getters.isLogin;
    },
    //通过登录状态来判断用户是否登录执行相关的操作
    ver() {
      console.log(this.$store.state.isLogin)
      if (this.$store.state.isLogin) {
        console.log("已登录");
        // this.$notify({
        //     title: '系统提示',
        //     message: '欢迎管理员：'+this.$store.state.userName,
        //     position: 'bottom-right'
        // });
      } else {
        //如果没有登录就返回登录界面
        this.$router.push("/adminLogin");
        this.$message({
          message: '警告，请登录账户',
          type: 'warning'
        });
      }
    },
  },
  created() {
    this.isLogin();
    this.ver();
  }
};
</script>

<style scoped>
a {
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>