package cn.edu.zucc.controller;

import cn.edu.zucc.annotation.PassToken;
import cn.edu.zucc.annotation.UserLoginToken;
import cn.edu.zucc.common.HashKit;
import cn.edu.zucc.common.R;
import cn.edu.zucc.domain.entity.AnotherUser;
import cn.edu.zucc.service.AnotherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class anotheruserController {
    @Autowired
    AnotherUserService service;

    @UserLoginToken
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public R<List<AnotherUser>> getUserList() {
        //  /users/  get 用户获取用户列表
        return R.data(service.getAllUsers());
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String checkregister(
            @RequestParam("user_id") String user_id,
            @RequestParam("user_password") String user_password,
            @RequestParam("user_name") String user_name,
            @RequestParam("user_type") String user_type
    ){
            AnotherUser user=new AnotherUser(user_id,user_password,user_name,user_type);
            service.create(user);
            return "success";
    }

    @RequestMapping(value = "/createdeveloper", method = RequestMethod.POST)
    public String createdeveloper(
            @RequestParam("user_id") String user_id,
            @RequestParam("user_password") String user_password,
            @RequestParam("user_name") String user_name,
            @RequestParam("user_type") String user_type,
            @RequestParam("user_office") String user_office
    ){
        AnotherUser user=new AnotherUser(user_id,user_password,user_name,user_type,user_office);
        service.create(user);
        return "success";
    }
//    @RequestMapping(value="/create",method = RequestMethod.POST)
//     public R<String> postUser(@RequestBody AnotherUser user){
//        // /users/ post 用户创建User
//        service.create(user);
//        return R.success("success");
//     }
    @UserLoginToken
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public R<AnotherUser> getUserbyId(@PathVariable String id) {
        // /users/{id} users中id为id的user的信息
        return R.data(service.getUserbyId(id));
    }

//     @RequestMapping(value="/{id}",method = RequestMethod.PUT)
//    public R<String> putUser(@PathVariable Long id,@ModelAttribute User user){
//        // /users/{id} put 用于更新用户信息
//         service.update(id,user.getName(),user.getAge());
//        return R.success("success");
//    }

    //------    3-28 start
    @UserLoginToken
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public R<String> deleteUSer(@PathVariable String id) {
        // /users/{id} delete 用于删除user
        service.deleteById(id);
        return R.success("success");
    }

    //    @UserLoginToken
    //@PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam("user_id") String user_id, @RequestParam("user_password") String user_password,@RequestParam("user_type") String user_type) {
//        User user = service.getUser(name,password);
        AnotherUser user = service.getUserbyId(user_id);

        if (user == null) {
            return "用户不存在！";
        }
        if (!user.getUser_type().equals(user_type)) {
            System.out.println(user_type);
            return "您的权限不正确！";
        } else {
            if(!user.getUser_password().equals(user_password))
                return "密码错误！";
            else
                return "success";
        }
//        System.out.println(user.getName());
//        System.out.println(user.getPassword());
//        System.out.println(user.getSalt());
//        System.out.println(HashKit.md5(password+user.getSalt()));
       // return user.getUser_password().equals(password) ? R.Loginsuccess(user) : R.fail("密码错误");
        //return user == null?R.fail("用户不存在或密码错误"):R.Loginsuccess(user);
    }

    @PassToken
    @RequestMapping(value = "/getdeveloper", method = RequestMethod.POST)
    public List<AnotherUser> changePassword(@RequestParam("user_office") String user_office) {
        //获取开发人员列表
        return service.getDeveloper(user_office);
    }

    //------   3-28 end


    //----old2 code
    // --------old code
//    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long,User>());
//
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public List<User> getUserList(){
//        List<User> r = new ArrayList<User>(users.values());
//        return  r;
//    }
//
//
//    @UserLoginToken
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public R<String> postUser(@Valid @ModelAttribute AnotherUser user) {//@RequestBody
////        users.put(user.getId(),user);
////        if(StringUtils.isEmpty(user.getName())){
////            throw new NullPointerException("用户名不能为空");
////        }
//        user.setUser_password(user.getUser_password());
//        service.create(user);
//        return R.success("success");
//
//    }
}
