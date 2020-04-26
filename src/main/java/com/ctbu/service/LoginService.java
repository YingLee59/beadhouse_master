package com.ctbu.service;

import com.ctbu.entity.Login;

/**
 * @Description
 * @Author LIYING
 */
public interface LoginService {
    //修改用户密码
    void updatePwd(String userName,String userPwd);
    //通过用户名获取用户密码
    String getUser(String userName);
}
