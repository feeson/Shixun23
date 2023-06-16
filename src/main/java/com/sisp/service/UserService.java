package com.sisp.service;

import com.sisp.utils.UUIDUtil;
import com.sisp.Dao.UserEntityMapper;
import com.sisp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;

    public List<UserEntity> selectUserInfo(UserEntity userEntity) {
        List<UserEntity> result = userEntityMapper.selectUserInfo(userEntity);
        return result;
    }

    public List<UserEntity> queryUserList(UserEntity userEntity) {
        List<UserEntity> result = userEntityMapper.queryUserList(userEntity);
        return result;
    }

    public int addUserInfo(UserEntity userEntity) {
        userEntity.setId(UUIDUtil.getUUID());
        int userResult = 0;
        try {
            userResult = userEntityMapper.insert(userEntity);
        } catch (Exception e) {}
        if (userResult != 0) {
            return 3;
        } else {
            return userResult;
        }
    }

    public int modifyUserInfo(UserEntity userEntity) {
        int userResult = userEntityMapper.updateByPrimaryKeySelective(userEntity);
        return userResult;
    }

    public int deleteUserById(UserEntity userEntity) {
        int userResult = userEntityMapper.deleteUserById(userEntity);
        return userResult;
    }


}
