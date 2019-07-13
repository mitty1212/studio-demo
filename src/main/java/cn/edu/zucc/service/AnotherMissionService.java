package cn.edu.zucc.service;

import cn.edu.zucc.domain.entity.AnotherMission;

import java.util.List;

public interface AnotherMissionService {
    void create(AnotherMission mission);
    void update(AnotherMission mission);

    List<AnotherMission> getAllMissionbyproject(String projectname,String projectfabu,String projectchengbao);
    List<AnotherMission> getDeveloperMission(String missionperson);
    AnotherMission getMissionbyname (String projectname,String missionname);
    int countAllMission(String projectname,String missionstate);
    int countMission(String projectname);
    int countMissionperson(String projectname);
    int countMissionrealtime(String projectname);
}
