<template>
  <div>
    <div >
      <h1>
        医生问诊系统
      </h1>
      <el-row>
        <div style="float: right">
          <span>医生编号:{{this.$route.params.doctorId}}</span>

          <el-button style="color:#0340f9;" type="text" @click="open">【退 出】</el-button>
        </div>
      </el-row>
      <el-row>
        <div style="float: right;margin-top: 5px">
          <span>{{dateFormat(newDate)}}</span>
        </div>
      </el-row>


    </div>
    <el-table
        :data="tableData"
        border
        :row-class-name="tableRowClassName"
        style="height: 500px;width: 60%;margin-left:20%;margin-right: 20%;margin-top:0;overflow: scroll">
      <el-table-column
          fixed
          type="index"
          label="编号"
          width="100">

      </el-table-column>
      <el-table-column

          prop="patientName"
          label="姓名"
          width="150">
      </el-table-column>
      <el-table-column
          prop="idCard"
          label="身份证号"
          width="300">
      </el-table-column>
      <el-table-column
          prop="sexType"
          label="性别"
          width="120">
      </el-table-column>
      <el-table-column
          prop="registerDate"
          label="挂号日期"
          width="125">
      </el-table-column>
      <el-table-column
          label="操作"
          width="115">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope)" type="text" size="small">问诊</el-button>
          <el-button @click="handleOver(scope)" type="text" size="small">结束</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>





<script>
import axios from "axios";

export default {
  data() {
    return {
      NowIndex:0,
      newDate: this.dateFormat(),
      tableData:[
        {
          patientName:'赵世浩',
          idCard:'123456789',
          sexType:'男',
          registerDate:'',
        }

      ]
    }
  },

// 挂载时间
  methods: {
    open() {
      this.$confirm('确定退出当前账号, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$router.push("/doctorLogin");
        this.$message({
          type: 'success',
          message: '退出成功!'
        });
      }).catch(() => {
        this.exits = false;
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    },
    //判断是否已经登录状态
    isLogin() {
      //判断sessionStorage中是否有登录信息
      if (sessionStorage.getItem("user") != null && sessionStorage.getItem("userToken")) {
        //存在登录信息就从sessionStorage中提取状态再传值给vuex中
        this.$store.commit("userStatus", sessionStorage.getItem("user"));
      } else {
        //登录不成功就将vuex中的state清空
        this.$store.commit("userStatus", null);
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
        this.$router.push("/doctorLogin");
        this.$message({
          message: '警告，请登录账户',
          type: 'warning'
        });
      }
    },
    // 回调函数
    tableRowClassName({ rowIndex }) {
      console.log(rowIndex) // 每一行的索引
      if (rowIndex === this.NowIndex) {
        return 'warning-row'
      }
      return ''
    },
    // 时间格式化
    dateFormat () {
      var date = new Date()
      var year = date.getFullYear()
      var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
      var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
      var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
      var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      let week = date.getDay() // 星期
      let weekArr = [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ]
      // 拼接 时间格式处理
      return year + '年' + month + '月' + day + '日 ' + hours + ':' + minutes + ':' + seconds + ' ' + weekArr[week]
    },
    handleClick(scope){
      alert("叫号："+scope.$index+"，姓名："+scope.row.patientName);
    },
    handleOver(scope){
      if (scope.$index + 1< this.tableData.length){
        this.NowIndex = scope.$index + 1
        axios.get("http://localhost:9090/modifyDoctorTodayCount/"+this.$route.params.doctorId+"/"+this.NowIndex)
      }else{
        alert("问诊完成！")
      }
    }
  },

  mounted () {
    let that = this
    this.timer = setInterval(function () {
      that.newDate = new Date().toLocaleString()
    })
  },

  created() {
    this.isLogin();
    this.ver();
    var _this = this
    axios.get("http://localhost:9090/getRegisterData/"+this.$route.params.doctorId).then(function (res) {
      _this.tableData = res.data
    })
    axios.get("http://localhost:9090/getDoctorTodayCount/"+this.$route.params.doctorId).then(function (res) {
      // 有数据，不用再
     _this.NowIndex = res.data
    })


  },

  // 销毁时清除计时器
  beforeDestroy: function () {
    if (this.timer) {
      clearInterval(this.timer)
    }
  }

}
</script>


<style>
.el-table .warning-row {
  background: #d95454 !important;
}

.el-table .warning-row:hover>td {
  background: #bbb !important;
}

</style>