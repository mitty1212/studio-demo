package cn.edu.zucc.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "anotheruser")
public class AnotherUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_id;  //账号
    private String user_password;
    private String user_name;       //用户名
    private String user_type;
    private String user_office;

    public String getUser_office() {
        return user_office;
    }

    public void setUser_office(String user_office) {
        this.user_office = user_office;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public AnotherUser(){

    }
    public AnotherUser(String user_id,String user_password,String user_name,String user_type){
        this.user_id=user_id;
        this.user_password=user_password;
        this.user_name=user_name;
        this.user_type=user_type;
    }
    public AnotherUser(String user_id,String user_password,String user_name,String user_type,String user_office){
        this.user_id=user_id;
        this.user_password=user_password;
        this.user_name=user_name;
        this.user_type=user_type;
        this.user_office=user_office;
    }
}
