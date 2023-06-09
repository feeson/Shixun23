package com.sisp.Dao;

import com.sisp.entity.ProjectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.internal.junit.JUnitTestRule;

import javax.annotation.Resource;

import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectEntityMapperTest {

    @Resource
    ProjectEntityMapper projectEntityMapper;
    @Test
    void queryProjectList() {
        ProjectEntity projectEntity=new ProjectEntity();

        List<ProjectEntity> test=new ArrayList<>();

    }

    @Test
    void insert() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void deleteProjectById() {
    }
}