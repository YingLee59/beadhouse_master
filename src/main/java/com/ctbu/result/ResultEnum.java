package com.ctbu.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author LIYING
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    //定义与前端交互的状态码和错误信息的枚举类
    SUCCESS(200,"成功！"),
    DATA_IS_NULL(404,"数据为空"),
    DELETE_FAIL(405,"删除失败"),
    UPDATE_FAIL(406,"更新失败"),
    VERIFY_FAIL(407,"验证失败"),
    INSERT_FAIL(408,"插入失败"),
    DATA_EXIST(409,"数据已存在"),
    DEP_NOT_EXIST(410,"该编号部门不存在"),
    ELDER_NOT_EXIST(411,"该编号老人不存在"),
    HEALTH_EXIST(412,"该老人已存在健康档案"),
    OUTRECORD_EXIST(413,"该老人外出未返，不能新增外出记录"),
    EMPLOYEE_NOT_EXIST(414,"该编号员工不存在"),
    LEAVE_EXIST(415,"该员工请假未返，不能新增请假记录"),
    ROOMTYPE_NOT_EXIST(416,"该编号房间类型不存在"),
    ROOM_EXIST(417,"该编号房间存在"),
    CHECKIN_EXIST(418,"该编号老人已办理入住"),
    ROOM_NOT_EXIST(419,"该编号房间不存在"),
    ROOM_FULL(420,"该房间已满"),
    ROOM_EXIST_ELDER(421,"该房间有人居住，不能删除！"),
    BIND_ERROR(1000,"")
    ;
    private Integer code;
    private String msg;
}
