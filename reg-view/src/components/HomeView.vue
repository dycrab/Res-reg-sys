<template>
  <div>
    <router-view></router-view>
  </div>



</template>

<script>

// @click="register(scope.row)"

const axios = require('axios');

export default {
  created() {


  },
  methods: {


  },


  data() {
    return {
      partType:"儿科",
      parts:[],
      pageTicket:[],
      sideTitle :[],

    }
  },
  mounted() {

    // })

  },

};
</script>

<style>
.title{
  background: #2B6695;
  border-radius: 6px 6px 6px 6px;
  box-shadow: 0 0 0 1px #5F5A4B, 1px 1px 6px 1px rgba(10, 10, 0, 0.5);
  color: #FFFFFF;
  font-family:"微软雅黑", "宋体", "黑体", Arial,serif;
  font-size: 18px;
  font-weight: bold;
  height: 25px;
  line-height: 25px;
  margin: 5px 0 !important;
  padding: 8px 0 5px 5px;
  text-shadow: 2px 2px 3px #222222;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 55px;
}

.el-aside {
  color: #333;
}

</style>
