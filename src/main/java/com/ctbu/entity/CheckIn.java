package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 入住实体类
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn {
    private Integer checkId;//入住编号
    @NotNull(message="编号不能为空")
    @Min(value = 1,message="编号不能小于0")
    private Integer elderId;//老人编号
    @NotBlank(message="房间号不能为空")
    private String roomId;//房间编号
    @NotBlank(message="入住日期不能为空")
    private String checkDate;//入住时间
    private String newCheckDate;//续住日期
    private String outDate;//退住日期
    private double prepaid;//预收费用
    private double cost;//已用费用
    private double balance;//余额
}
