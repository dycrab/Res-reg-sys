<template>
  <div class="about">
    <el-form :model="ruleForm" style="width: 50%; margin-left: 21.7%;" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input v-model="ruleForm.idCard"></el-input>
      </el-form-item>
<!--      <el-form-item label="出生日期" >-->
<!--        <el-form-item prop="birth">-->
<!--          <el-date-picker type="date"-->
<!--                          format="yyyy-MM-dd"-->
<!--                          value-format="yyyy-MM-dd"-->
<!--                          name="datePicker"-->
<!--                          placeholder="选择日期"-->
<!--                          v-model="ruleForm.birth"-->
<!--                          style="width: 100%;">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--      </el-form-item>-->

      <el-form-item label="预约日期" >
        <el-form-item prop="registerDate">
          <el-date-picker type="date"
                          @change="disableTime"
                          :picker-options="beforeDate"
                          format="yyyy-MM-dd"
                          clearable="true"
                          value-format="yyyy-MM-dd"
                          name="datePicker2"
                          placeholder="选择日期"
                          v-model="ruleForm.registerDate"
                          style="width: 100%;">
          </el-date-picker>
        </el-form-item>
      </el-form-item>
      <el-form-item label="预约时段">
        <el-select style="width: 100%;" v-model="ruleForm.registerTime" placeholder="请选择">
          <el-option

              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="性别" prop="sexType">
        <el-radio-group v-model="ruleForm.sexType">
          <el-radio label="男" name="type"></el-radio>
          <el-radio label="女" name="type"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">挂号</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
    <div v-show="qrcodeShow">
      <p style="color: red; font-size: 15px;text-align: center">点击挂号后,请扫描二维码付款</p>
      <div style="margin-bottom: 10px;margin-left: 42%;position: absolute">
        <vue-qr name="qrcode" id="qrcode" ref="qrcode"></vue-qr>
      </div>
    </div>

  </div>
</template>



<script>
import QRCode from "qrcodejs2";
const axios = require('axios');
export default {
  components: {
    QRCode
  },

  data() {
    return {
      before: "",
      beforeDate: {
        disabledDate(time) {
          // 禁止选择以前的时间
          return time.getTime() < (Date.now()-86400000)  ;
        },
      },
      startDate:'',
      qrcodeShow:true,
      videoVisible:false,
      ruleForm: {
        name: '',
        idCard:'',
        birth:'',
        registerDate:'',
        sexType:'',
        registerTime:'',
      },
      options: [{
        value: '8:30:00',
        label: '8:30~9:00',
        disabled: true
      }, {
        value: '9:00:00',
        label: '9:00~9:30',
        disabled: true
      }, {
        value: '9:30:00',
        label: '9:30~10:00',
        disabled: true
      }, {
        value: '10:00:00',
        label: '10:00~10:30',
        disabled: true
      }, {
        value: '10:30:00',
        label: '10:30~11:00',
        disabled: true
      }, {
        value: '14:30:00',
        label: '14:30~15:00',
        disabled: true
      }, {
        value: '15:00:00',
        label: '15:00~15:30',
        disabled: true
      }, {
        value: '15:30:00',
        label: '15:30~16:00',
        disabled: true
      }, {
        value: '16:00:00',
        label: '16:00~16:30',
        disabled: true
      }, {
        value: '16:30:00',
        label: '16:30~17:00',
        disabled: true
      }],
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { min: 18, max: 18, message: '长度为18个字符', trigger: 'blur' }
        ],
        birth: [
          { type: 'string', required: true, message: '请选择日期' }
        ],
        registerDate: [
          { type: 'string', required: true, message: '请选择日期' }
        ],
        sexType:[
          { required: true, message: '请选择性别' }
        ]
      }
    };
  },
  methods: {
    disableTime(){

      var date2 = new Date();

      for (var i= 0;i<10;i++){
        var part = this.options[i]
        var date1 = this.ruleForm.registerDate + ' ' + part.value;
        var date3 = new Date(date1).getTime() - date2.getTime();
        var doctorId = "46";
        if (date3 > 0 ){
          var hour1 = date3/1000/3600;
          if (hour1 >= 1){
            part.disabled = false;
            axios.get("http://localhost:9090/haveRegisterDate/"+doctorId+"/"+date1).then(function (resp) {
              if (resp.data === "0"){
                part.disabled = false
              }else{
                axios.get("http://localhost:9090/countRegisterDate/"+doctorId+"/"+date1).then(function (resp1) {
                  if (resp1.data>=3){

                    part.disabled = true
                  }else{
                    part.disabled = false
                  }
                })
              }
            })
          }else{
            part.disabled = true
          }
        }else{
          part.disabled = true
        }
      }
    },
    getInfo(idCard) {
      let sex = null;
      let birth = null;
      let myDate = new Date();
      let month = myDate.getMonth() + 1;
      let day = myDate.getDate();
      let age = 0;

      if(idCard.length===18){
        age = myDate.getFullYear() - idCard.substring(6, 10) - 1;
        sex = idCard.substring(16,17);
        birth = idCard.substring(6,10)+"-"+idCard.substring(10,12)+"-"+idCard.substring(12,14);
        if (idCard.substring(10, 12) < month || idCard.substring(10, 12) === month && idCard.substring(12, 14) <= day) age++;

      }
      if(idCard.length===15){
        age = myDate.getFullYear() - idCard.substring(6, 8) - 1901;
        sex = idCard.substring(13,14);
        birth = "19"+idCard.substring(6,8)+"-"+idCard.substring(8,10)+"-"+idCard.substring(10,12);
        if (idCard.substring(8, 10) < month || idCard.substring(8, 10) === month && idCard.substring(10, 12) <= day) age++;
      }

      if(sex%2 === 0)
        sex = '0';  // 性别代码 1代表男，0代表女，暂时不涉及其他类型性别
      else
        sex = '1';
      this.ruleForm.birth = birth
    },

    qrcode()
    {
      new QRCode("qrcode", {
        width: 232, // 设置宽度
        height: 232, // 设置高度
        text: "http://192.168.195.144:8080/RegisterDisplay/"+this.ruleForm.name+'/'+this.ruleForm.idCard +'/'+this.$route.params.doctorName
            +'/'+this.ruleForm.registerDate+' '+this.ruleForm.registerTime

      });
    }
    ,

    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.getInfo(this.ruleForm.idCard)
          alert(this.ruleForm.birth)
          this.qrcode();
          const _this = this;
          _this.qrcodeShow = true
          axios.get("http://localhost:9090/register/"+this.ruleForm.name+'/'+this.ruleForm.idCard+'/'+this.ruleForm.birth+'/'+this.ruleForm.sexType
          +'/'+this.$route.params.doctorId+'/'+this.$route.params.doctorName + '/' +this.$route.params.partType+'/'+this.ruleForm.registerDate+' '+this.ruleForm.registerTime).then(function (res){
           if (res.data === "预约成功"){
             _this.qrcodeShow = true
           }
          })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
  },

}


</script>


<style>

/*.qrcode {*/
/*  display: inline-block;*/
/*  width: 132px;*/
/*  height: 132px;*/
/*  background-color: #fff;*/
/*  padding: 6px;*/
/*  box-sizing: border-box;*/
/*}*/
</style>
