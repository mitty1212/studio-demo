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
            <el-autocomplete
                style="width:100%;margin-bottom:20px"
                v-model="search"
                :fetch-suggestions="querySearch"
                placeholder="请输入内容"
                @select="handleSelect"
                clearable="clearable"
            >
                <el-button 
                    slot="append" 
                    icon="el-icon-search" 
                    @click="searchyfb"
                >
                    查询
                </el-button>
            </el-autocomplete>
            
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

            <el-table-column label="验收" align="center" width="100px">
                <!-- <template slot-scope="scope"> slot-scope已经被v-slot取代 -->
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        @click="handleEdit(myscope.$index, myscope.row)"
                    >
                        验收
                    </el-button>
                </template>
            </el-table-column>
            
            <el-table-column label="详细信息" align="center" width="100px">
                <!-- <template slot-scope="scope"> slot-scope已经被v-slot取代 -->
                <template v-slot="myscope">
                    <el-button size="medium"  
                        style="width:100%"
                        @click="projectdetail(myscope.$index, myscope.row)"
                    >
                        查看
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
         <br>
                <el-row>
                    <el-col :span="4">&nbsp;</el-col>
                    <el-col :span="20">
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
            restaurants: [
                { "value":"已发布的项目" },
                { "value":"已验收的项目" },
                { "value":"待验收的项目" },
                { "value":"所有项目" },
            ],
            isflash:false
        }
    },
     methods: {

      projectdetail(index, row){
    
          this.$router.push({
              name:"ProjectDetail",
              params:{
                project_name:row.project_name,
            }
              }) 
      },
      handleEdit(index, row) {
            // console.log(row)
            if(row.project_state == '待验收'){
                this.$confirm('此操作将会验收项目, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let data = this.$qs.stringify({
                    project_fabu:row.project_fabu,
                    project_name:row.project_name,
                    project_state:"已验收"
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
                            message: '验收成功!'   
                        })           
                    }else{
                        vm.$message({
                            type: 'info',
                            message: '验收失败!'
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
                this.$message.error('此项目已验收，操作失败!')
                this.reload()
            }else{
                this.$message.error('此项目还未到验收阶段，操作失败!')
                this.reload()
            }
            
      },
      upload(){
        
            
            let data = this.$qs.stringify({
                    project_fabu:this.$root.$personid,
                    project_state:"初始化"
                })

            this.$http({
                method:"post",
                url:"/api/project/getbysearchcsh",
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
      searchyfb(){
            
            if(!this.search){
                this.$message({
                    type: 'warning',
                    message:'请先输入查询！'})
                return
            }
            
            let data = this.$qs.stringify({
                    project_fabu:this.$root.$personid,
                    search:this.search
                })

            this.$http({
                method:"post",
                url:"/api/project/getbysearchyfb",
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
        if(this.search==""){
            this.upload()
        }
        else{
            this.searchyfb()
        }
    },
    watch:{
        isflash:function(n,o){
            this.searchyfb()
        }
    }
}

</script>
