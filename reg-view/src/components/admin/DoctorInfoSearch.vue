<template>
  <div>

    <el-table
        :data="tableData"

        style="width: 100%;height: 448px">
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
    <el-form :model="ruleForm" style=";margin-top: 50px" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-row>
        <el-col :span="6">
          <el-form-item label="医生姓名" prop="idCard" style="width: 400px">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item  style="width: 400px">
            <el-button type="primary" @click="submitForm('ruleForm')">搜索</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="科室" prop="idCard" style="width: 400px">
            <el-input v-model="ruleForm.partType"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item  style="width: 400px">
            <el-button type="primary" @click="submitForm2('ruleForm')">搜索</el-button>
          </el-form-item>
        </el-col>

      </el-row>

    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DoctorInfoSearch.vue",
  data(){
    return{
      total:42,
      currentPage:1,
      totalPage:4,
      pageSize:5,
      tableData: [],
      allData:[],
      ruleForm: {
        name:'',
        partType:'',

      },
      rules:{
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
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

          axios.get("http://localhost:9090/getDoctorData/"+this.ruleForm.name).then(function (res){
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
    submitForm2(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;

          axios.get("http://localhost:9090/getDoctorDataByPart/"+this.ruleForm.partType).then(function (res){
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