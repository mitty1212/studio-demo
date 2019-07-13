<template>
    <el-card class="box-card">
        <h3 class="title">申请单</h3>
        <el-form 
            style="width:80%;margin:0 auto"
            label-position="left"
            status-icon 
            label-width="100px" 
        >
            <el-form-item label="申请人">
                <el-input disabled :value="applyInfo.applyname"></el-input>
            </el-form-item>

            <el-form-item label="请假时间">
                <el-input disabled :value="applyInfo.applytime + ' 至 ' + applyInfo.applyendtime"></el-input>
            </el-form-item>

            <el-form-item label="请假理由">
                <el-input disabled :value="applyInfo.applyreason"></el-input>
            </el-form-item>

             <el-form-item label="审批意见">
                <!-- <el-input v-model="approve" clearable="clearable"></el-input> -->
                <el-autocomplete
                    style="width:100%"
                    v-model="approve"
                    :fetch-suggestions="querySearch"
                    placeholder="请输入内容"
                    @select="handleSelect"
                    clearable="clearable"
                ></el-autocomplete>
            </el-form-item>

            <el-form-item>
                <el-button type="warning" @click="agree()">同意申请</el-button>
                <el-button type="warning" @click="disagree()">拒绝申请</el-button>
                <el-button @click="backtolist">返回列表</el-button>
            </el-form-item>

        </el-form>
    </el-card>
</template>
<script>
export default {
    data(){
        return {
            clearable:true,
            approve:"",
            applyInfo:{
                applyname:"",
                applytime:"",
                applyendtime:"",
                applyreason:""
            },
            restaurants: [
                { "value":"已通过审核，允许请假。" },
                { "value":"通过审核，祝假期愉快。" },
                { "value":"申请理由不充分，拒绝申请。" },
                { "value":"十分抱歉，您的申请未能通过。" },
            ]
        }
    },
    methods:{
        agree(){    // 同意请假申请
            this.$confirm('此操作将批准请假申请, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: '批准申请成功!'
                });
                let data = this.$qs.stringify({
                    applyid:this.$route.params.applyID,
                    status:"同意",
                    approve:this.approve
                })
                let vm = this
                this.$http({
                    method:"post",
                    url:"/api/mid/approve",
                    data:data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(data => {
                    // 申请结果的回调函数
                    console.log(data)
                    if(data.data.data === 'success'){
                        vm.$message({
                            type: 'success',
                            message: '申请成功!'
                        })
                       
                    }else{
                        vm.$message({
                            type: 'info',
                            message: '申请失败!'
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })

                this.$router.push('/layout?isflash=true')
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消批准'
                });          
            });
        },
        disagree(){ // 拒绝请假申请
            this.$confirm('此操作将拒绝请假申请, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: '拒绝申请!'
                });
                // --            
                let data = this.$qs.stringify({
                    applyid:this.$route.params.applyID,
                    status:"不同意",
                    approve:this.approve
                })
                let vm = this
                this.$http({
                    method:"post",
                    url:"/api/mid/approve",
                    data:data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(data => {
                    // 申请结果的回调函数
                    console.log(data)
                    if(data.data.data === 'success'){
                        vm.$message({
                            type: 'success',
                            message: '申请成功!'
                        })
                       
                    }else{
                        vm.$message({
                            type: 'info',
                            message: '申请失败!'
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })
                // --
                this.$router.push('/layout?isflash=true')
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消动作'
                });          
            });
        },
        backtolist(){ // 返回列表
            this.$destroy()
            this.$router.push('/layout')
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
        handleSelect(item){

        }
    },
    created(){
        // console.log('created')
        // console.log(this.$route.params)
        if(!this.$route.params){
            this.$message('还未选择具体的申请')
            this.$router.push('/layout')
            return
        }
        let data = this.$qs.stringify({
                    applyid: this.$route.params.applyID
              })

        this.$http({
            method:"post",
            url:"/api/mid/getapplydetail",
            data:data,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).then(data => {
            // console.log(data.data)
              /**
                 *  apply_days: "3"
                    apply_endtime: "2019-05-04"
                    apply_id: "1001"
                    apply_name: "张三"
                    apply_personid: "31601001"
                    apply_reason: "家中有事"
                    apply_status: "同意"
                    apply_time: "2019-05-01"
                    approve: "同意审批"
                 */
            this.applyInfo = data.data
        }).catch(err => {
            console.log(err)
        })

    }
}
</script>
