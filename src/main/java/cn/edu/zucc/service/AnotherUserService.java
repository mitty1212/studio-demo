package cn.edu.zucc.service;

import cn.edu.zucc.domain.entity.AnotherUser;

import java.util.List;

public interface AnotherUserService {
    void create(AnotherUser user);
    void update(AnotherUser user);
    void deleteById(String user_id);
    List<AnotherUser> getAllUsers();
    AnotherUser getUserbyId(String user_id);
    AnotherUser getUserbyName(String name);
    AnotherUser getUser(String name,String password);
    List<AnotherUser> getDeveloper(String user_office);
}
