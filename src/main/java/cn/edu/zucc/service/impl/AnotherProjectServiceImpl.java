package cn.edu.zucc.service.impl;

import cn.edu.zucc.domain.dao.AnotherProjectRepository;
import cn.edu.zucc.domain.entity.AnotherProject;
import cn.edu.zucc.service.AnotherProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnotherProjectServiceImpl implements AnotherProjectService {
    @Autowired
    private AnotherProjectRepository anotherProjectRepository;

    @Override
    public void create(AnotherProject project){
        anotherProjectRepository.save(project);
    }

    @Override
    public void update(AnotherProject project){
        anotherProjectRepository.save(project);
    }

//    @Override
//    public void deleteByname(String project_name){
//        anotherProjectRepository.delete();
//    }

    @Override
    public List<AnotherProject> getAllproject(){
        return anotherProjectRepository.findAll();
    }

    @Override
    public List<AnotherProject> getBySearch(String projectstate){
        return anotherProjectRepository.findbySearch(projectstate);
    }

    @Override
    public List<AnotherProject> getBySearchYfb(String projectfabu,String projectstate){
        return anotherProjectRepository.findbySearchYfb(projectfabu,projectstate);
    }

    @Override
    public List<AnotherProject> getBySearchcsh(String projectfabu,String projectstate){
        return anotherProjectRepository.findbySearchcsh(projectfabu,projectstate);
    }

    @Override
    public List<AnotherProject> getBySearchYcb(String projectchengbao,String projectstate){
        return anotherProjectRepository.findbySearchYcb(projectchengbao,projectstate);
    }

    @Override
    public List<AnotherProject> getBySearchDys(String projectstate){
        return anotherProjectRepository.findbySearchDys(projectstate);
    }

    @Override
    public List<AnotherProject> getBySearchYys(String projectstate){
        return anotherProjectRepository.findbySearchYys(projectstate);
    }

    @Override
    public List<AnotherProject> getProjectByPfabu(String projectfabu,String projectstate){
        return anotherProjectRepository.findProject_fabu(projectfabu,projectstate);
    }
    @Override
    public List<AnotherProject> getProjectByChengbao(String projectchengbao){
        return anotherProjectRepository.findProject_chengbao(projectchengbao);
    }
    @Override
    public AnotherProject getProjectByfabuAndName(String projectfabu,String projectname){
        return anotherProjectRepository.findProjectfabuAndName(projectfabu,projectname);
    }

    @Override
    public AnotherProject getProjectbyName(String projectname,String projectchengbao){
        return anotherProjectRepository.findProjectbycbNname(projectname,projectchengbao);
    }

    @Override
    public AnotherProject getProjectname(String projectname){
        return anotherProjectRepository.findprojectname(projectname);
    }

    @Override
    public List<AnotherProject> getProjectbyname(String projectname){
        return anotherProjectRepository.findprojectbyname(projectname);
    }
}
