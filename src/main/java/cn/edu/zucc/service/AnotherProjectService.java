package cn.edu.zucc.service;

import cn.edu.zucc.domain.entity.AnotherProject;

import java.util.List;

public interface AnotherProjectService {
    void create(AnotherProject project);
    void update(AnotherProject project);
    //void deleteByname(String project_name);
    List<AnotherProject> getAllproject();
    List<AnotherProject> getBySearch(String projectstate);
    List<AnotherProject> getBySearchYfb(String projectfabu,String projectstate);
    List<AnotherProject> getBySearchcsh(String projectfabu,String projectstate);
    List<AnotherProject> getBySearchYcb(String projectchengbao,String projectstate);
    List<AnotherProject> getBySearchDys(String projectstate);
    List<AnotherProject> getBySearchYys(String projectstate);
    List<AnotherProject> getProjectByPfabu(String projectfabu,String projectstate);
    List<AnotherProject> getProjectByChengbao(String projectchengbao);
    AnotherProject getProjectByfabuAndName(String projectfabu,String projectname);
    AnotherProject getProjectbyName(String projectname,String projectchengbao);
    AnotherProject getProjectname(String projectname);
    List<AnotherProject> getProjectbyname(String projectname);
}
