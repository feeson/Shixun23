package com.sisp;

import com.sisp.dao.UserEntityMapper;
import com.sisp.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.UUID;

@SpringBootTest
class ShixunApplicationTests {

    ShixunApplicationTests() throws IOException {
    }

    private UserEntityMapper userEntityMapper = getUserEntityMapper();

    private UserEntityMapper getUserEntityMapper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        return userEntityMapper;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void insert() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(String.valueOf(UUID.randomUUID()));
        userEntity.setUsername("testUsername");
        userEntity.setStartTime(new Date(new java.util.Date().getTime()));
        userEntity.setStopTime(new Date(new java.util.Date().getTime()));
        userEntity.setStatus("testStatus");
        userEntity.setCreatedBy("testCreatedBy");
        userEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        userEntity.setLastUpdatedBy("testLastUpdatedBy");
        userEntity.setLastUpdateDate(new Date(new java.util.Date().getTime()));

        Assertions.assertEquals(1, userEntityMapper.insert(userEntity));
    }


    @Test
    void deleteUserById() {

        UserEntity user = new UserEntity();
        user.setId("testId");
        userEntityMapper.insert(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        Assertions.assertEquals(1, userEntityMapper.deleteUserById(userEntity));
    }
}
