package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Description 员工请假实体表
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    private Integer leaveId;//请假编号
    @NotNull(message="编号不能为空")
    @Min(value = 1,message="编号不能小于0")
    private Integer employeeId;//员工编号
    //请假日期必须是当前或将来的日期
    @FutureOrPresent(message = "请假日期一定是在将来或现在的时间")
    private Date leaveDate;//请假日期
    //假期结束一定是在将来的时间
    @Future(message = "假期结束一定是在将来的时间")
    private Date dueDate;//假期结束日期
    @Future
    private Date backDate;//销假日期
    private String reason;//请假事由
    private Float days;//请假天数
    private Float realDays;//实际假期天数

}
