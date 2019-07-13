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
                        <span slot="title">发包</span>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <i class="el-icon-success"></i>
                        <span slot="title">项目详情</span>
                    </el-menu-item>
                </el-menu>
                <keep-alive>
                     <component 
                        v-bind:is="currentTabComponent"
                        v-on:topersonal="topersonal"
                        v-on:toaddapply="toaddapply"
                        v-on:toapprove="toapprove"
                    ></component>
                </keep-alive>
            </el-main>
        </el-container>
    </div>
</template>
<script>

import Apply from './Apply.vue'
import Approve from './Approve.vue'
import PersonalApply from './PersonalApply.vue'

export default {
    name:"contractor",
    data(){
        return {
            currentTabComponent:Apply
        }
    },
    components:{
        Apply,Approve,PersonalApply
    },
    methods:{
        handleSelect(key, keyPath){ // 侧边栏菜单 选择动态组件
            if(key === '1'){
                this.currentTabComponent = Apply
            }else{
                this.currentTabComponent = Approve
            }
        },
        topersonal(){   // 子组件触发 选择个人的申请情况组件作为动态组件
            this.currentTabComponent = PersonalApply
        },
        toaddapply(){
            this.currentTabComponent = Apply
        },
        toapprove(){
            this.currentTabComponent = Approve
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
