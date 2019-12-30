package com.azhuo.usercrud.service;

import com.azhuo.usercrud.pojo.UserInfo;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     */
    List<UserInfo> findAll();

    /**
     * 根据用户id查询
     * @param uid
     */
    UserInfo findUserById(Long uid);

    /**
     * 添加用户
     * @param userInfo
     */
    void addUser(UserInfo userInfo);

    /**
     * 根据id删除
     * @param uid
     */
    void deleteUserById(Long uid);

    /**
     * 根据id更新用户
     * @param userInfo
     */
    void updateUser(UserInfo userInfo);

    /**
     * 用户名密码校验
     * @param userInfo
     * @return
     */
    UserInfo login(UserInfo userInfo);

    /**
     * 用户注册
     */
    void regist(UserInfo formUserInfo);
}
