package com.sisp.entity.dto;

import com.sisp.entity.ProjectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class HttpResponseEntityTest {

    @Test
    void get200() {
    }

    @Test
    void testGet200() {
    }

    @Test
    void getData() {
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setId("123");
        HashMap<String,ProjectEntity> map= new HashMap<>();
        map.put("123",projectEntity);
        HttpResponseEntity httpResponseEntity = HttpResponseEntityFactory.get200(
                map);
        HashMap<String,ProjectEntity> data;
        data =httpResponseEntity.getData(HashMap.class);
        Assertions.assertEquals("123",data.get("123").getId());
    }
}