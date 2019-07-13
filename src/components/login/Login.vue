<template>
    <div class="login">
        <el-card class="box-card">
            <h3 class="title">请登陆</h3>
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
                <el-form-item label="账号" prop="userid">
                    <el-input v-model.number="ruleForm2.userid" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm2.pass" placeholder="请输入密码" autocomplete="off"></el-input>
                </el-form-item>

                
                 <el-form-item>
                    <el-radio v-model="radio" style="width:20%"   size="medium" label="发包方" border>发包方</el-radio>
                    <el-radio v-model="radio" style="width:30%"   size="medium" label="承包方管理员" border>承包方管理员</el-radio>
                    <el-radio v-model="radio" style="width:20%"   size="medium" label="开发人员" border>开发人员</el-radio>
                  </el-form-item>
                   <el-form-item> 
                    <el-button style="width:20%" type="warning" @click="submitForm('ruleForm2')">登陆</el-button>
                    <el-button style="width:20%" type="warning" @click="register()">注册</el-button>
                    <el-button style="width:20%" type="warning" @click="resetForm('ruleForm2')" plain>重置</el-button>
                    </el-form-item>
                

            </el-form>
        </el-card>
    </div>
</template>
<script>
export default {
    data(){
      var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('账号不能为空'));
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
                userid: ''
            },
            rules2: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                userid: [
                    { validator: checkAge, trigger: 'blur' }
                ]
            },
            radio:""
        }
    },
     methods: {
       register(){
         this.$router.replace('/register')
       },
      submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              if(!this.radio){
                this.$message('请选择身份类别')
                return
              }

              let data = this.$qs.stringify({
                    user_id: this.ruleForm2.userid,
                    user_password: this.ruleForm2.pass,
                    user_type:this.radio.toString()
              })

              // this.$root.$personid = this.ruleForm2.username
              // let my_permission = this.radio
              // this.resetForm("ruleForm2")
              // this.$router.push({ name:"layout" ,params: { my_permission: my_permission } })
            
              this.$http({
                method:"post",
                url:"/api/user/login",
                data:data,
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              }).then(data => {
                if(data.data === 'success'){
                  this.$root.$personid = this.ruleForm2.userid
                  let my_permission = this.radio
                  this.resetForm("ruleForm2")
                  if(my_permission == "发包方"){
                  this.$router.push({ name:"contractor" ,params: { my_permission: my_permission } })
                  }
                  else if(my_permission == "承包方管理员"){
                  this.$router.push({ name:"admin" ,params: { my_permission: my_permission} })
                  }
                  else{
                  this.$router.push({ name:"develop" ,params: { my_permission: my_permission } })
                  }

                }else if(data.data == '用户不存在！'){
                  this.$message('用户不存在！')
                }
                else if(data.data == '您的权限不正确！'){
                  this.$message('您的权限不正确！')
                }else if(data.data == '密码错误！'){
                  this.$message('密码错误！')
                }else
                {
                  this.$message('登陆失败')
                  this.$refs[formName].resetFields()
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


