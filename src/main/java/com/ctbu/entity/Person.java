package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Date;

/**
 * @Description  员工和老人的抽象父类
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;
    @NotBlank(message="姓名不能为空")
    private String name;
    @NotBlank(message="性别不能为空")
    private String sex;
    @NotBlank(message="出生日期不能为空")
    private String birth;
    @NotBlank(message="身份证不能为空")
    @Pattern(regexp = "^\\d{18}$|^\\d{17}(X|x)$",message ="身份证格式不对")
    private String idCard;
    @NotBlank(message="电话不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message="电话格式不对")
    private String telephone;
    private String address;
}
