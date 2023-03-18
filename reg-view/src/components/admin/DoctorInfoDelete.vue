<template>
  <div>
    <el-form :model="ruleForm" style=";margin-top: 50px" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-row>
        <el-col :span="6">
          <el-form-item label="医生编号" prop="doctorId" style="width: 400px">
            <el-input v-model="ruleForm.doctorId"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item  style="width: 400px">
            <el-button type="primary" @click="deleteDoctor('ruleForm')">删除</el-button>
          </el-form-item>
        </el-col>

      </el-row>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DoctorInfoDelete.vue",
  data(){
    return{
      PTRs:[
        {
          ptr:'住院医师'
        },
        {
          ptr:'主治医师'
        },
        {
          ptr:'副主任医师'
        },{
          ptr:'主任医师'
        }
      ],
      options:[],
      ruleForm:{

      },

      rules:{
        doctorId: [
          { required: true, message: '请输入编号', trigger: 'blur' },
        ],

      },

    }

  },
  methods:{
    deleteDoctor(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var _this = this;

          axios.get('http://localhost:9090/deleteDoctor/'+this.ruleForm.doctorId).then(function (res) {
            alert(res.data)
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>