package cn.edu.zucc.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applyitem", schema = "test")
public class ApplyItem {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String applyid;        // 申请编号
    private String applyname;      // 申请人姓名
    private String applypersonid;  // 申请人编号
    private String applytime;      // 申请假期开始日期
    private String applyendtime;   // 申请假期截止日期
    private String applydays;      // 申请时长
    private String applystatus;    // 申请状态
    private String applyreason;    // 申请理由
    private String approve;         // 审批意见

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname;
    }

    public String getApplypersonid() {
        return applypersonid;
    }

    public void setApplypersonid(String applypersonid) {
        this.applypersonid = applypersonid;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getApplyendtime() {
        return applyendtime;
    }

    public void setApplyendtime(String applyendtime) {
        this.applyendtime = applyendtime;
    }

    public String getApplydays() {
        return applydays;
    }

    public void setApplydays(String applydays) {
        this.applydays = applydays;
    }

    public String getApplystatus() {
        return applystatus;
    }

    public void setApplystatus(String applystatus) {
        this.applystatus = applystatus;
    }

    public String getApplyreason() {
        return applyreason;
    }

    public void setApplyreason(String applyreason) {
        this.applyreason = applyreason;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public ApplyItem( String applyname, String applypersonid, String applytime, String applyendtime, String applydays, String applystatus, String applyreason, String approve) {
//        this.applyid = applyid;
        this.applyname = applyname;
        this.applypersonid = applypersonid;
        this.applytime = applytime;
        this.applyendtime = applyendtime;
        this.applydays = applydays;
        this.applystatus = applystatus;
        this.applyreason = applyreason;
        this.approve = approve;
    }

    public ApplyItem() {
    }
}
