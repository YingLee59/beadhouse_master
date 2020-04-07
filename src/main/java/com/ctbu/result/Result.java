package com.ctbu.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 前端调用接口的时候，当成功调用和失败调用能够以此返回相应的数据
 * @Author LIYING
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//状态码
    private String msg;//返回的提示信息
    private Object data;//实际返回的数据信息

    //因为使用了lombok插件所以没有手写getter、setter、构造器等

    /**
     * 当调用成功不返回数据
     * @return
     */
    public static Result success(){
        Result result =new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }
    /**
     * 当调用成功并返回数据
     * @param data
     * @return
     */
    public static Result success(Object data){
        Result result =new Result();
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setData(data);
        return result;

    }
    /**
     * 失败
     * @param code,msg
     * @return
     */
    public static Result error(Integer code, String msg){
        Result result =new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
