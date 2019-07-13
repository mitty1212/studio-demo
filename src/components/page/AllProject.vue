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
                label="项目文档"
                prop="project_file">
            </el-table-column>

            <el-table-column
                label="项目状态"
                prop="project_state">
            </el-table-column>

            <el-table-column label="承包" align="center" width="100px">
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
                projectname: '',
                projectfabu:'',
                projectexptime:'',
                projectstate:'',
                project_chengbao:this.$root.$personid
            },
            tableData:[],
            tableCurrentData:[],
            currentPage:1,
        }
    },
     methods: {
         handleEdit(index, row) {
            // console.log(row)
            if(row.project_state == "已发布"){
                this.$confirm('此操作将会承包项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let data = this.$qs.stringify({
                    project_fabu:row.project_fabu,
                    project_name:row.project_name,
                    project_chengbao:this.$root.$personid,
                    project_state:"已承包"
                })
                let vm = this
                this.$http({
                    method:"post",
                    url:"/api/project/updatecb",
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
                            message: '承包成功!'   
                        })           
                    }else{
                        vm.$message({
                            type: 'info',
                            message: '承包失败!'
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消承包'
                    });          
                });
            }else{
                this.$message({
                            type: 'info',
                            message: '此项目已承包，操作失败!'
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
      updatee(){
        console.log(123)
        // console.log(this.$root.$personid)
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            project_state:"已发布"
        })
        this.$http({
                    method:"post",
                    url:"/api/project/getproject",
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
        let data = this.$qs.stringify({
            // project_fabu:this.$root.$personid,
            project_state:"已发布",
            project_chengbao:this.$root.$personid
        })
        this.$http({
                    method:"post",
                    url:"/api/project/getproject",
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
