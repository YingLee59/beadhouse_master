package com.ctbu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @Description 老人外出登记实体表
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutRecord {
    private Integer outId;//外出登记号
    @NotNull(message="编号不能为空")
    @Min(value = 1,message="编号不能小于0")
    private Integer elderId;//老人编号
    //@JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date outTime;//外出时间
    //@JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    @Null(message="必须返回时才能添加返回时间")
    private Date backTime;//返回时间
    private String remark;//相关备注
}
