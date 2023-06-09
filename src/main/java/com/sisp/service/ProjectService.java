package com.sisp.service;

import com.sisp.Dao.ProjectEntityMapper;
import com.sisp.entity.ProjectEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Resource
    ProjectEntityMapper projectEntityMapper;

    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){
        return projectEntityMapper.queryProjectList(projectEntity);
    }

    public int addProjectInfo(ProjectEntity projectEntity){
        return projectEntityMapper.insert(projectEntity);
    }

    public int modifyProjectInfo(ProjectEntity projectEntity){
        return projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
    }

    public int deleteProjectById(ProjectEntity projectEntity){

        return projectEntityMapper.deleteProjectById(projectEntity);
    }
}
