<template>
  <div style=" " >
    <!--    <header class="title"> 预约挂号系统</header>-->
    <el-container style=" border: 1px solid #eee; ">

      <el-aside width="250px"   style="height: 600px; overflow-y: scroll; background-color: rgb(238, 241, 246)">
        <el-menu v-for="(item,inde) in parts" :key="inde" :default-openeds="['1-1']">

          <el-submenu :index="inde.toString()" v-show="item.part">
            <template slot="title" ><i class="el-icon-first-aid-kit"></i>{{item.partsName}}</template>
            <el-menu-item-group :name=item.partsName  v-for="(iitem,ind) in item.part" :key="ind">
              <el-menu-item :index="inde+'-'+ind"  @click="menuClick(iitem.partType)" >{{iitem.partType}}</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container >
        <el-header style="font-size: 12px; text-align: right" >
          科室医生列表
        </el-header>

        <el-main>
          <el-table
              :data="pageTicket"
              style="width: 100%; height: 530px;overflow: scroll">
            <el-table-column
                fixed
                prop="doctorId"
                label="编号"
                width="120">
            </el-table-column>
            <el-table-column
                prop="doctorName"
                label="姓名"
                width="120">
            </el-table-column>
            <el-table-column
                prop="partType"
                label="科室"
                width="120">
            </el-table-column>
            <el-table-column
                prop="sex"
                label="性别"
                width="120">
            </el-table-column>
            <el-table-column
                prop="ptr"
                label="职称"
                width="120">
            </el-table-column>
            <el-table-column
                prop="good"
                label="擅长"
                width="500">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                width="100">
              <template slot-scope="scope">
                <el-button type="text"  size="small">
                  <router-link :to="{path:'/about/' + scope.row.doctorId+'/'
                  + scope.row.doctorName+'/' +scope.row.partType }">
                    挂号
                  </router-link>
                </el-button>
              </template>
            </el-table-column>

          </el-table>

        </el-main>
      </el-container>
    </el-container>

  </div>

</template>

<script>

// @click="register(scope.row)"

const axios = require('axios');

export default {
  data() {
    return {
      partType:"儿科",
      parts:[],
      pageTicket:[],
      sideTitle :[],

    }
  },

  created() {
    var _this = this;
    axios.get("http://localhost:9090/sideTitle").then(function (res){
      _this.parts = res.data;
    })
    axios.get("http://localhost:9090/doctorPage/"+this.partType+"/1/10").then(function (res){
      _this.pageTicket = res.data

    })

  },
  methods: {

    menuClick(partType){
      this.partType = partType
      var _this = this;
      axios.get("http://localhost:9090/doctorPage/"+this.partType+"/1/10").then(function (res){
        _this.pageTicket = res.data

      })
    },

  },

};
</script>

<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 55px;
}

.el-aside {
  color: #333;
}

</style>
