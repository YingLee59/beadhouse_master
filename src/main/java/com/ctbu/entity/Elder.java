package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * @Description
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Elder extends Person{
    private String relaName;//亲属名称
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$|^$",message="电话格式不对")
    private String relaPhnoe;//亲属电话
}
