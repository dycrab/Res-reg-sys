import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import ElementUI from 'element-ui'

import store from './store/store'
import 'element-ui/lib/theme-chalk/index.css'
import scroll from 'vue-seamless-scroll'
import BaiduMap from 'vue-baidu-map'
// import './rem'
Vue.use(BaiduMap, { ak : 'xmzV94OpCNnSPWDBSBsOya7h6iPCzzYC&amp' } )
Vue.use(scroll)
axios.defaults.baseURL = 'http://localhost:9000/api/'
Vue.prototype.$http = axios
Vue.config.productionTip = false
router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
