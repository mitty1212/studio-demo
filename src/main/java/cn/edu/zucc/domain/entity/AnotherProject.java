package cn.edu.zucc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "anotherproject")
public class AnotherProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String project_name;
    private String project_fabu;    //发布方
    private String project_chengbao;    //承包方
    private String project_detail;  //项目说明
    private String project_expdate;     //期望完成时间
    private String project_state;   //状态 初始化 已发布 已承包 待验收 已验收
    private String project_file;    //项目文档
    private String project_startime; //项目开始时间
    private String project_endtime;     //项目结束时间
    private int project_persons;  //开发人员数
    private int project_realtime; //实际人天数

    public int getProject_persons() {
        return project_persons;
    }

    public void setProject_persons(int project_persons) {
        this.project_persons = project_persons;
    }

    public int getProject_realtime() {
        return project_realtime;
    }

    public void setProject_realtime(int project_realtime) {
        this.project_realtime = project_realtime;
    }

    public String getProject_startime() {
        return project_startime;
    }

    public void setProject_startime(String project_startime) {
        this.project_startime = project_startime;
    }

    public String getProject_endtime() {
        return project_endtime;
    }

    public void setProject_endtime(String project_endtime) {
        this.project_endtime = project_endtime;
    }

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

    public String getProject_detail() {
        return project_detail;
    }

    public void setProject_detail(String project_detail) {
        this.project_detail = project_detail;
    }

    public String getProject_expdate() {
        return project_expdate;
    }

    public void setProject_expdate(String project_expdate) {
        this.project_expdate = project_expdate;
    }

    public String getProject_state() {
        return project_state;
    }

    public void setProject_state(String project_state) {
        this.project_state = project_state;
    }

    public String getProject_file() {
        return project_file;
    }

    public void setProject_file(String project_file) {
        this.project_file = project_file;
    }

    public AnotherProject(){

    }

    public AnotherProject(String project_name,String project_fabu,String project_detail,String project_expdate,String project_state,String project_file){
        this.project_name=project_name;
        this.project_fabu=project_fabu;
        this.project_detail=project_detail;
        this.project_expdate=project_expdate;
        this.project_state=project_state;
        this.project_file=project_file;
    }

    public AnotherProject(String project_file){
        this.project_file=project_file;
    }
}
