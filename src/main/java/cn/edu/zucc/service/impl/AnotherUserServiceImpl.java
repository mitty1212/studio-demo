package cn.edu.zucc.service.impl;

import cn.edu.zucc.domain.dao.AnotherUserRepository;
import cn.edu.zucc.domain.entity.AnotherUser;
import cn.edu.zucc.service.AnotherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnotherUserServiceImpl implements AnotherUserService {
    @Autowired
    private AnotherUserRepository anotherUserRepository;
    @Override
    @CachePut(value = "user",key = "#user.user_id")
    public void create(AnotherUser user){
        anotherUserRepository.save(user);
    }
    @Override
    @CachePut(value = "user",key = "#user.user_id")
    public void update(AnotherUser user){
        anotherUserRepository.save(user);
    }
    @Override
    @CacheEvict(value = "user",key = "#user_id")
    public void deleteById(String user_id){
        anotherUserRepository.deleteById(user_id);
    }
    @Override
    public List<AnotherUser> getAllUsers(){
        return anotherUserRepository.findAll();
    }
    @Override
    public AnotherUser getUserbyId(String user_id){
        return anotherUserRepository.findUserId(user_id);
    }
    @Override
    public AnotherUser getUserbyName(String name){
        return anotherUserRepository.findUserName(name);
    }
    @Override
    public AnotherUser getUser(String id,String password){

        return anotherUserRepository.findIdAndPassword(id,password);
    }
    @Override
    public  List<AnotherUser> getDeveloper(String user_office){
        return anotherUserRepository.findDeveloper(user_office);
    }

}
