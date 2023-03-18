<template>
  <div>
    <el-form :model="ruleForm" style="width: 50%; margin-left: 21.7%;"  ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <!--      </el-form-item>-->

      <el-form-item label="预约日期" >
        <el-form-item prop="registerDate">
          <el-date-picker type="date"
                          @change="disableTime"
                          :picker-options="beforeDate"
                          format="yyyy-MM-dd"
                          value-format="yyyy-MM-dd"
                          name="datePicker2"
                          placeholder="选择日期"
                          v-model="ruleForm.registerDate"
                          style="width: 100%;">
          </el-date-picker>
        </el-form-item>
      </el-form-item>
    </el-form>

    <el-select v-model="ruleForm.registerTime" placeholder="请选择">
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          :disabled="item.disabled">
      </el-option>
    </el-select>
<!--    <button @click="disableTime">获取可选时段</button>-->
  </div>

</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      beforeDate: {
        disabledDate(time) {
          // 禁止选择以前的时间
          return time.getTime() < (Date.now()-86400000)  ;
        },
      },
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
      value: ''
    }
  },

  methods:{
    getDate(){
      var date2 = new Date();

      for (var i= 0;i<10;i++) {
        var part = this.options[i]
        var date1 = this.ruleForm.registerDate + ' ' + part.value;
        var date3 = new Date(date1).getTime() - date2.getTime();
        if (date3 > 0) {
          var hour1 = date3 / 1000 / 3600;
          if (hour1 >= 1) {
            part.disabled = false;
          } else {
            part.disabled = true
          }
        } else {
          part.disabled = true
        }
      }


    },
    str2date(str){
      return new Date(str);
    },
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
    }
  },

  created() {

  }
}
</script>