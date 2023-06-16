package com.sisp.controller;

import com.sisp.entity.ProjectEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectControllerTest {
    String longText="this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,";
    @Resource
    ProjectController projectController;

    private ProjectEntity getDefault(){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("id");
        projectEntity.setUserId("userId");
        projectEntity.setProjectName("projectName");
        projectEntity.setProjectContent("projectContent");
        projectEntity.setCreatedBy("createdBy");
        projectEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        projectEntity.setLastUpdatedBy("lastUpdatedBy");
        projectEntity.setLastUpdateDate(new Date(new java.util.Date().getTime()));
        return projectEntity;
    }
    @Test
    void queryProjectList() {
        projectController.queryProjectList(Optional.empty());
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    void addProjectInfo() {
        projectController.addProjectInfo(new ProjectEntity());
        ProjectEntity project = getDefault();

        projectController.addProjectInfo(project);

        project.setId("id2");
        project.setProjectContent(longText);
        projectController.addProjectInfo(project);
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    void modifyProjectInfo() {
        ProjectEntity project = getDefault();

        projectController.addProjectInfo(project);

        project.setProjectContent("newprojectId");
        HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(
                project);
        project.setProjectContent(longText);
        projectController.modifyProjectInfo(project);
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    void deleteProjectById() {
        ProjectEntity project = getDefault();

        projectController.addProjectInfo(project);
        projectController.deleteProjectById(project);
        project.setId("null");
        projectController.deleteProjectById(project);
    }
}