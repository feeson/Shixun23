package com.sisp.controller;

import com.sisp.entity.UserEntity;
import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.utils.SpringSecurityUtil;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.util.Date;
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
        userEntity.setStartTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setStopTime(new Timestamp(System.currentTimeMillis()));
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

        userEntity.setUsername("\\`~`！￥&*（🆒😀/0/'ヾ(≧▽≦*)o🍔🍕🥗🥙🍅🍆🌱");
        userEntity.setPassword(longText);
        userController.userLogin(userEntity);
    }

    @Test
    void queryUserInfo() {
        UserEntity userEntity = getDefault();
        userEntity.setStatus("0");
        userController.queryUserInfo(userEntity);

        userEntity.setStatus("1");
        userController.queryUserInfo(userEntity);

        userEntity.setUsername("\\`~`！￥&*（🆒😀/0/'ヾ(≧▽≦*)o🍔🍕🥗🥙🍅🍆🌱");
        userEntity.setPassword(longText);
        userController.userLogin(userEntity);
    }

    @Test
    @WithMockUser(username = "name",password = "pwd")
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

        try {
            userController.modifyUserInfo(hasUsers.get(0));
            // 使代码进入catch
            userController.modifyUserInfo(null);
        } catch (Exception e) {
            Assertions.assertEquals("NullPointerException", e.getClass().getSimpleName());
        }
    }

    @Test
    void deleteUserinfo() {
        UserEntity userEntity = getDefault();
        userController.deleteUserinfo(userEntity);


        HttpResponseEntity httpResponseEntity = userController.queryUserInfo(userEntity);
        Object data = httpResponseEntity.getData();
        List<UserEntity> hasUsers = (List<UserEntity>) data;

        // 使代码进入catch
        try {
            userController.deleteUserinfo(hasUsers.get(0));
            userEntity.setUsername("\\`~`！￥&*（🆒😀/0/'ヾ(≧▽≦*)o🍔🍕🥗🥙🍅🍆🌱//':：/**/@user name = 'admin'");
            userEntity.setPassword(longText);
            userController.userLogin(userEntity);
        } catch (Exception e) {
            Assertions.assertEquals("NullPointerException", e.getClass().getSimpleName());
        }

    }
}