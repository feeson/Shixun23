package com.sisp.controller;

import com.sisp.entity.UserEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoControllerTest {
    String longText = "this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,this is big text,";

    @Resource
    UserController userController;

    private UserEntity getDefault() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("id");
        userEntity.setStatus("1");
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setCreatedBy("createdBy");
        userEntity.setLastUpdatedBy("lastUpdatedBy");
        userEntity.setStartTime(new Date(new java.util.Date().getTime()));
        userEntity.setStopTime(new Date(new java.util.Date().getTime()));
        userEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        userEntity.setLastUpdateDate(new Date(new java.util.Date().getTime()));
        return userEntity;
    }

    @Test
    void userLogin() {
        UserEntity userEntity = getDefault();
        userController.userLogin(userEntity);

        userEntity.setUsername("test1");
        userEntity.setPassword("1");
        userController.userLogin(userEntity);
    }

    @Test
    void queryUserInfo() {
        UserEntity userEntity = getDefault();
        userEntity.setStatus("0");
        userController.queryUserInfo(userEntity);

        userEntity.setStatus("1");
        userController.queryUserInfo(userEntity);
    }

    @Test
    void addUserInfo() {

        UserEntity userEntity = getDefault();
        userController.addUserInfo(userEntity);

        userEntity.setUsername(longText);
        userController.addUserInfo(userEntity);

    }

    @Test
    void modifyUserInfo() {
        UserEntity userEntity = getDefault();
        userController.modifyUserInfo(userEntity);

        HttpResponseEntity httpResponseEntity = userController.queryUserInfo(userEntity);
        Object data = httpResponseEntity.getData();
        List<UserEntity> hasUsers = (List<UserEntity>) data;
        userController.modifyUserInfo(hasUsers.get(0));

    }

    @Test
    void deleteUserinfo() {
        UserEntity userEntity = getDefault();
        userController.deleteUserinfo(userEntity);


        HttpResponseEntity httpResponseEntity = userController.queryUserInfo(userEntity);
        Object data = httpResponseEntity.getData();
        List<UserEntity> hasUsers = (List<UserEntity>) data;
        userController.deleteUserinfo(hasUsers.get(0));
    }
}