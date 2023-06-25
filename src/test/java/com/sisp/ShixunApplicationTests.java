package com.sisp;

import com.sisp.dao.UserEntityMapper;
import com.sisp.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
class ShixunApplicationTests {

    ShixunApplicationTests() throws IOException {
    }

    @Resource
    private UserEntityMapper userEntityMapper;
//    private UserEntityMapper userEntityMapper = getUserEntityMapper();

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
        userEntity.setStartTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setStopTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setStatus("testStatus");
        userEntity.setCreatedBy("testCreatedBy");
        userEntity.setCreationDate(new Date(new java.util.Date().getTime()));
        userEntity.setLastUpdatedBy("testLastUpdatedBy");
        userEntity.setLastUpdateDate(new Date(new java.util.Date().getTime()));

        Assertions.assertEquals(1, userEntityMapper.insert(userEntity));
    }


//    @Test
    void deleteUserById() {

        UserEntity user = new UserEntity();
        user.setId("id2347890328984789");
        userEntityMapper.insert(user);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        Assertions.assertEquals(1, userEntityMapper.deleteUserById(userEntity));
    }
}
