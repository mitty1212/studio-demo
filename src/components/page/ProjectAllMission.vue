<template>
    <div>
        <el-card class="box-card">
            
            <el-table :data="tableCurrentData" style="width: 100%" border stripe>
           
            <el-table-column
                label="序号"
                prop="id">
            </el-table-column>

            <el-table-column
                label="项目名称"
                prop="project_name">
            </el-table-column>

            <el-table-column
                label="任务名称"
                prop="mission_name">
            </el-table-column>

            <el-table-column
                label="开发内容"
                prop="mission_detail">
            </el-table-column>

            <el-table-column
                label="设计文档"
                prop="mission_file">
            </el-table-column>
            
            <el-table-column
                label="预计人天"
                prop="mission_exptime">
            </el-table-column>

            <el-table-column
                label="实际人天"
                prop="mission_realtime">
            </el-table-column>

            <el-table-column
                label="任务开始时间"
                prop="mission_startime">
            </el-table-column>

            <el-table-column
                label="任务结束时间"
                prop="mission_endtime">
            </el-table-column>

            <el-table-column
                label="任务开发人员"
                prop="mission_person">
            </el-table-column>

            <el-table-column
                label="任务状态"
                prop="mission_state">
            </el-table-column>

            <el-table-column label="分配任务" align="center" width="100px">
                <!-- <template slot-scope="scope"> slot-scope已经被v-slot取代 -->
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        type="warning" plain
                        @click="fenpei(myscope.$index, myscope.row)"
                    >
                        分配
                    </el-button>
                </template>
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
                project_name: '',
                mission_detail:'',
                mission_name:'',
                mission_file:'',
                mission_exptime:'',
                mission_realtime:'',
                mission_startime:'',
                mission_endtime:'',
                mission_person:'',
                mission_state:''
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
          this.$router.push({name:"admin"})      
          this.$destroy()
      },
      fenpei(index,row){
          if(row.mission_state == '已分配'){
               this.$message.error("该任务已分配，操作失败！")
               this.updatee()
          }else if(row.mission_state == '已完成'){
               this.$message.error("该任务已完成，操作失败！")
               this.updatee()
          }
          else{
            this.$router.push({
            name:"MissionToDeveloper",
            params:{
                project_name:row.project_name,
                project_fabu:this.$route.params.project_fabu,
                project_chengbao:this.$route.params.project_chengbao,
                mission_name:row.mission_name,
                mission_detail:row.mission_detail,
                mission_exptime:row.mission_exptime
            }
        })
        }
      },
      updatee(){
          console.log("123:"+this.$root.$personid)
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            project_name:this.$route.params.project_name,
            project_fabu:this.$route.params.project_fabu,
            project_chengbao:this.$route.params.project_chengbao,
        })
        this.$http({
                    method:"post",
                    url:"/api/mission/getAllMissionbyproject",
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
        console.log(123)
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            project_name:this.$route.params.project_name,
            project_fabu:this.$route.params.project_fabu,
            project_chengbao:this.$route.params.project_chengbao,
        })
        this.$http({
                    method:"post",
                    url:"/api/mission/getAllMissionbyproject",
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

        
          console.log(this.$root.$personid)
        if(!this.$root.$personid){
            this.$router.push('/login')
        }
        
    }


}

</script>
