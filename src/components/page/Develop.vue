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

            <el-table-column label="完成任务" align="center" width="100px">
                <!-- <template slot-scope="scope"> slot-scope已经被v-slot取代 -->
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        type="warning" plain
                        @click="handleEdit(myscope.$index, myscope.row)"
                    >
                        完成
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
            // this.updatee()
          this.$router.push({name:"admin"})      
      },
      handleEdit(index, row) {
            // console.log(row)
            if(row.mission_state == '已分配'){
                this.$confirm('此操作将会完成项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let data = this.$qs.stringify({
                    project_name:row.project_name,
                    mission_name:row.mission_name
                })
                let vm = this
                this.$http({
                    method:"post",
                    url:"/api/mission/setmissionend",
                    data:data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(data => {
                    // 申请结果的回调函数
                    console.log(data)
                    if(data.data === 'success'){
                        this.updatee()
                        vm.$message({
                            type: 'success',
                            message: '操作成功!'   
                        })           
                    }else{
                        vm.$message({
                            type: 'warning',
                            message: '操作失败!'
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消操作'
                    });          
                });
            }
            else{
                this.$message.error('此项目已完成，操作失败!')
                this.reload()
            }
            
      },
      updatee(){
        console.log(123)
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            mission_person:this.$root.$personid
        })
        this.$http({
                    method:"post",
                    url:"/api/mission/getDeveloperMission",
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
        this.updatee()
        console.log(123)
        //  console.log(this.$root.$personid)
        if(!this.$root.$personid){
            this.$router.push('/login')
        }
        
    },
    mounted(){
        this.updatee();
    }


}

</script>
