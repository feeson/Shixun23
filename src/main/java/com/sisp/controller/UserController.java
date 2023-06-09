package com.sisp.controller;

import com.sisp.entity.dto.HttpResponseEntity;
import com.sisp.entity.UserEntity;
import com.sisp.service.UserService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lucy
 * @Date: 2023-05-30-12:50
 */

@RestController
@RequestMapping("/admin")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasUsers  = userService.selectUserInfo(userEntity);

            if (CollectionUtils.isEmpty(hasUsers)) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUsers.get(0));
                httpResponseEntity.setMessage("用户名或密码错误");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUsers);
                httpResponseEntity.setMessage("登录成功");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return httpResponseEntity;
    }


    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserInfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<UserEntity> hasUsers  = userService.queryUserList(userEntity);

            if (CollectionUtils.isEmpty(hasUsers)) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUsers.get(0));
                httpResponseEntity.setMessage("无用户信息");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUsers);
                httpResponseEntity.setMessage("登录成功");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.addUserInfo(userEntity);

            if (result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.modifyUserInfo(userEntity);

            if (result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("修改成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteUserinfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUserinfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.deleteUserById(userEntity);

            if (result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return httpResponseEntity;
    }

}
