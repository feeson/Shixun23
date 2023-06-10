package com.sisp.service;

import com.sisp.Dao.ProjectEntityMapper;
import com.sisp.entity.ProjectEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.utils.SpringSecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Resource
    ProjectEntityMapper projectEntityMapper;

    public HttpResponseEntity queryProjectList(ProjectEntity projectEntity){
        List<ProjectEntity> projectEntities = projectEntityMapper.queryProjectList(
                projectEntity);
        HttpResponseEntity httpResponseEntity = HttpResponseEntityFactory.get200(
                projectEntities);
        return httpResponseEntity;
    }

    @Transactional
    public HttpResponseEntity addProjectInfo(ProjectEntity projectEntity) throws SQLException {
        String currentUserId = SpringSecurityUtil.getCurrentUsername();
        long currentTime = new Date().getTime();
        projectEntity.setCreatedBy(currentUserId);
        projectEntity.setCreationDate(new java.sql.Date(currentTime));
        projectEntity.setLastUpdatedBy(currentUserId);
        projectEntity.setLastUpdateDate(new java.sql.Date(currentTime));
        if (projectEntityMapper.insert(projectEntity)==1){
            return HttpResponseEntityFactory.get200();
        }else {
            throw new SQLException("插入失败");
        }
    }

    public int modifyProjectInfo(ProjectEntity projectEntity){
        return projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
    }

    public int deleteProjectById(ProjectEntity projectEntity){

        return projectEntityMapper.deleteProjectById(projectEntity);
    }
}
