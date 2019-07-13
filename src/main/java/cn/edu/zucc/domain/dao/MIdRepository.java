package cn.edu.zucc.domain.dao;

import cn.edu.zucc.domain.entity.ApplyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MIdRepository extends JpaRepository<ApplyItem,String> {
    ApplyItem findByApplyid(String applyid);

    @Override
    List<ApplyItem> findAll();

    @Query("from ApplyItem item where item.applypersonid=:applypersonid")
    List<ApplyItem> findByApplypersonid(@Param("applypersonid") String applypersonid);
    @Query("from ApplyItem item where item.applystatus!=:applystatus")
    List<ApplyItem> findByApprove(@Param("applystatus") String applystatus);

    @Query("from ApplyItem item where item.applystatus=:applystatus")
    List<ApplyItem> findByApproveWsp(@Param("applystatus") String applystatus);

}
