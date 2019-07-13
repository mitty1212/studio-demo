<template>
    <div>
        <el-container>
            <!-- <el-aside width="200px"  class="side">
               
            </el-aside> -->
            <el-main  class="main">
                 <el-menu
                    style="width:80%;margin:0 auto"
                    mode="horizontal"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    default-active="1"
                    class="el-menu-vertical-demo"
                    @select="handleSelect"
                >
                    <el-menu-item index="1">
                        <i class="el-icon-thumb"></i>
                        <span slot="title">承包项目</span>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <i class="el-icon-success"></i>
                        <span slot="title">已承包项目详情</span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <i class="el-icon-user-solid"></i>
                        <span slot="title">开发人员</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-document-add"></i>
                        <span slot="title">任务创建与分配</span>
                    </el-menu-item>
                </el-menu>
                <keep-alive>
                     <component 
                        v-bind:is="currentTabComponent"
                        v-on:topersonal="topersonal"
                        v-on:toaddapply="toaddapply"
                        v-on:todeveloperlist="todeveloperlist"
                        v-on:toprojectlist="toprojectlist"
                        
                    ></component>
                </keep-alive>
            </el-main>
        </el-container>
    </div>
</template>
<script>

import AllProject from './AllProject.vue'
import MyProject from './MyProject.vue'
import CreateDeveloperVue from '../login/CreateDeveloper.vue';
import DeveloperList from './DeveloperList.vue'
import ProjectNMission from './ProjectNMission.vue'

export default {
    name:"admin",
    data(){
        return {
            currentTabComponent:AllProject
        }
    },
    components:{
        AllProject,CreateDeveloperVue,DeveloperList,ProjectNMission
    },
    methods:{
        handleSelect(key, keyPath){ // 侧边栏菜单 选择动态组件
            if(key === '1'){
                this.currentTabComponent = AllProject
            }else if(key ==='2'){
                this.currentTabComponent = MyProject
            }else if(key ==='3'){
                this.currentTabComponent = DeveloperList
            }else if(key === '4'){
                this.currentTabComponent = ProjectNMission
            }
        },
        topersonal(){   // 子组件触发 选择个人的申请情况组件作为动态组件
            this.currentTabComponent = CreateDeveloperVue
        },
        toaddapply(){
            this.currentTabComponent = AllProject
        },
        todeveloperlist(){
            this.currentTabComponent = DeveloperList
        },
        toprojectlist(){
            this.currentTabComponent = ProjectNMission
        }
    },
    created(){
        console.log(this.$route)
    }
   
}
</script>
<style scoped>
.side{
    /* background-color: aqua */
}
.main{
    /* background-color: aquamarine */
     background-color: #545c64;
}
</style>
