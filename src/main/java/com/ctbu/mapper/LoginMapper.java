package com.ctbu.mapper;

import com.ctbu.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Description 登录持久层
 * @Author LIYING
 */
@Component
@Mapper
public interface LoginMapper {
    //修改用户密码
    void updatePwd(String userName,String userPwd);
    //通过用户名获取用户密码
    String getUser(String userName);
}

