<template>
<div>
  <el-table
      :data="tableData"

      style="width: 100%;">
    <el-table-column
        prop="doctorId"
        label="医生编号"
        width="100">
    </el-table-column>
    <el-table-column
        prop="doctorName"
        label="医生姓名"
        width="100">
    </el-table-column>
    <el-table-column
        prop="PTR"
        label="职称"
        width="100">
    </el-table-column>
    <el-table-column
        prop="password"
        label="密码"
        width="100">
    </el-table-column>
    <el-table-column
        prop="partType"
        label="科室"
        style="text-align: center"
        width="100">
    </el-table-column>
    <el-table-column
        prop="using"
        label="账户状态"
        width="100">
    </el-table-column>
    <el-table-column
        prop="sex"
        label="性别"
        width="50">
    </el-table-column>
    <el-table-column
        prop="birth"
        label="预约日期"
        width="100">
    </el-table-column>
    <el-table-column
        prop="partTypeId"
        label="科室编号"
        width="100">
    </el-table-column>
    <el-table-column
        prop="good"
        :show-overflow-tooltip='true'
        label="擅长"
        width="300">
    </el-table-column>
  </el-table>
  <el-pagination
      background
      layout="prev, pager, next"
      @current-change="currentChange"
      :current-page="currentPage"
      :page-size="pageSize"
      :total="total">
  </el-pagination>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "DoctorInfoView.vue",
  data(){
    return{
      total:42,
      currentPage:1,
      totalPage:4,
      pageSize:10,
      tableData: [],
      allData:[],
      ruleForm: {
        name:'',

      },
    }
  },
  methods:{
    currentChange(val){
      this.currentPage=val
      var _this = this;
      axios.get("http://localhost:9090/getDoctorAllData").then(function (res){
        _this.allData = res.data
        // console.log( _this.allData)
        // console.log( _this.allData)
        //allData为全部数据，tableData是目前表格绑定的数据
        _this.tableData = _this.allData.slice(
            (_this.currentPage - 1) * _this.pageSize,
            _this.currentPage * _this.pageSize
        );

        _this.total=_this.allData.length
      })
    },
  },
  created() {
    var _this = this;
    axios.get("http://localhost:9090/getDoctorAllData").then(function (res){
      _this.allData = res.data
      // console.log( _this.allData)
      // console.log( _this.allData)
      //allData为全部数据，tableData是目前表格绑定的数据
      _this.tableData = _this.allData.slice(
          (_this.currentPage - 1) * _this.pageSize,
          _this.currentPage * _this.pageSize
      );

      _this.total=_this.allData.length
    })
  }
}
</script>

<style scoped>



</style>