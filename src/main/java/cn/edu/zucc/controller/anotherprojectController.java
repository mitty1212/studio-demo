package cn.edu.zucc.controller;

import cn.edu.zucc.annotation.UserLoginToken;
import cn.edu.zucc.common.R;
import cn.edu.zucc.domain.entity.AnotherProject;
import cn.edu.zucc.service.AnotherProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/project")
public class anotherprojectController {
    @Autowired
    AnotherProjectService service;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String checkregister(
            //创建项目
            @RequestParam("project_name") String project_name,
            @RequestParam("project_fabu") String project_fabu,
            @RequestParam("project_detail") String project_detail,
            @RequestParam("project_expdate") String project_expdate,
            @RequestParam("project_state") String project_state,
            @RequestParam("project_file") String project_file
    ) {
        project_file="C:\\Users\\mitty\\Desktop\\信息系统设计与开发\\uploadtest\\"+project_name;
        AnotherProject project = new AnotherProject(project_name, project_fabu, project_detail, project_expdate, project_state,project_file);
        service.create(project);
        return "success";
        //   }
    }

    //@UserLoginToken
    @RequestMapping(value = "/getprojectbyfabu", method = RequestMethod.POST)
    public List<AnotherProject> getProjectbyfabu(@RequestParam("project_fabu") String project_fabu,@RequestParam("project_state") String project_state) {
        //通过项目发布方获取项目列表
        return service.getProjectByPfabu(project_fabu,project_state);
    }
    @RequestMapping(value = "/getproject", method = RequestMethod.POST)
    public List<AnotherProject> getAllproject(@RequestParam("project_state") String project_state) {
        //获取已发布项目列表
        return service.getBySearch(project_state);
    }
    @RequestMapping(value = "/getbysearchyfb", method = RequestMethod.POST)
    public List<AnotherProject> getBySearchYfb(@RequestParam("project_fabu") String project_fabu,@RequestParam("search") String search) {
        //通过项目发布方项目状态获取项目列表
        String yfb="已发布";
        String yys="已验收";
        String csh="初始化";
        String dys="待验收";
        if(search.equals("已发布的项目")){
        return service.getBySearchYfb(project_fabu,yfb);
        }else if(search.equals("已验收的项目")){
        return service.getBySearchYfb(project_fabu,yys);
        }else if(search.equals("待验收的项目")){
            return service.getBySearchYfb(project_fabu,dys);
        }else{
            return service.getBySearchcsh(project_fabu,csh);
        }
    }
    @RequestMapping(value = "/getbysearchcsh", method = RequestMethod.POST)
    public List<AnotherProject> getBySearchcsh(@RequestParam("project_fabu") String project_fabu,@RequestParam("project_state") String project_state) {
        //通过项目发布方项目状态获取项目列表
        return service.getBySearchcsh(project_fabu,project_state);
    }

    @RequestMapping(value = "/getbychengbao", method = RequestMethod.POST)
    public List<AnotherProject> getBychengbaoh(@RequestParam("project_chengbao") String project_chengbao) {
        //通过项目发布方项目状态获取项目列表
        return service.getProjectByChengbao(project_chengbao);
    }
    @RequestMapping(value = "/getbychengbaoycb", method = RequestMethod.POST)
    public List<AnotherProject> getBychengbaohycb(@RequestParam("project_chengbao") String project_chengbao,@RequestParam("project_state") String project_state) {
        //通过项目发布方项目状态获取项目列表
        return service.getBySearchYcb(project_chengbao,project_state);
    }
    @RequestMapping(value = "/getbysearchcb", method = RequestMethod.POST)
    public List<AnotherProject> getBySearchcb(@RequestParam("project_chengbao") String project_chengbao,@RequestParam("search") String search) {
        //通过项目发布方项目状态获取项目列表
        String ycb="已承包";
        String yys="已验收";
        String ywc="已完成";
        String dys="待验收";
        if(search.equals("已承包的项目")){
            return service.getBySearchYcb(project_chengbao,ycb);
        }else if(search.equals("已验收的项目")){
            return service.getBySearchYcb(project_chengbao,yys);
        }else if(search.equals("待验收的项目")){
            return service.getBySearchYcb(project_chengbao,dys);
        }else if(search.equals("已完成的项目")){
            return service.getBySearchcsh(project_chengbao,ywc);
        }else{
            return service.getProjectByChengbao(project_chengbao);
        }
    }

    @RequestMapping(value = "/getbycbnname", method = RequestMethod.POST)
    public AnotherProject getBycbNname(@RequestParam("project_name") String project_name,@RequestParam("project_chengbao") String project_chengbao) {
        //通过项目发布方项目状态获取项目列表
        return service.getProjectbyName(project_name,project_chengbao);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String approve(
            @RequestParam("project_fabu") String project_fabu,
            @RequestParam("project_name") String project_name,
            @RequestParam("project_state") String project_state
    ){
        System.out.println(project_fabu);
        System.out.println(project_name);
        System.out.println(project_state);
        AnotherProject project = service.getProjectByfabuAndName(project_fabu,project_name);
        project.setProject_state(project_state);
        service.update(project);
        return "success";
    }


    @RequestMapping(value = "/updatecb",method = RequestMethod.POST)
    public String updatecb(
            @RequestParam("project_fabu") String project_fabu,
            @RequestParam("project_chengbao") String project_chengbao,
            @RequestParam("project_name") String project_name,
            @RequestParam("project_state") String project_state
    ){
        System.out.println(project_fabu);
        System.out.println(project_name);
        System.out.println(project_state);
        Date time=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        String Pstartime = formatter.format(time);
        AnotherProject project = service.getProjectByfabuAndName(project_fabu,project_name);
        project.setProject_chengbao(project_chengbao);
        project.setProject_state(project_state);
        project.setProject_startime(Pstartime);
        service.update(project);
        return "success";
    }
}
