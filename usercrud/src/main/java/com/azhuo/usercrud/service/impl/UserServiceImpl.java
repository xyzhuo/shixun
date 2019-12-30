package com.azhuo.usercrud.service.impl;

import com.azhuo.usercrud.dao.UserDao;
import com.azhuo.usercrud.pojo.UserInfo;
import com.azhuo.usercrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User 服务层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder; // 密码加密

    /**
     * 查询所有用户
     */
    @Override
    public List<UserInfo> findAll() {

        List<UserInfo> userInfos = userDao.findAll();
        // for (UserInfo userInfo : userInfos) {
        //     System.out.println(userInfo);
        // }
        return userInfos;
    }

    /**
     * id查询
     * @param uid
     */
    @Override
    public UserInfo findUserById(Long uid) {
        UserInfo userInfo = userDao.findById(uid).get();
        return userInfo;
    }

    /**
     * 添加用户
     * @param userInfo
     */
    @Override
    public void addUser(UserInfo userInfo) {
        userDao.save(userInfo);
    }

    /**
     * 更新用户
     * @param uid
     */
    @Override
    public void deleteUserById(Long uid) {
        userDao.deleteById(uid);
    }

    /**
     * 用户更新
     * @param userInfo
     */
    @Override
    public void updateUser(UserInfo userInfo) {
        userDao.save(userInfo);
    }

    /**
     * 用户名密码校验
     * @param formUserInfo
     * @return
     */
    @Override
    public UserInfo login(UserInfo formUserInfo) {

        // 根据用户名查询
        UserInfo userInfo = userDao.findByUsername(formUserInfo.getUsername());
        System.out.println("userInfo = "+ userInfo);

        // 进行密码校验：matches
        if(userInfo != null && encoder.matches(formUserInfo.getPassword(), userInfo.getPassword())){
            return userInfo;
        }

        return null;
    }


    /**
     * 用户注册
     * @param formUserInfo
     */
    @Override
    public void regist(UserInfo formUserInfo){

        // 对密码加密
        formUserInfo.setPassword(encoder.encode(formUserInfo.getPassword()));

        formUserInfo.setEmail("");
        formUserInfo.setSex("");
        formUserInfo.setProvince("");
        formUserInfo.setHobby("");

        userDao.save(formUserInfo);
    }
}
