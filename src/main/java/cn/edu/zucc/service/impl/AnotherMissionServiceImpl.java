package cn.edu.zucc.service.impl;

import cn.edu.zucc.domain.dao.AnotherMissionRepository;
import cn.edu.zucc.domain.entity.AnotherMission;
import cn.edu.zucc.service.AnotherMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnotherMissionServiceImpl implements AnotherMissionService {
    @Autowired
    private AnotherMissionRepository anotherMissionRepository;

    @Override
    public void create(AnotherMission mission){
        anotherMissionRepository.save(mission);
    }

    @Override
    public void update(AnotherMission mission){
        anotherMissionRepository.save(mission);
    }

    @Override
    public List<AnotherMission> getAllMissionbyproject(String projectname, String projectfabu, String projectchengbao){
        return anotherMissionRepository.findAllMissionbyproject(projectname,projectfabu,projectchengbao);
    }

    @Override
    public List<AnotherMission> getDeveloperMission(String missionperson){
        return anotherMissionRepository.findDeveloperMission(missionperson);
    }

    @Override
    public AnotherMission getMissionbyname (String projectname,String missionname){
        return anotherMissionRepository.findMissionbyname(projectname,missionname);
    }

    @Override
    public int countAllMission(String projectname,String missionstate){
        return anotherMissionRepository.findcountAllMission(projectname,missionstate);
    }

    @Override
    public  int countMission(String projectname){
        return anotherMissionRepository.findcountMission(projectname);
    }

    @Override
    public int countMissionperson(String projectname){
        return anotherMissionRepository.findcountMissionperson(projectname);
    }

    @Override
    public int countMissionrealtime(String projectname){
        return anotherMissionRepository.findcountMissionrealtime(projectname);
    }
}
