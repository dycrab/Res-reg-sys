<template>
  <div style="margin-top: 10%">
    <h2>
      医生登录
    </h2>
    <div class="doctorLogin" >
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="医生编号" prop="doctorId" style="width: 500px; margin-left: 30%">
          <el-input type="text" v-model="ruleForm.doctorId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="width: 500px; margin-left: 30%">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="margin-right: 7%" @click="submitForm('ruleForm')">登录</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>

</template>

<script>

const axios = require('axios');

export default {
  data() {
    return {
      ruleForm: {
        password: '',
        doctorId: '',
      },
      rules: {
        pass: [
          { require: true, trigger: 'blur' }
        ],
        doctorId: [
          {  require: true, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          // alert(this.ruleForm.doctorId)

          axios.get("http://localhost:9090/login_doctor/"+this.ruleForm.doctorId+"/"+this.ruleForm.password).then(function (res) {
            if (res.data === "密码正确"){
              //将用户名放入sessionStorage中
              sessionStorage.setItem("user", JSON.stringify(res.data));
              sessionStorage.setItem("userToken", _this.ruleForm.password)
              //将用户名放入vuex中
              _this.$store.dispatch("setUser", JSON.stringify(res.data));
              _this.$store.dispatch("setToken", _this.ruleForm.password);
              //打印login状态
              console.log(_this.$store.state.isLogin);
              _this.$router.push({path:'/CallPatient/'+_this.ruleForm.doctorId})
            }else{
              alert(res.data)
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  },
  //此方法写在method外面
  //to: Route: 即将要进入的目标路由对象
  //from: Route: 当前导航正要离开的路由
  //next()：必须执行
  beforeRouteEnter: (to, from, next) => {
    next(vm => {
      // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。
      vm.$store.dispatch("setUser", null);
    });
  },
}
</script>

<style scoped>

</style>