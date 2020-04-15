package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.sql.Date;

/**
 * @Description
 * @Author LIYING
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Person{
    @NotNull(message="编号不能为空")
    @Min(value = 1,message="编号不能小于0")
    private Integer dep;
    @NotBlank(message="职位不能为空")
    private String job;
    @NotBlank(message="入职日期不能为空")
    private String hiredate;
    @Email
    private String email;

}
