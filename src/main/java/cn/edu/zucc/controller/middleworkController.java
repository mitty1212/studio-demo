package cn.edu.zucc.controller;


import cn.edu.zucc.domain.entity.ApplyItem;
import cn.edu.zucc.service.MidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/mid")
public class middleworkController {
    @Autowired
    MidService midService;
//    @Role("admin")
    // 登陆检测
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String checkLogin(
            @RequestParam("username") String username,
            @RequestParam("pass") String pass,
            @RequestParam("type") String type
    ){
        System.out.println(username);
        System.out.println(pass);
        System.out.println(type);
        return "{\"data\":\"success\"}";
    }
    // 申请假期
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public String apply(
            @RequestParam("username") String username,
            @RequestParam("reason") String reason,
            @RequestParam("st") String st,
            @RequestParam("et") String et
    ){
        System.out.println(username);
        System.out.println(reason);
        System.out.println(st); // 2019/5/1 上午12:00:00
        System.out.println(et);
        st = st.split(" ")[0];
        et = et.split(" ")[0];
        int days =  Integer.parseInt(et.split("/")[0])*365 +
                    Integer.parseInt(et.split("/")[1])*30 +
                    Integer.parseInt(et.split("/")[2]) -
                    Integer.parseInt(st.split("/")[0])*365 -
                    Integer.parseInt(st.split("/")[1])*30 -
                    Integer.parseInt(st.split("/")[2]);
        String d = days + "";
        System.out.println(d);
        ApplyItem newItem = new ApplyItem(

                username,
                username,
                st, // apply_time
                et, // apply_endtime
                d,  // apply_days
                "未审批",
                reason, // apply_reason
                "未审批"
        );
        midService.create(newItem);
        return "{\"data\":\"success\"}";
    }
    // 获取申请假期的列表
    @RequestMapping(value = "/getapplylist",method = RequestMethod.POST)
    public List<ApplyItem> getApplyList(
            @RequestParam("search") String search
    ){
        String weisp = "未审批";
       // String yisp = "同意";
        if(search.equals("所有的申请")){
            return midService.getAllApplyItem();
        }
        else if(search.equals("已审批的申请")){
            return midService.getBySearch(weisp);
        }
        else{
            return midService.getBySearchWsp(weisp);
        }

//        return midService.getAllApplyItem();

    }
    // 获取申请详情
    @RequestMapping(value = "/getapplydetail",method = RequestMethod.POST)
    public ApplyItem getApplyDetail(
            @RequestParam("applyid") String applyid
    ){
        return midService.getApplyByApplyID(applyid);
//        System.out.println(applyid);
//        return new ApplyItem(
////                "1001",
//                "张三",
//                "31601001",
//                "2019-05-01",
//                "2019-05-04",
//                "3",
//                "同意",
//                "家中有事",
//                "同意审批"
//        );
    }
    // 获取个人的申请情况的列表
    @RequestMapping(value = "/getpersonalapplylist",method = RequestMethod.POST)
    public List<ApplyItem> getPersonalApplyList(
            @RequestParam("personid") String personid
    ){
        System.out.println(personid);
//        ArrayList<ApplyItem> searchList = new ArrayList<>();
        return midService.getApplyByApplyPersonID(personid);
//        searchList.add(new ApplyItem(
//                "1001",
//                "张三",
//                "31601001",
//                "2019-05-01",
//                "2019-05-04",
//                "3",
//                "同意",
//                "家中有事",
//                "同意审批"
//        ));
//        searchList.add(new ApplyItem(
//                "1002",
//                "李四",
//                "31601002",
//                "2019-04-12",
//                "2019-04-16",
//                "4",
//                "未审批",
//                "家中有事",
//                "未审批"
//        ));
//        return searchList;
    }
    // 管理员提交审批结果
    @RequestMapping(value = "/approve",method = RequestMethod.POST)
    public String approve(
            @RequestParam("applyid") String applyid,
            @RequestParam("status") String status,
                @RequestParam("approve") String approve
    ){
        System.out.println(applyid);
        System.out.println(status);
        System.out.println(approve);
        ApplyItem applyItem = midService.getApplyByApplyID(applyid);
        applyItem.setApplystatus(status);
        applyItem.setApprove(approve);
        midService.update(applyItem);
        return "{\"data\":\"success\"}";
    }
}
