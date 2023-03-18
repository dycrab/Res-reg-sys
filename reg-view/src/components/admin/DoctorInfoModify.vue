<template>
  <div>
    <h1>
      修改数据
    </h1>
    <p style="color: red">医生编号为必须输入的数值</p>
    <el-form style="" label-width="100px" :rules="rules" ref="ruleForm" :model="ruleForm">
      <el-row>
        <el-col :span="8">
          <el-form-item label="" prop="idCard" style="width: 400px">
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="医生编号" prop="doctorId" style="width: 400px">
            <el-input v-model="ruleForm.doctorId"></el-input>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8  ">
          <el-form-item label="医生姓名" prop="idCard" style="width: 400px">
            <el-input v-model="ruleForm.doctorName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">

          <el-form-item label="密码" prop="idCard" style="width: 400px">
            <el-input v-model="ruleForm.password"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="擅长" prop="idCard" style="width: 400px">
            <el-input v-model="ruleForm.good"></el-input>
          </el-form-item>
        </el-col>

      </el-row>

      <el-row>
        <el-col :span="8  ">
          <el-form-item label="科室" prop="idCard" style="width: 400px">
            <el-select v-model="ruleForm.partType" placeholder="请选择" style="width: 100%">
              <el-option-group
                  v-for="group in options"
                  :key="group.partsName"
                  :label="group.partsName">
                <el-option
                    v-for="item in group.part"
                    :key="item.partType"
                    :label="item.partType"
                    :value="item.partType">
                </el-option>
              </el-option-group>
            </el-select>
            <!--          <el-input v-model="ruleForm.partType"></el-input>-->
          </el-form-item>
        </el-col>
        <el-col :span="8">

          <el-form-item label="职称" prop="idCard" style="width: 400px">
            <el-select v-model="ruleForm.PTR" placeholder="请选择" style="width: 100%">
              <el-option
                  v-for="item in PTRs"
                  :key="item.ptr"
                  :label="item.ptr"
                  :value="item.ptr">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">

          <el-form-item label="出生日期" prop="birth" style="width: 400px;">
            <el-date-picker type="date"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            name="datePicker"
                            placeholder="选择日期"
                            v-model="ruleForm.birth"
                            style="width: 100%">
            </el-date-picker>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="性别" prop="idCard" style="width: 400px">
            <el-radio-group v-model="ruleForm.sex">
              <el-radio label="男" value="男" name="type"></el-radio>
              <el-radio label="女" value="女" name="type"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="账户状态" prop="idCard" style="width: 400px">
            <el-radio-group v-model="ruleForm.using">
              <el-radio label="1" value="1" name="type"></el-radio>
              <el-radio label="0" value="0" name="type"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="" prop="idCard" style="width: 400px">
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="" prop="" style="width: 400px">
            <el-button style="" type="primary" @click="modifyDoctor('ruleForm')"> 修改</el-button>
          </el-form-item>
        </el-col>

      </el-row>






    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DoctorInfoModify.vue",
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
        doctorId:'',
        doctorName:'',
        PTR:'',
        password:'',
        partType:'',
        using:'',
        sex:'',
        birth:'',
        partTypeId:'',
        good:''
      },
      rules:{
        doctorId: [
          { required: true, message: '请输入编号', trigger: 'blur' },
        ]
      }
    }

  },
  methods:{
    modifyDoctor(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var _this = this;
          let obj = this.ruleForm
          let formdata =new FormData();
          formdata.append('DoctorAllData',obj)
          axios.post('http://localhost:9090/modifyDoctor', obj).then(function (res) {
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