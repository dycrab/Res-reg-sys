<template>
  <div class="AdLogin" style="margin-top: 10%">
    <h1>
      管理员登录
    </h1>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="密码" prop="pass"  style="width: 500px;margin-left: 30%">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="margin-right: 7%" @click="submitForm('ruleForm')">登录</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>

const axios = require('axios');

export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };

    return {
      ruleForm: {
        pass: '',
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this;
          axios.get("http://localhost:9090/login_admin/"+this.ruleForm.pass).then(function (res) {
            if (res.data === "密码正确"){
              //将用户名放入sessionStorage中
              sessionStorage.setItem("admin", JSON.stringify(res.data));
              sessionStorage.setItem("adminToken", _this.ruleForm.pass)
              //将用户名放入vuex中
              _this.$store.dispatch("setAdmin", JSON.stringify(res.data));
              _this.$store.dispatch("setAdminToken", _this.ruleForm.pass);
              //打印login状态
              console.log(_this.$store.state.isLogin);
              _this.$router.push({path:'/adminPage'})
            }else{
              alert("密码错误")
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
  }
}
</script>

<style scoped>

</style>