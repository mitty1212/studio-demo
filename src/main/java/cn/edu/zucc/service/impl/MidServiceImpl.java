package cn.edu.zucc.service.impl;

import cn.edu.zucc.domain.dao.MIdRepository;
import cn.edu.zucc.domain.entity.ApplyItem;
import cn.edu.zucc.service.MidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MidServiceImpl implements MidService {
    @Autowired
    private MIdRepository mIdRepository;

    @Override
    public void create(ApplyItem applyItem) {
        mIdRepository.save(applyItem);
    }

    @Override
    public void update(ApplyItem applyItem) {
        mIdRepository.save(applyItem);
    }

    @Override
    public List<ApplyItem> getBySearch(String applystatus) {
        return mIdRepository.findByApprove(applystatus);
    }

    @Override
    public List<ApplyItem> getBySearchWsp(String applystatus) {
        return mIdRepository.findByApproveWsp(applystatus);
    }

    @Override
    public List<ApplyItem> getAllApplyItem() {
        return mIdRepository.findAll();
    }


    @Override
    public List<ApplyItem> getApplyByApplyPersonID(String applypersonid) {
        return mIdRepository.findByApplypersonid(applypersonid);
    }

    @Override
    public ApplyItem getApplyByApplyID(String applyid) {
        return mIdRepository.findByApplyid(applyid);
    }
}
