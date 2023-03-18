<template>
<div>

  <el-table
      :data="tableData"

      style="width: 100%;height: 448px">
    <el-table-column
        prop="registerId"
        label="序号"
        width="50">
    </el-table-column>
    <el-table-column
        prop="idCard"
        label="身份证"
        width="180">
    </el-table-column>
    <el-table-column
        prop="patientName"
        label="姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="sexType"
        label="性别"
        width="180">
    </el-table-column>
    <el-table-column
        prop="doctorId"
        label="医生序号"
        width="180">
    </el-table-column>
    <el-table-column
        prop="doctorName"
        label="医生姓名"
        width="180">
    </el-table-column>
    <el-table-column
        prop="partType"
        label="科室"
        width="180">
    </el-table-column>
    <el-table-column
        prop="registerDate"
        label="预约日期"
        width="180">
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
  <el-form :model="ruleForm" style="width: 50%; margin-left: 360px;margin-top: 50px" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">


    <el-form-item label="预约日期" style="width: 400px">
      <el-date-picker type="date"
                      format="yyyy-MM-dd"
                      value-format="yyyy-MM-dd"
                      name="datePicker2"
                      placeholder="选择日期"
                      v-model="ruleForm.registerDate"
                      style="width: 100%;">
      </el-date-picker>
    </el-form-item>
    <el-form-item  style="width: 400px">
      <el-button type="primary" @click="submitForm('ruleForm')">搜索</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
const axios = require("axios")
export default {
  name: "RegisterDateByDate",
  data(){
    return{
      total:42,
      currentPage:1,
      totalPage:4,
      pageSize:8,
      tableData: [],
      allData:[],
      ruleForm: {
        registerDate:'',

      },
      rules:{
        registerDate: [
          { type: 'string', required: true, message: '请选择日期' }
        ]
      }
    }

  },
  methods:{
    currentChange(val){
      this.currentPage=val
      this.getRegisterData()
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;

          axios.get("http://localhost:9090/registerDataByDate/"+this.ruleForm.registerDate).then(function (res){
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

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>