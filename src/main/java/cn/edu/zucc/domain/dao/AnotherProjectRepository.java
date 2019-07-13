package cn.edu.zucc.domain.dao;

import cn.edu.zucc.domain.entity.AnotherProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnotherProjectRepository extends JpaRepository<AnotherProject,String> {
    @Query("from anotherproject project where project.project_fabu=:pfabu and project.project_state=:projectstate")
    List<AnotherProject> findProject_fabu(@Param("pfabu") String pfabu,String projectstate);

    @Query("from anotherproject project where project.project_chengbao=:projectchengbao and project_state='已承包' or project_state='待验收' or project_state='已验收' or project_state='已完成'")
    List<AnotherProject> findProject_chengbao(String projectchengbao);

    @Query("from anotherproject project where project.project_fabu=:pfabu and project.project_name=:pname")
    AnotherProject findProjectfabuAndName(String pfabu,String pname);

    @Query("from anotherproject project where project.project_name=:pname and project.project_chengbao=:pchengbao")
    AnotherProject findProjectbycbNname(String pname,String pchengbao);
    @Override
    List<AnotherProject> findAll();

    @Query("from anotherproject project where project.project_state=:projectstate")
    List<AnotherProject> findbySearch(@Param("projectstate") String projectstate);
    @Query("from anotherproject project where project.project_fabu=:projectfabu and project.project_state=:projectstate")
    List<AnotherProject> findbySearchYfb(String projectfabu,String projectstate);
    @Query("from anotherproject project where project.project_fabu=:projectfabu and project.project_state!=:projectstate")
    List<AnotherProject> findbySearchcsh(String projectfabu,String projectstate);
    @Query("from anotherproject project where project.project_chengbao=:projectchengbao and project.project_state=:projectstate")
    List<AnotherProject> findbySearchYcb(String projectchengbao,String projectstate);
    @Query("from anotherproject project where project.project_state=:projectstate")
    List<AnotherProject> findbySearchDys(@Param("projectstate") String projectstate);
    @Query("from anotherproject project where project.project_state=:projectstate")
    List<AnotherProject> findbySearchYys(@Param("projectstate") String projectstate);
    @Query("from anotherproject project where project.project_name=:projectname")
    AnotherProject findprojectname(String projectname);
    @Query("from anotherproject project where project.project_name=:projectname")
    List<AnotherProject> findprojectbyname(String projectname);
}
