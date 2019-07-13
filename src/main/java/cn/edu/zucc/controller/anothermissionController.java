package cn.edu.zucc.controller;

import cn.edu.zucc.domain.entity.AnotherMission;
import cn.edu.zucc.domain.entity.AnotherProject;
import cn.edu.zucc.service.AnotherMissionService;
import cn.edu.zucc.service.AnotherProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/mission")
public class anothermissionController {

    @Autowired
    AnotherMissionService service;
    @Autowired
    AnotherProjectService projectService;
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createmission(
            //创建任务
            @RequestParam("project_name") String project_name,
            @RequestParam("project_fabu") String project_fabu,
            @RequestParam("project_chengbao") String project_chengbao,
            @RequestParam("mission_name") String mission_name,
            @RequestParam("mission_detail") String mission_detail,
            @RequestParam("mission_exptime") String mission_exptime,
            @RequestParam("mission_state") String mission_state
    ) {

        AnotherMission mission = new AnotherMission(project_name, project_fabu,project_chengbao,mission_name,mission_detail,mission_exptime,mission_state);
        service.create(mission);
        return "success";
        //   }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updatemission(
            @RequestParam("project_name") String project_name,
            @RequestParam("mission_name") String mission_name,
            @RequestParam("mission_person") String mission_person,
            @RequestParam("mission_startime") String mission_startime,
            @RequestParam("mission_state") String mission_state
    ){
        System.out.println(mission_name);
        AnotherMission mission = service.getMissionbyname(project_name,mission_name);
        mission.setMission_person(mission_person);
        mission.setMission_startime(mission_startime);
        mission.setMission_state(mission_state);
        service.update(mission);
        return "success";
    }

    @RequestMapping(value = "/getAllMissionbyproject", method = RequestMethod.POST)
    public List<AnotherMission> getAllMissionbyproject(@RequestParam("project_name") String project_name,@RequestParam("project_fabu") String project_fabu,@RequestParam("project_chengbao") String project_chengbao){
        //通过项目获取任务列表
        return service.getAllMissionbyproject(project_name,project_fabu,project_chengbao);
    }

    @RequestMapping(value = "/getDeveloperMission", method = RequestMethod.POST)
    public List<AnotherMission> getDeveloperMission(@RequestParam("mission_person") String mission_person){
        //通过开发人员账号获取开发人员任务列表
        return service.getDeveloperMission(mission_person);
    }

    @RequestMapping(value = "/setmissionend", method = RequestMethod.POST)
    public String setmissionend(
            @RequestParam("project_name") String project_name,
            @RequestParam("mission_name") String mission_name
    ){
        AnotherMission mission = service.getMissionbyname(project_name,mission_name);
        Date time=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String Mend = formatter.format(time);
        int ys=Integer.parseInt(mission.getMission_startime().substring(0,4));
        int ye=Integer.parseInt(Mend.substring(0,4));
        int ms=Integer.parseInt(mission.getMission_startime().substring(5,7));
        int me=Integer.parseInt(Mend.substring(5,7));
        int ds=Integer.parseInt(mission.getMission_startime().substring(8,10));
        int de=Integer.parseInt(Mend.substring(8,10));
        int rd=0;
        int day=0;
        if((ye-ys)!=0){
            me=me+12;
            day=(((me-ms)*30+de-ds)/7)*2;
            rd=(me-ms)*30+de-ds-day;
        }
        else{
            day=(((me-ms)*30+de-ds)/7)*2;
            rd=(me-ms)*30+de-ds-day;
        }
        rd=(rd*8)/24;
        String realtime=Integer.toString(rd);
        String state="已完成";
        mission.setMission_state(state);
        mission.setMission_endtime(Mend);
        mission.setMission_realtime(realtime);
        service.update(mission);

        int allmission=service.countMission(project_name);
        int donemission=service.countAllMission(project_name,"已完成");
        if(allmission==donemission){
            System.out.println(project_name);
            AnotherProject project=projectService.getProjectname(project_name);
            project.setProject_state("已完成");
            projectService.update(project);
            return "success";
        }
        return "success";

    }

    @RequestMapping(value = "/getprojectdetail", method = RequestMethod.POST)
    public List<AnotherProject> getprojectdetail(
            @RequestParam("project_name") String project_name
    ){
        AnotherProject project=projectService.getProjectname(project_name);
        int projectperson=service.countMissionperson(project_name);
        int projectrealtime=service.countMissionrealtime(project_name);
        project.setProject_persons(projectperson);
        project.setProject_realtime(projectrealtime);
        projectService.update(project);

        return projectService.getProjectbyname(project_name);
    }

}
