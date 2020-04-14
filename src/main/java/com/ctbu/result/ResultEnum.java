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
    DATA_EXIST(409,"数据已存在")
    ;
    private Integer code;
    private String msg;
}
