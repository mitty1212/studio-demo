import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import qs from 'qs'

Vue.prototype.$http = axios
Vue.prototype.$qs = qs
Vue.config.productionTip = false
/**
 * this.$http({
 *  method:"post",
 *  url:"",
 *  data:{
 *    name:"111"
 *  }
 * })
 */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
