package com.azhuo.usercrud.dao;

import com.azhuo.usercrud.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {


    public UserInfo findByUsername(String username);
}
