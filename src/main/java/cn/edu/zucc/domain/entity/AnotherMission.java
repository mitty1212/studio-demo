package cn.edu.zucc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="anothermission")
public class AnotherMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String project_name;
    private String project_fabu;
    private String project_chengbao;
    private String mission_name;
    private String mission_person;  //任务负责人
    private String mission_detail;  //开发内容
    private String mission_exptime;     //预计人天
    private String mission_realtime;    //实际人天
    private String mission_startime;    //开始时间
    private String mission_endtime;     //结束时间
    private String mission_state;       //任务状态 初始化 已分配 已完成
    private String mission_file;        //设计文档

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_fabu() {
        return project_fabu;
    }

    public void setProject_fabu(String project_fabu) {
        this.project_fabu = project_fabu;
    }

    public String getProject_chengbao() {
        return project_chengbao;
    }

    public void setProject_chengbao(String project_chengbao) {
        this.project_chengbao = project_chengbao;
    }

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getMission_person() {
        return mission_person;
    }

    public void setMission_person(String mission_person) {
        this.mission_person = mission_person;
    }

    public String getMission_detail() {
        return mission_detail;
    }

    public void setMission_detail(String mission_detail) {
        this.mission_detail = mission_detail;
    }

    public String getMission_exptime() {
        return mission_exptime;
    }

    public void setMission_exptime(String mission_exptime) {
        this.mission_exptime = mission_exptime;
    }

    public String getMission_realtime() {
        return mission_realtime;
    }

    public void setMission_realtime(String mission_realtime) {
        this.mission_realtime = mission_realtime;
    }

    public String getMission_startime() {
        return mission_startime;
    }

    public void setMission_startime(String mission_startime) {
        this.mission_startime = mission_startime;
    }

    public String getMission_endtime() {
        return mission_endtime;
    }

    public void setMission_endtime(String mission_endtime) {
        this.mission_endtime = mission_endtime;
    }

    public String getMission_state() {
        return mission_state;
    }

    public void setMission_state(String mission_state) {
        this.mission_state = mission_state;
    }

    public String getMission_file() {
        return mission_file;
    }

    public void setMission_file(String mission_file) {
        this.mission_file = mission_file;
    }

    public AnotherMission(String project_name,String project_fabu,String project_chengbao,String mission_name,String mission_detail,String mission_exptime,String mission_state){
        this.project_name=project_name;
        this.project_fabu=project_fabu;
        this.project_chengbao=project_chengbao;
        this.mission_name=mission_name;
        this.mission_detail=mission_detail;
        this.mission_exptime=mission_exptime;
        this.mission_state=mission_state;
    }
    public AnotherMission(){

    }
}

