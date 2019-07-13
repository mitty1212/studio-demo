<template>
    <div class="register">
        <el-card class="box-card">
            <h3 class="title">创建任务</h3>
            <el-form 
                style="width:90%;margin:0 auto;"
                label-position="top"
                :model="ruleForm2" 
                status-icon 
                :rules="rules2" 
                ref="ruleForm2" 
                label-width="90px" 
                class="demo-ruleForm"
                
            >
                <el-form-item label="项目名称" prop="project_name">
                    <el-input v-model="this.$route.params.project_name" disabled></el-input>
                </el-form-item>

                <el-form-item label="项目发布方" prop="project_fabu">
                    <el-input v-model="this.$route.params.project_fabu" disabled></el-input>
                </el-form-item>

                <el-form-item label="项目承包方" prop="project_chengbao">
                <el-input v-model="this.$root.$personid" disabled></el-input>
            </el-form-item>

                <el-form-item label="任务名称" prop="mission_name">
                    <el-input v-model.number="ruleForm2.mission_name" placeholder="请输入任务名称"></el-input>
                </el-form-item>

                <el-form-item label="开发内容" prop="mission_detail">
                <el-input
                 type="textarea"
                 :rows="2"
                placeholder="请输入开发内容"
                maxlength="255"
                show-word-limit
                v-model="ruleForm2.mission_detail">
                </el-input>
                </el-form-item>



                <el-form-item label="设计文档" prop="mission_file">
                <el-upload
                class="upload-demo"
                 drag
            action="http://localhost:81/uploads/uploadfile"
            multiple>
             <i class="el-icon-upload"></i>
             <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
             <div class="el-upload__tip" slot="tip">只能上传一份文档，且文档名必须和项目同名！</div>
                </el-upload>
                 </el-form-item>

                <!-- <el-form-item label="设计文档" prop="mission_file">
                <el-upload
                 style="width:100%"
                 class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="fileList">
                <el-button size="small" type="warning" plain>点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传一个文件，且不超过3M</div>
                </el-upload>
                    </el-form-item> -->

                <el-form-item label="预计人天" prop="mission_exptime">
                    <el-input v-model="ruleForm2.mission_exptime" placeholder="请输入预计人天" autocomplete="off"></el-input>
                </el-form-item>
                
                <el-form-item label="任务状态" prop="mission_state">
                <el-input v-model="ruleForm2.mission_state"
                 placeholder="初始化"
                :disabled="true">
                ></el-input>
            </el-form-item>

                   <el-form-item> 
                    <el-button style="width:20%" type="warning" @click="registerForm('ruleForm2')">创建</el-button>
                    <el-button style="width:20%" type="warning" @click="resetForm('ruleForm2')" plain>重置</el-button>
                    <el-button style="width:20%" type="warning" @click="back" plain>返回</el-button>
                    </el-form-item>
                

            </el-form>
        </el-card>
    </div>
</template>
<script>
export default {
    data(){
       var checkmissionname = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('任务名称不能为空'));
        }
        setTimeout(() => {
              callback();
        }, 500);
      };

      var checkDetail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('开发内容不能为空'));
        }
        setTimeout(() => {
              callback();
        }, 500);
      };

      var checkexptime = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('预计人天不能为空'));
        }
        setTimeout(() => {
              callback();
        }, 500);
      };

        return{
            fileList: [{}],
            ruleForm2: {
                project_name:this.$route.params.project_name,
                project_fabu:this.$route.params.project_fabu,
                project_chengbao:this.$root.$personid,
                mission_name:"",
                mission_detail:"",
                mission_file: '',
                mission_exptime:'',
                mission_state:""
                
            },
            rules2: {
                mission_name: [
                    { validator: checkmissionname, trigger: 'blur' }
                ],
                mission_detail: [
                    { validator: checkDetail, trigger: 'blur' }
                ],
                mission_exptime: [
                    { validator: checkexptime, trigger: 'blur' }
                ]
            },
            textarea: ''
        }
    },
     methods: {
         handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
       back(){
          this.$router.push({name:"admin"})
        // this.$emit('todeveloperlist')
        // this.$destroy()
       },
      registerForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              

              let data = this.$qs.stringify({
                    project_name: this.$route.params.project_name,
                    project_fabu: this.$route.params.project_fabu,
                    project_chengbao:this.$root.$personid,
                    mission_name:this.ruleForm2.mission_name,
                    mission_detail:this.ruleForm2.mission_detail,
                    mission_exptime:this.ruleForm2.mission_exptime,
                    mission_state:"初始化"
              })

              // this.$root.$personid = this.ruleForm2.username
              // let my_permission = this.radio
              // this.resetForm("ruleForm2")
              // this.$router.push({ name:"layout" ,params: { my_permission: my_permission } })
            
              this.$http({
                method:"post",
                url:"/api/mission/create",
                data:data,
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              }).then(data => {
                if(data.data === 'success'){
                  this.$router.push({path:"/admin",
                  })
                  this.$message("创建成功")     
                }
              }).catch(err => {
                console.log(err)
              })

          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
        this.radio = ''
      }
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


