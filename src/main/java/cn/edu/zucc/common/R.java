package cn.edu.zucc.common;


//import org.jetbrains.annotations.Nullable;

import cn.edu.zucc.domain.entity.AnotherUser;
import cn.edu.zucc.domain.entity.LeaveApplication;
import cn.edu.zucc.domain.entity.User;
import cn.edu.zucc.service.LeaveService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.List;

public class R<T>  implements Serializable {
    private static final long serialVersionUID=1L;
    private int code;
    private boolean success;
    private T data;
    private String msg;
    private R(ResultCode resultCode){
        this(resultCode,null,resultCode.getMessage());
    }
    private R(ResultCode resultCode, String msg){
        this(resultCode,null,msg);
    }
    private R(ResultCode resultCode, T data){
        this(resultCode,data,resultCode.getMessage());
    }
    private R(ResultCode resultCode, T data, String msg){
        this(resultCode.getCode(),data,msg);
    }
    private R(int code,T data,String msg){
        this.code=code;
        this.data=data;
        this.msg=msg;
        this.success= ResultCode.SUCCESS.code==code;
    }
//    public static boolean isSuccess(@Nullable R<?> result){
//        return(Boolean) Optional.ofNullable(result).map((x)->{
//            return ResultCode.SUCCESS.code==x.code;
//        }).orElse(Boolean.FALSE);
//    }
//    public static boolean isNotSuccess(@Nullable R<?> result){
//        return !isSuccess(result);
//    }
    public static <T> R<T> data(T data){
        return data(data,"操作成功");
    }
    public static <T> R<T> data(T data, String msg){
        return data(200,data,msg);
    }
    public static <T> R<T> data(int code, T data, String msg){
        return new R(code,data,data==null?"暂无数据":msg);
    }
    public static <T> R<T> success(String msg){
        return new R(ResultCode.SUCCESS,msg);
    }
    public static <T> R<T> success(ResultCode resultcode){
        return new R(resultcode);
    }
    public static <T> R<T> success(ResultCode resultCode, String msg){
        return new R(resultCode,msg);
    }
    public static <T> R<T> fail(String msg){
        return new R(ResultCode.FAILURE,msg);
    }
    public static <T> R<T> fail(int code, String msg){
        return new R(code,(Object)null,msg);
    }
    public static <T> R<T> fail(ResultCode resultCode){
        return new R(resultCode);
    }
    public static <T> R<T> fail(ResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }
    public static <T> R<T> status(boolean flag){
        return flag?success("操作成功"):fail("操作失败");
    }
    //joy code
    public static R<List<LeaveService>> data(List<LeaveApplication> allLists) {
        return null;
    }

    public int getCode(){
        return this.code;
    }
    public boolean isSuccess(){
        return this.success;
    }
    public T getData(){
        return this.data;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setSuccess(final boolean success){
        this.success=success;
    }
    public void setData(final T data){
        this.data=data;
    }
    public void setMsg(final String msg){
        this.msg=msg;
    }
    public R(){

    }


    public static final Key KEY = new SecretKeySpec("zucc".getBytes(), SignatureAlgorithm.HS512.getJcaName());
    public static <T> R<T> Loginsuccess(AnotherUser user){
        JwtBuilder builder = Jwts.builder().setHeaderParam("type","JsonWebToken")
                .claim("id",user.getUser_id())
                .claim("name",user.getUser_name())
              //  .claim("email",user.getEmail())
                .signWith(SignatureAlgorithm.HS512,KEY);
        long expMills = System.currentTimeMillis() + 3600*1000*24;
        Date exp = new Date(expMills);
        builder.setExpiration(exp).setNotBefore(new Date());
        return new R(ResultCode.SUCCESS,builder.compact(),"登陆成功");
    }

    public static <T> R<T> Loginsuccess(User user){
        JwtBuilder builder = Jwts.builder().setHeaderParam("type","JsonWebToken")
                .claim("id",user.getId())
                .claim("name",user.getName())
                  .claim("email",user.getEmail())
                .signWith(SignatureAlgorithm.HS512,KEY);
        long expMills = System.currentTimeMillis() + 3600*1000*24;
        Date exp = new Date(expMills);
        builder.setExpiration(exp).setNotBefore(new Date());
        return new R(ResultCode.SUCCESS,builder.compact(),"登陆成功");
    }
//    public User getLickUser(){
//
//    }
}
