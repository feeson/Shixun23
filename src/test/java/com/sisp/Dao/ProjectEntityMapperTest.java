package com.sisp.Dao;

import com.sisp.entity.ProjectEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.internal.junit.JUnitTestRule;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProjectEntityMapperTest {
    private ProjectEntityMapper projectEntityMapper=getProjectEntityMapper();

    ProjectEntityMapperTest() throws IOException {
    }

    private ProjectEntityMapper getProjectEntityMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        return projectEntityMapper;
    }

    @Test
    void queryProjectList() {
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId(String.valueOf(UUID.randomUUID()));
        projectEntity.setUserId("testUserId");
        projectEntity.setProjectName("testProjectName");
        projectEntity.setProjectContent("testProjectContent");
        projectEntity.setCreatedBy("testCreatedBy");
        projectEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        projectEntity.setLastUpdatedBy("testLastUpdatedBy");
        projectEntity.setLastUpdateDate(new Date(new java.util.Date().getTime()));

        Assertions.assertEquals(1,projectEntityMapper.insert(projectEntity));

        projectEntity=new ProjectEntity();
        projectEntity.setUserId("UserId");
        Assertions.assertEquals(1,projectEntityMapper.queryProjectList(projectEntity).size());
        projectEntity.setProjectName("ProjectName");
        Assertions.assertEquals(1,projectEntityMapper.queryProjectList(projectEntity).size());
        projectEntity.setProjectContent("ProjectContent");
        Assertions.assertEquals(1,projectEntityMapper.queryProjectList(projectEntity).size());
        projectEntity.setCreatedBy("CreatedBy");
        Assertions.assertEquals(0,projectEntityMapper.queryProjectList(projectEntity).size());
        projectEntity.setCreatedBy(null);
        projectEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        Assertions.assertEquals(1,projectEntityMapper.queryProjectList(projectEntity).size());
        projectEntity.setLastUpdatedBy("wrongLastUpdatedBy");
        Assertions.assertEquals(0,projectEntityMapper.queryProjectList(projectEntity).size());
    }

    @Test
    void insert() throws IOException {


        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId(String.valueOf(UUID.randomUUID()));
        projectEntity.setUserId("testUserId");
        projectEntity.setProjectName("testProjectName");
        projectEntity.setProjectContent("testProjectContent");
        projectEntity.setCreatedBy("testCreatedBy");
        projectEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        projectEntity.setLastUpdatedBy("testLastUpdatedBy");
        projectEntity.setLastUpdateDate(new Date(new java.util.Date().getTime()));

        Assertions.assertEquals(1,projectEntityMapper.insert(projectEntity));
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void deleteProjectById() {
    }
}