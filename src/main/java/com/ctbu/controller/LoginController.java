package com.ctbu.controller;

import com.ctbu.entity.Login;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.LoginService;
import com.ctbu.utils.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author LIYING
 */
@RestController
@Api(description = "系统用户管理")
public class LoginController {
    @Autowired
    private LoginService loginService;
    //修改用户密码
    @PutMapping("/updatePwd")
    @ApiOperation(value="修改用户密码")
    public Result updatePwd(@RequestParam String userName, @RequestParam String userPwd){
        System.out.println(userName);
        System.out.println(userPwd);
        String password= Md5Util.toMd5(userPwd);
        System.out.println(password);
        loginService.updatePwd(userName,password);
        return Result.success();
    }
    //登录
    @PostMapping("/login")
    @ApiOperation(value="登录")
    public Result login(@RequestBody @Validated Login login,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //将前端输入的密码加密后和数据库中数据进行匹配
        String password = Md5Util.toMd5(login.getUserPwd());
        if(password.equals(loginService.getUser(login.getUserName()))){
            return Result.success();
        }else{
            return Result.error(ResultEnum.VERIFY_FAIL.getCode(),ResultEnum.VERIFY_FAIL.getMsg());
        }
    }


}
