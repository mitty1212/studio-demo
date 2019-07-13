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
                label="项目发布方"
                prop="project_fabu"
            ></el-table-column>

            <el-table-column
                label="项目承包方"
                prop="project_chengbao">
            </el-table-column>
        
            <el-table-column
                label="项目说明"
                prop="project_detail">
            </el-table-column>

            <el-table-column
                label="期望完成时间"
                prop="project_expdate">
            </el-table-column>


            <el-table-column
                label="项目状态"
                prop="project_state">
            </el-table-column>

            <el-table-column
                label="项目文档"
                prop="project_file">
               
            <!-- <el-button style="float:right" @click="download(myscope.$index, myscope.row)">下载</el-button> -->
            </el-table-column>

            <el-table-column label="发布" align="center" width="100px">
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        @click="handleEdit(myscope.$index, myscope.row)"
                        type="warning"
                        plain
                    >
                        发布
                    </el-button>
                </template>
            </el-table-column>

        </el-table>
        <br>
        <el-row>
            <el-col :span="4">&nbsp;</el-col>
            <el-col :span="20">
                <el-pagination v-on:current-change="currentPageChange"
                    background
                    layout="prev, pager, next"
                    :total="tableData.length"
                    :page-size="5"
                    :current-page.sync="currentPage"
                    >
                </el-pagination>
                 <el-button style="float:right" @click="back">返回</el-button>
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
                projectname: '',
                projectfabu:'',
                projectexptime:'',
                projectstate:'',
            },
            tableData:[],
            tableCurrentData:[],
            currentPage:1,
        }
    },
     methods: {

         downloadtest(){
                this.$router.push('/uploads/MOOC申请')
         },
         download(index,row){
            let data = this.$qs.stringify({
            project_name:row.project_name,
        }) 
        this.$http({
                    method:"post",
                    url:"/api/uploads/downloadFileByName",
                    data:data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                    }).then(data => {
                        this.$message({
                            type: 'info',
                            message: '下载成功!'
                        })
                    })
        },
         handleEdit(index, row) {
            // console.log(row)
            if(row.project_state == '初始化'){
                this.$confirm('此操作将会发布项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let data = this.$qs.stringify({
                    project_fabu:row.project_fabu,
                    project_name:row.project_name,
                    project_state:"已发布"
                })
                let vm = this
                this.$http({
                    method:"post",
                    url:"/api/project/update",
                    data:data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(data => {
                    // 申请结果的回调函数
                    console.log(data)
                    if(data.data === 'success'){
                        this.back()
                        vm.$message({
                            type: 'success',
                            message: '发布成功!'   
                        })           
                    }else{
                        vm.$message({
                            type: 'info',
                            message: '发布失败!'
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消发布'
                    });          
                });
            }else{
                this.$message({
                            type: 'info',
                            message: '此项目已发布，操作失败!'
                        })
                this.reload()
            }
            
      },
      currentPageChange(){//在改变当前页面的同时改变表格的数据
            console.log(this.currentPage)
          this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
      },
      back(){   // 动态组件面板转回到发包申请面板
          this.$emit('toaddapply')
          this.$destroy()
      },
    },
    created(){
        console.log(123)
        // console.log(this.$root.$personid)
        let data = this.$qs.stringify({
            project_fabu:this.$root.$personid,
            project_state:"初始化"
        })
        this.$http({
                    method:"post",
                    url:"/api/project/getprojectbyfabu",
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
// var tableData =  [
//             {
//                 id:1001,
//                 applyTime:"2019-05-22",
//                 days:"3",
//                 state:'同意',
//                 approve:"已获知，批准申请！"
//             },
//             {
//                 id:1002,
//                 applyTime:"2019-05-01",
//                 days:"4",
//                 state:'拒绝',
//                 approve:"十分抱歉，您的申请未能通过！"
//             },
//             {
//                 id:1003,
//                 applyTime:"2019-05-12",
//                 days:"1",
//                 state:'未审批',
//                 approve:"未审批"
//             }
//         ]
</script>
