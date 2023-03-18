<template>
  <div >

    <el-col :sm="12" :lg="6" style="">
      <el-result icon="success" title="付款成功提示" subTitle="预约成功！">
      </el-result>
    </el-col>
    <p style="font-family:'黑体',serif; "> 预约信息</p>
    <el-descriptions style="font-size: 12px;width: 100%;margin-top:10px" class="margin-top" title="" :column="1" :size="size" border>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          预约人
        </template>
        {{ patientName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-s-custom"></i>
          身份证号
        </template>
        {{ idCard }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-chat-line-round"></i>
          预约医生
        </template>
        {{ doctorName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-date"></i>
          预约时间
        </template>
        {{registerDate}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building"></i>
          医院地址
        </template>
        XX省XX市XX区XX大道120号-XX市人民医院
      </el-descriptions-item>
    </el-descriptions>
  </div>


</template>






<script>

export default {
  data(){
    return{
      patientName:'',
      idCard:'',
      doctorName:'',
      registerDate:'',

    }
  },


  created() {
    this.patientName=this.$route.params.name
    this.idCard=this.$route.params.idCard
    this.doctorName=this.$route.params.doctorName
    this.registerDate =this.$route.params.registerDate

  }
}


</script>