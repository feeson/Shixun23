package com.sisp.service;

import com.sisp.Dao.ProjectEntityMapper;
import com.sisp.entity.ProjectEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.dto.HttpResponseEntityFactory;
import com.sisp.utils.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    private final static Logger logger= LoggerFactory.getLogger(ProjectService.class);
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
        String currentUserId =SpringSecurityUtil.getCurrentUsername();
        long currentTime = new Date().getTime();
        projectEntity.setUserId(currentUserId);
        projectEntity.setId(String.valueOf(UUID.randomUUID()));
        projectEntity.setCreatedBy(currentUserId);
        projectEntity.setCreationDate(new java.sql.Date(currentTime));
        projectEntity.setLastUpdatedBy(currentUserId);
        projectEntity.setLastUpdateDate(new java.sql.Date(currentTime));
        try {
            projectEntityMapper.insert(projectEntity);
        }catch (Exception e){
            logger.error("Error: projectEntityMapper.insert(projectEntity)\n"+projectEntity+"\n");
            logger.error(e.getMessage());
            throw new SQLException("插入失败");
        }
        return HttpResponseEntityFactory.get200();
    }

    @Transactional
    public HttpResponseEntity modifyProjectInfo(ProjectEntity projectEntity) throws SQLException {
        String currentUserId =SpringSecurityUtil.getCurrentUsername();
        long currentTime=new Date().getTime();
        projectEntity.setLastUpdatedBy(currentUserId);
        projectEntity.setLastUpdateDate(new java.sql.Date(currentTime));
        try {
            projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        }catch (Exception e){
            logger.error("Error : projectEntityMapper.updateByPrimaryKeySelective(projectEntity)\n"+ projectEntity);
            throw new SQLException("修改失败");
        }
        return HttpResponseEntityFactory.get200();
    }

    @Transactional
    public HttpResponseEntity deleteProjectById(ProjectEntity projectEntity) throws SQLException {
        if (projectEntityMapper.deleteProjectById(projectEntity)==1){
            return HttpResponseEntityFactory.get200();
        }else {
            logger.error("Error: projectEntityMapper.deleteProjectById(projectEntity)\n"+projectEntity);
            throw new SQLException("删除失败");
        }
    }
}
