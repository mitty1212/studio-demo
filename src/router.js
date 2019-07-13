import Vue from 'vue'
import Router from 'vue-router'
import Login from './components/login/Login.vue'
import Register from'./components/login/Register.vue'
import ApproveDetail from './components/page/ApproveDetail.vue'
import Contractor from './components/page/Contractor.vue'
import Admin from './components/page/Admin.vue'
import Develop from './components/page/Develop.vue'
import Apply from './components/page/Apply.vue'
import Create from './components/login/CreateDeveloper.vue'
import DeveloperList from './components/page/DeveloperList.vue'
import CreateMission from './components/page/CreateMission.vue'
import ProjectNMission from './components/page/ProjectNMission.vue'
import ProjectAllMission from './components/page/ProjectAllMission.vue'
import MissionToDeveloper from './components/page/MissionToDeveloper.vue'
import ProjectDetail from './components/page/Projectdetail.vue'
import ProjectDetailCb from './components/page/ProjectDetailCb.vue'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/approveDetail',
      name: 'approveDetail',
      component: ApproveDetail
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      
      path: '/contractor',
      name: 'contractor',
      component: Contractor
      
    },
    {
      
      path: '/admin',
      name: 'admin',
      component: Admin
      
    },
    {
      
      path: '/develop',
      name: 'develop',
      component: Develop
      
    },
    {
      path: '/apply',
      name: 'apply',
      component: Apply
    },
    {
      path: '/create',
      name: 'create',
      component: Create
    },
    {
      path: '/developlist',
      name: 'developlist',
      component: DeveloperList
    },
    {
      path: '/createmission',
      name: 'createmission',
      component: CreateMission
    },
    {
      path: '/projectnmission',
      name: 'projectnmission',
      component: ProjectNMission
    },
    {
      path: '/projectallmission',
      name: 'projectallmission',
      component: ProjectAllMission
    },
    {
      path: '/MissionToDeveloper',
      name: 'MissionToDeveloper',
      component: MissionToDeveloper
    },
    {
      path: '/ProjectDetail',
      name: 'ProjectDetail',
      component: ProjectDetail
    },
    {
      path: '/ProjectDetailCb',
      name: 'ProjectDetailCb',
      component: ProjectDetailCb
    }
  ]
})
