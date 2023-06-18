package com.sisp.service;

import com.sisp.utils.SpringSecurityUtil;
import com.sisp.utils.UUIDUtil;
import com.sisp.dao.UserEntityMapper;
import com.sisp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        String currentUsername = SpringSecurityUtil.getCurrentUsername();
        long currentTime = new Date().getTime();
        int userResult = 0;
        try {
            userEntity.setStatus("1");
            userEntity.setCreatedBy(currentUsername);
            userEntity.setLastUpdatedBy(currentUsername);
            userEntity.setCreationDate(new Date(currentTime));
            userEntity.setLastUpdateDate(new Date(currentTime));
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
