<template>
    <div>
        <el-card class="box-card">
            
            <el-table :data="tableCurrentData" style="width: 100%" border stripe>
           
            <el-table-column
                label="项目名称"
                prop="project_name">
            </el-table-column>

              <el-table-column
                label="项目发布方"
                prop="project_fabu"
            ></el-table-column>

            <el-table-column
                label="项目承包方"
                prop="project_chengbao">
            </el-table-column>
        
            <el-table-column
                label="项目开发人数"
                prop="project_persons">
            </el-table-column>

            <el-table-column
                label="项目实际人天数"
                prop="project_realtime">
            </el-table-column>

            <el-table-column
                label="项目开始时间"
                prop="project_startime">
            </el-table-column>

            <el-table-column
                label="项目结束时间"
                prop="project_endtime">
            </el-table-column>


            
        </el-table>
        <br>
        <el-row>
            <el-col :span="4">&nbsp;</el-col>
            <el-col :span="17">
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
            <el-button style="width:20%" type="warning" @click="back" plain>返回</el-button>
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
                project_fabu:''
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
      back(){   // 动态组件面板转回到发包申请面板
          this.$router.push({name:"contractor"}) 
          this.$destroy()
      },
      updatee(){
        console.log(123)
        // console.log(this.$root.$personid)
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            project_name:this.$route.params.project_name
        })
        this.$http({
                    method:"post",
                    url:"/api/mission/getprojectdetail",
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
            // this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
        }).catch(err => { console.log(err) })

        
    }
    },
    created(){
        // this.updatee()
        
         console.log(this.$root.$personid)
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            project_name:this.$route.params.project_name
        })
        this.$http({
                    method:"post",
                    url:"/api/mission/getprojectdetail",
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
}

</script>
