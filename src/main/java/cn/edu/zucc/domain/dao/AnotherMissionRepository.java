package cn.edu.zucc.domain.dao;

import cn.edu.zucc.domain.entity.AnotherMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnotherMissionRepository extends JpaRepository<AnotherMission,String> {

    @Query("from anothermission mission where mission.project_name=:projectname and mission.project_fabu=:projectfabu and mission.project_chengbao=:projectchengbao")
    List<AnotherMission> findAllMissionbyproject(String projectname, String projectfabu, String projectchengbao);

    @Query("from anothermission mission where mission.mission_person=:missionperson")
    List<AnotherMission> findDeveloperMission(String missionperson);

    @Query("from anothermission mission where mission.project_name=:projectname and mission.mission_name=:missionname")
    AnotherMission findMissionbyname(String projectname,String missionname);

    @Query("Select count(*) from anothermission where project_name=:projectname and mission_state=:missionstate")
    int findcountAllMission(String projectname,String missionstate);

    @Query("Select count(*) from anothermission where project_name=:projectname")
    int findcountMission(String projectname);


    @Query("Select count(DISTINCT mission_person) from anothermission where project_name=:projectname")
    int findcountMissionperson(String projectname);


    @Query("SELECT sum(mission_realtime) from anothermission where project_name=:projectname")
    int findcountMissionrealtime(String projectname);
}
