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

    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    void addProjectInfo() {
        ProjectEntity project = getDefault();

        HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(
                project);

        HttpResponseEntity expected=new HttpResponseEntity();
        expected.setCode("200");
        expected.setMessage("OK");


        Assertions.assertEquals(expected,httpResponseEntity);
        project.setId("id2");
        project.setProjectContent(longText);
        httpResponseEntity=projectController.addProjectInfo(project);
        expected.setCode("400");

        Assertions.assertEquals(expected.getCode(),httpResponseEntity.getCode());
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    void modifyProjectInfo() {
        ProjectEntity project = getDefault();

        projectController.addProjectInfo(project);

        project.setProjectContent("new projectId");
        HttpResponseEntity httpResponseEntity = projectController.modifyProjectInfo(
                project);
        Assertions.assertEquals("200",httpResponseEntity.getCode());

        project.setProjectContent(longText);
        Assertions.assertEquals("400",projectController.modifyProjectInfo(project).getCode());
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
    void deleteProjectById() {
        ProjectEntity project = getDefault();

        projectController.addProjectInfo(project);

        Assertions.assertEquals("200",projectController.deleteProjectById(project).getCode());

        project.setId("null");
        Assertions.assertEquals("400",projectController.deleteProjectById(project).getCode());
    }
}