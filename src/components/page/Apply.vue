<template>
   <el-card class="box-card" >
        <h3 class="title">发包申请</h3>
        <el-form 
            style="width:80%;margin:0 auto"
            label-position="left"
            :model="ruleForm" 
            status-icon 
            ref="ruleForm" 
            label-width="100px" 
        >
            <el-form-item label="项目名称" prop="projectname">
                <el-input v-model="ruleForm.projectname"></el-input>
            </el-form-item>

            <el-form-item label="项目发布方" prop="projectfabu">
                <el-input v-model="ruleForm.projectfabu" disabled></el-input>
            </el-form-item>

            <el-form-item label="期望完成时间" prop="projectexpdate">
                <el-date-picker
                style="width:100%"
                    v-model="ruleForm.projectexpdate"
                    type="date"
                    placeholder="选择日期"
                    :picker-options="pickerOptions"
                    value-format="yyyy-MM-dd"
                >
                </el-date-picker>
            </el-form-item>

            <el-form-item label="项目说明" prop="reason">
                <el-input
                 type="textarea"
                 :rows="2"
                placeholder="请输入项目说明"
                maxlength="255"
                show-word-limit
                v-model="ruleForm.reason">
                </el-input>
                </el-form-item>


                 <el-form-item label="项目需求文档" prop="project_file">
                <el-upload
                class="upload-demo"
                 drag
                action="http://localhost:81/uploads/uploadfile"
                multiple
            >
             <i class="el-icon-upload"></i>
             <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
             <div class="el-upload__tip" slot="tip">只能上传一份文档，且文档名必须和项目同名！</div>
                </el-upload>
                 </el-form-item>

                <!-- <el-form-item label="项目需求文档" prop="projectfile">
                <el-upload
                 style="width:100%"
                 class="upload-demo"
                action="http://localhost:81/uploads/uploadfile"
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
                
                <el-form-item label="项目状态" prop="projectstate">
                <el-input v-model="ruleForm.projectstate"
                 placeholder="初始化"
                :disabled="true">
                ></el-input>
            </el-form-item>
                    
                <!-- <el-input v-model="ruleForm.reason" clearable="clearable"></el-input> -->
                    <!-- <el-autocomplete
                        style="width:100%"
                        v-model="ruleForm.reason"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入内容"
                        @select="handleSelect"
                        clearable="clearable"
                    ></el-autocomplete> -->
            <!-- </el-form-item> -->

            

           

            <el-form-item>
                <el-button type="warning" @click="personsl">我的申请</el-button>
                <el-button type="warning" @click="addNewApply">新增申请</el-button>
                <el-button @click="resetForm('ruleForm2')">重置</el-button>
            </el-form-item>

        </el-form>
    </el-card>
</template>
<script>
export default {
    data(){
        return {
            fileList:[{}],
            clearable:true,
            ruleForm: {
                projectname: '',
                projectfabu:this.$root.$personid,
                projectexptime:'',
                projectstate:'',
                reason:"",
                projectfile:"",
                projectexpdate: "",
            },
            input: '',
            
             pickerOptions: {
            disabledDate(time) {
            return time.getTime() < Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '明天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周后',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
            value: '',
            textarea: ''
        }
    },
    methods:{
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
        addNewApply(){   // 提交发包申请
            // 非空检测
            if(!this.ruleForm.projectname){
                this.$message('请先填写项目名称！')
                return
            }else if(!this.ruleForm.projectfabu){
                this.$message('请先填写项目发包方名称！')
                return
            }
            else if(this.ruleForm.projectexpdate == 0){
                this.$message('请先选择项目期望完成时间！')
                return
            }else if(!this.ruleForm.reason){
                this.$message('请先填写项目说明')
                return
            }
            // 提示
            let vm = this
            this.$confirm('此操作将申请发包, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {

                let data = this.$qs.stringify({
                    project_name:this.ruleForm.projectname,
                    project_fabu:this.ruleForm.projectfabu,
                    project_state:'初始化',
                    project_detail:this.ruleForm.reason,
                    project_expdate:this.ruleForm.projectexpdate,
                   // project_file:this.ruleForm.projectfile
                })
                // console.log(data)

                // 发起申请提交请求
                this.$http({
                    method:"post",
                    url:"/api/project/create",
                    data:data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).then(data => {
                    // 申请结果的回调函数
                    if(data.data === 'success'){
                        vm.$message({
                            type: 'success',
                            message: '申请成功!'
                        })
                        vm.resetForm()
                        vm.personsl()
                    }else{
                        vm.$message({
                            type: 'info',
                            message: '申请失败!'
                        })
                    }
                }).catch(err => {
                    console.log(err)
                })

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消申请'
                });          
            });
        },
        resetForm(){
            this.ruleForm = {
                projectfabu: this.$root.$personid,
                reason:"",
                value: [],
            }
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
            // console.log(item)
            // console.log(this.ruleForm.reason)
        },
        personsl(){ // 当前用户的申请情况
            this.$emit('topersonal')
            this.$destroy()
        }
    },
    created(){
        console.log(this.$root.$personid)
        if(!this.$root.$personid){
            this.$router.push('/login')
        }
    }
}
</script>
<style>
.box-card{
    width: 80%;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid;
   
}
</style>
