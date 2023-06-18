package com.sisp.dao;

import com.sisp.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserEntityMapper {
    int insert(UserEntity userEntity);

    int deleteUserById(UserEntity userEntity);

//    int deleteUserinfo(UserEntity userEntity);

    int updateByPrimaryKeySelective(UserEntity userEntity);
    List<UserEntity> selectUserInfo(UserEntity userEntity);

    List<UserEntity> queryUserList(UserEntity userEntity);
}
