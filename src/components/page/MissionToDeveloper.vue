<template>
    <div class="register">
        <el-card class="box-card">
            <h3 class="title">分配任务</h3>
            <el-form 
                style="width:90%;margin:0 auto;"
                label-position="top"
                :model="ruleForm2" 
                status-icon 
                ref="ruleForm2" 
                label-width="90px" 
                class="demo-ruleForm"
                
            >
                <el-form-item label="任务名称" prop="mission_name">
                    <el-input v-model="this.$route.params.mission_name" disabled></el-input>
                </el-form-item>

                <el-form-item label="开发内容" prop="mission_detail">
                    <el-input v-model="this.$route.params.mission_detail" disabled></el-input>
                </el-form-item>

                <el-form-item label="预计人天" prop="mission_exptime">
                <el-input v-model="this.$route.params.mission_exptime" disabled></el-input>

            </el-form-item>

                <el-form-item label="任务开发人员" prop="mission_person">
                    <el-input v-model="ruleForm2.mission_person" placeholder="请输入开发人员ID（完整）"></el-input>
                </el-form-item>


                 <el-form-item label="任务开始时间" prop="mission_startime">
                 <el-date-picker
                 v-model="ruleForm2.mission_startime"
                 type="datetime"
                 placeholder="选择日期时间"
                  default-time="12:00:00"
                  value-format="yyyy-MM-dd hh:mm:ss">
                </el-date-picker>
                </el-form-item>

                <el-form-item label="任务状态" prop="mission_state">
                <el-input v-model="ruleForm2.mission_state"
                 placeholder="初始化"
                :disabled="true">
                ></el-input>
                 </el-form-item>

                   <el-form-item> 
                    <el-button style="width:20%" type="warning" @click="registerForm('ruleForm2')">分配</el-button>
                    <el-button style="width:20%" type="warning" @click="back" plain>返回</el-button>
                    </el-form-item>
                

            </el-form>
        </el-card>
    </div>
</template>
<script>
export default {
    data(){

        return{
            fileList: [{}],
            ruleForm2: {
                project_name:this.$route.params.project_name,
                project_fabu:this.$route.params.project_fabu,
                project_chengbao:this.$root.$personid,
                mission_person:"",
                mission_state:"",
                mission_startime:""
                
            },
            
        }
    },
     methods: {
       back(){
          this.$router.push({
              name:"projectallmission",
                params:{
                project_name:this.$route.params.project_name,
                project_fabu:this.$route.params.project_fabu,
                project_chengbao:this.$route.params.project_chengbao
            }})
        // this.$emit('todeveloperlist')
        // this.$destroy()
       },
      registerForm(formName) {
          console.log(111)
          this.$refs[formName].validate((valid) => {
            if (valid) {
                if(!this.ruleForm2.mission_person){
                    this.$message("开发人员不能为空！")
                    return
                }else if(!this.ruleForm2.mission_startime){
                    this.$message("请设置任务开始时间！")
                    return
                }

                
              let data = this.$qs.stringify({
                    project_name:this.$route.params.project_name,
                    mission_name:this.$route.params.mission_name,
                    mission_person:this.ruleForm2.mission_person,
                    mission_startime:this.ruleForm2.mission_startime,
                    mission_state:"已分配"
              })

              // this.$root.$personid = this.ruleForm2.username
              // let my_permission = this.radio
              // this.resetForm("ruleForm2")
              // this.$router.push({ name:"layout" ,params: { my_permission: my_permission } })
            
              this.$http({
                method:"post",
                url:"/api/mission/update",
                data:data,
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              }).then(data => {
                if(data.data === 'success'){
                  this.$router.push({path:"/admin",
            //      params:{
            //     project_name:this.$route.params.project_name,
            //     project_fabu:this.$route.params.project_fabu,
            //     project_chengbao:this.$route.params.project_chengbao
            // }
                 })
                  this.$message("分配成功")
                      
                }
              }).catch(err => {
                console.log(err)
              })

          }
        });
      },
    //   resetForm(formName) {
    //     this.$refs[formName].resetFields()
    //   }
    },
    created(){
      console.log(this.$route)
      
    },
   
}
</script>
<style scoped>
    .login{
        margin: 50px auto;
        width: 640px;
        background-color:#E6A23C;
        /* border:1px solid white; */
    }
    .top{
        position: relative;
        height: 40px;
        /* border: 1px solid white; */
    }
    .text {
        font-size: 14px;
    }
    .item {
        padding: 18px 0;
        fill:#ffd04b
    }
    .box-card {
        text-align: center;
        width: 640px;
        background-color:white;
    }
    .top .topBtn{
        position: absolute;
        top: 0;
        right: 0;
        padding: 5px;
        border-radius:5px;
        color:#fff;
        background-color: #000;
        cursor: pointer;
        border:1px solid #ffd04b;
    }
    .top .topBtn:hover{
        color:gray; 
    }
</style>


