package com.ctbu.service.impl;

import com.ctbu.entity.Login;
import com.ctbu.mapper.LoginMapper;
import com.ctbu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public void updatePwd(String userName,String userPwd) {
        loginMapper.updatePwd(userName,userPwd);
    }

    @Override
    public String getUser(String userName) {
        return loginMapper.getUser(userName);
    }
}
