<template>
    <div>
        <el-card class="box-card">
            <h3 class="title">我的开发人员</h3>
            <el-table :data="tableCurrentData" style="width: 100%" border stripe>
           
            <el-table-column
                label="序号"
                prop="id">
            </el-table-column>

            <el-table-column
                label="用户账号"
                prop="user_id">
            </el-table-column>

              <el-table-column
                label="用户名"
                prop="user_name"
            ></el-table-column>

            <el-table-column
                label="所属承包方"
                prop="user_office">
            </el-table-column>
        
            <!-- <el-table-column label="承包" align="center" width="100px">
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        @click="handleEdit(myscope.$index, myscope.row)"
                        type="warning"
                        plain
                    >
                        承包
                    </el-button>
                </template>
            </el-table-column> -->

        </el-table>
        <br>
            
        
        <el-row>   
            <el-col :span="4">&nbsp;</el-col>
            <el-col :span="16">
                <el-pagination v-on:current-change="currentPageChange"
                    background
                    layout="prev, pager, next"
                    :total="tableData.length"
                    :page-size="5"
                    :current-page.sync="currentPage"
                    >
                </el-pagination>
                
            </el-col>
        </el-row>
          <br>
          <el-button style="width:20% float:right" type="warning" @click="back">创建开发人员</el-button>
                    
            
        </el-card>
    </div>
</template>
<script>
import { callbackify } from 'util';
export default {
    inject:['reload'],
    data(){
        return {
            update:{
                projectname: '',
                projectfabu:'',
                project_chengbao:this.$root.$personid,
                user_office:this.$root.$personid,
            },
            tableData:[],
            tableCurrentData:[],
            currentPage:1,
        }
    },
     methods: {
        
      currentPageChange(){//在改变当前页面的同时改变表格的数据
            console.log(this.currentPage)
          this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
      },
      back(){   // 动态组件面板转回到创建开发人员面板
          this.$emit('topersonal')
          this.$destroy()
      },
      updatee(){
        console.log(123)
        // console.log(this.$root.$personid)
        let data = this.$qs.stringify({
             user_office:this.$root.$personid,
            
        })
        this.$http({
                    method:"post",
                    url:"/api/user/getdeveloper",
                    data:data,
                    headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                    }
        }).then(data => {
            // 申请结果的回调函数
            console.log(data)
            this.tableData = data.data
            //请求后台得到数据tableData,并保存到本地
            // this.tableData = tableData
            //在得到后台的时候默认加载前五条数据(或少于五条数据)
            this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
        }).catch(err => { console.log(err) })

        
    }
    },
    created(){
        // console.log(123)
        console.log(this.$root.$personid)
        if(!this.$root.$personid){
            this.$router.push('/login')
        }
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            user_office:this.$root.$personid
        })
        this.$http({
                    method:"post",
                    url:"/api/user/getdeveloper",
                    data:data,
                    headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                    }
        }).then(data => {
            // 申请结果的回调函数
            console.log(data)
            this.tableData = data.data
            //请求后台得到数据tableData,并保存到本地
            // this.tableData = tableData
            //在得到后台的时候默认加载前五条数据(或少于五条数据)
            this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
        }).catch(err => { console.log(err) })

        
    },
    watch:{
        isflash:function(n,o){
            this.updatee()
        }
    }
    
}

</script>
