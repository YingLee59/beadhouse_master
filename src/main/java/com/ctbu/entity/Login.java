package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * @Description 登录实体类
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @NotBlank(message="用户名不能为空")
    private String userName;//用户名
    @NotBlank(message="密码不能为空")
    private String userPwd;//密码
}
