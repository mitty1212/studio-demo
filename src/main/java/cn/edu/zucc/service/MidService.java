package cn.edu.zucc.service;

import cn.edu.zucc.domain.entity.ApplyItem;

import java.util.List;

public interface MidService {
    void create(ApplyItem applyItem);
    void update(ApplyItem applyItem);
    List<ApplyItem> getAllApplyItem();
    List<ApplyItem> getBySearch(String applystatus);
    List<ApplyItem> getBySearchWsp(String applystatus);

    List<ApplyItem> getApplyByApplyPersonID(String applypersonid);
    ApplyItem getApplyByApplyID(String applyid);
}
