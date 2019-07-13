package cn.edu.zucc.domain.dao;

import cn.edu.zucc.domain.entity.AnotherUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnotherUserRepository  extends JpaRepository<AnotherUser,String> {
  //  AnotherUser findByName(String name);

    @Query("from anotheruser u where u.user_id=:id and u.user_password=:password")
    AnotherUser findIdAndPassword(String id,String password);

    @Query("from anotheruser u where u.user_id=:uid")
    AnotherUser findUserId(@Param("uid") String uid);

    @Query("from anotheruser u where u.user_name=:name")
    AnotherUser findUserName(@Param("name") String name);

    @Query("from anotheruser u where u.user_name like %:pattern%")
    List<AnotherUser> findByNameLike(@Param("pattern") String pattern);
    @Query("from anotheruser u where u.user_office=:office")
    List<AnotherUser> findDeveloper(String office);
}
