<template>
    <div>
        <el-card class="box-card">
             <!-- <el-input 
             placeholder="请输入想要查询的申请编号或关键字" 
             v-model="search"
             style="margin-bottom:20px"
             prefix-icon="el-icon-tickets"> 
                <el-button 
                slot="append" 
                icon="el-icon-search" 
                @click="upload">用户查询</el-button>
            </el-input> -->
            
            <el-table :data="tableCurrentData" style="width: 100%" border stripe>
            <el-table-column
                label="编号"
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

            <el-table-column label="创建任务" align="center" width="100px">
                <!-- <template slot-scope="scope"> slot-scope已经被v-slot取代 -->
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        type="warning" plain
                        @click="createmission(myscope.$index, myscope.row)"
                    >
                        创建
                    </el-button>
                </template>
            </el-table-column>
            
            

            <el-table-column label="查看任务" align="center" width="100px">
                <!-- <template slot-scope="scope"> slot-scope已经被v-slot取代 -->
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        type="warning" plain
                        @click="allmission(myscope.$index, myscope.row)"
                    >
                        查看
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
         <br>
                <el-row>
                    <el-col :span="4">&nbsp;</el-col>
                    <el-col :span="17">
                        <el-pagination  v-on:current-change="currentPageChange"
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
export default {
    data(){
        return {
            tableData:[],
            tableCurrentData:[],
            currentPage:1,
            search:"",
            isflash:false
        }
    },
     methods: {
      createmission(index,row){
        //   this.$emit('tocreatemission')
        //   this.$destroy()
        // console.log((row.project_name))
        console.log(index,row)
        
        this.$router.push({
            name:"createmission",
            params:{
                project_name:row.project_name,
                project_fabu:row.project_fabu,
            }
        })
      },
      allmission(index,row){
          this.$router.push({
            name:"projectallmission",
            params:{
                project_name:row.project_name,
                project_fabu:row.project_fabu,
                project_chengbao:this.$root.$personid
            }
        })

      },
      handleEdit(index, row) {
            // console.log(row)
            if(row.project_state == '已完成'){
                this.$confirm('此操作将会验收项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let data = this.$qs.stringify({
                    project_fabu:row.project_fabu,
                    project_name:row.project_name,
                    project_state:"待验收"
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
                        this.upload()
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
                        message: '已取消验收'
                    });          
                });
            }else if(row.project_state == '已验收'){
                this.$message.error('此项目已被发包方验收，操作失败!')
                this.reload()
            }else if(row.project_state == '待验收'){
                this.$message.error('此项目已为待验收，操作失败!')
                this.reload()
            }
            else{
                this.$message.error('此项目尚未完成，操作失败!')
                this.reload()
            }
            
      },
      uploadd(){
          console.log(123)
            let data = this.$qs.stringify({
                    project_chengbao:this.$root.$personid,
                    project_state:"已承包"
                })
            this.$http({
                method:"post",
                url:"/api/project/getbychengbaoycb",
                data:data,
                headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(data => {
                console.log(data)

                //请求后台得到数据tableData,并保存到本地
                // this.tableData = tableData
                this.tableData = data.data
                //在得到后台的时候默认加载前五条数据(或少于五条数据)
                this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
            }).catch(err => {
                console.log(err)
            })

      },
      currentPageChange(){//在改变当前页面的同时改变表格的数据
            console.log(this.currentPage)
          this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
      },
      querySearch(queryString, cb){
            var restaurants = this.restaurants;
            var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            return (restaurant) => {
            return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
            }
        },
        handleSelect(item) {
        }
    },
    created(){
        console.log(this.$root.$personid)
             let data = this.$qs.stringify({
                    project_chengbao:this.$root.$personid,
                    project_state:"已承包"
                })
            this.$http({
                method:"post",
                url:"/api/project/getbychengbaoycb",
                data:data,
                headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
                }
            }).then(data => {
                console.log(data)

                //请求后台得到数据tableData,并保存到本地
                // this.tableData = tableData
                this.tableData = data.data
                //在得到后台的时候默认加载前五条数据(或少于五条数据)
                this.tableCurrentData = this.tableData.slice((this.currentPage-1)*5,(this.currentPage-1)*5+5)
            }).catch(err => {
                console.log(err)
            })
 
            if(!this.$root.$personid){
            this.$router.push('/login')
        }
    },
    watch:{
        isflash:function(n,o){
            this.uploadd()
        }
    }
}

</script>
