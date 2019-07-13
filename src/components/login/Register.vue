<template>
    <div class="register">
        <el-card class="box-card">
            <h3 class="title">请注册</h3>
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
                <el-form-item label="用户账号" prop="userid">
                    <el-input v-model.number="ruleForm2.userid" placeholder="请输入用户账号"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="username">
                    <el-input v-model.number="ruleForm2.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm2.pass" placeholder="请输入密码" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="请再次输入密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm2.checkPass" placeholder="请再次输入密码" autocomplete="off"></el-input>
                </el-form-item>
                
                 <el-form-item>
                    <el-radio v-model="radio" style="width:20%"   size="medium" label="发包方" border>发包方</el-radio>
                    <el-radio v-model="radio" style="width:30%"   size="medium" label="承包方管理员" border>承包方管理员</el-radio>
                    <!-- <el-radio v-model="radio" style="width:20%"   size="medium" label="开发人员" border>开发人员</el-radio> -->
                  </el-form-item>
                   <el-form-item> 
                    <el-button style="width:20%" type="warning" @click="registerForm('ruleForm2')">注册</el-button>
                    <el-button style="width:20%" type="warning" @click="resetForm('ruleForm2')" plain>重置</el-button>
                    <el-button style="width:20%" type="warning" @click="returnForm()" plain>返回</el-button>
                    </el-form-item>
                

            </el-form>
        </el-card>
    </div>
</template>
<script>
export default {
    data(){
       var checkId = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户账号不能为空'));
        }
        setTimeout(() => {
              callback();
        }, 500);
      };

      var checkName = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户名不能为空'));
        }
        setTimeout(() => {
              callback();
        }, 500);
      };

      var validatePass = (rule, value, callback) => {//输入密码
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass');
          }
          callback();
        }
      };

      var validatePass2 = (rule, value, callback) => {//确认密码
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
        return{
            ruleForm2: {
                pass: '',
                checkPass: '',
                username: '',
                userid:''
            },
            rules2: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                username: [
                    { validator: checkName, trigger: 'blur' }
                ],
                userid: [
                  { validator: checkId, trigger: 'blur'}
                ]
            },
            radio:""
        }
    },
     methods: {
       returnForm(){
         this.$router.replace('/login')
       },
      registerForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              if(!this.radio){
                this.$message('请选择身份类别')
                return
              }

              let data = this.$qs.stringify({
                    user_name: this.ruleForm2.username,
                    user_password: this.ruleForm2.pass,
                    user_id:this.ruleForm2.userid,
                    user_type:this.radio.toString()
              })

              // this.$root.$personid = this.ruleForm2.username
              // let my_permission = this.radio
              // this.resetForm("ruleForm2")
              // this.$router.push({ name:"layout" ,params: { my_permission: my_permission } })
            
              this.$http({
                method:"post",
                url:"/api/user/create",
                data:data,
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              }).then(data => {
                if(data.data === 'success'){
                  this.$message("注册成功")
                  this.$root.$personid = this.ruleForm2.username
                  let my_permission = this.radio
                  this.resetForm("ruleForm2")
                  this.$router.push({ name:"login" ,params: { my_permission: my_permission } })
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
    }
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


