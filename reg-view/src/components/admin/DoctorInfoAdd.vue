<template>
<div>
  <h1>
    添加医生
  </h1>
  <el-form style="" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
    <el-row>
      <el-col :span="8  ">
        <el-form-item label="医生姓名" prop="doctorName" style="width: 400px">
          <el-input v-model="ruleForm.doctorName"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">

        <el-form-item label="密码" prop="password" style="width: 400px">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="擅长" prop="good" style="width: 400px">
          <el-input v-model="ruleForm.good"></el-input>
        </el-form-item>
      </el-col>

    </el-row>

    <el-row>
      <el-col :span="8  ">
        <el-form-item label="科室" prop="partType" style="width: 400px">
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

        <el-form-item label="职称" prop="PTR" style="width: 400px">
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
        <el-form-item label="性别" prop="sex" style="width: 400px">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio label="男" value="男" name="type"></el-radio>
            <el-radio label="女" value="女" name="type"></el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="账户状态" prop="using" style="width: 400px">
          <el-radio-group v-model="ruleForm.using">
            <el-radio label="1" value="1" name="type"></el-radio>
            <el-radio label="0" value="0" name="type"></el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-button style="" type="primary" @click="addDoctor('ruleForm')">添加</el-button>
</div>
</template>

<script>

import axios from "axios";
export default {
  name: "DoctorInfoAdd.vue",
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
      rules: {
        doctorName:[
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, message: '长度大于 2 个字符', trigger: 'blur' }
        ],
        PTR:[
          { required: true, message: '请输入职称', trigger: 'blur' },
        ],
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 8, message: '长度大于 8 个字符', trigger: 'blur' }
        ],
        partType:[
          { required: true, message: '请输入科室', trigger: 'blur' },
        ],
        using:[
          { required: true, message: '请选择状态' }
        ],
        sex:[
          { required: true, message: '请选择性别' }
        ],
        birth:[
          { required: true, message: '请选择日期' }
        ],
        good:[
          { required: true, message: '请输入擅长', trigger: 'blur' },
        ],
      }
    }

  },
  methods:{
    addDoctor(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var _this = this;
          axios.get("http://localhost:9090/getPartTypeId/"+_this.ruleForm.partType).then(function (res) {
            _this.ruleForm.partTypeId = res.data
          })
          let obj = this.ruleForm
          let formdata =new FormData();
          formdata.append('DoctorAllData',obj)
          axios.post('http://localhost:9090/addDoctor', obj).then(function (res) {
            alert(res.data)
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });



    }
  },
  created() {
    var _this = this;
    axios.get("http://localhost:9090/sideTitle").then(function (res){
      _this.options = res.data;
    })
  }
}
</script>

<style scoped>

</style>