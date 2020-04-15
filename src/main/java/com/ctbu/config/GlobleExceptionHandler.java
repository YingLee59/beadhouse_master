//package com.ctbu.config;
//
//import com.ctbu.result.Result;
//import com.ctbu.result.ResultEnum;
//import lombok.extern.java.Log;
//import org.springframework.validation.BindException;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * @Description 全局异常统一处理
// * @Author LIYING
// */
//@ControllerAdvice
//@ResponseBody
//@Log
//public class GlobleExceptionHandler {
//    @ExceptionHandler(value = Exception.class)
//    public Result ExceptionHandler(HttpServletRequest request, Exception e) {
//        if (e instanceof BindException) {
//            BindException ex = (BindException) e;
//            List<ObjectError> allErrors = ex.getAllErrors();//捕获的所有错误对象
//            ObjectError error = allErrors.get(0);
//            String defaultMessage = error.getDefaultMessage();//异常内容
//            log.info(defaultMessage);//打印日志
//            return Result.error(ResultEnum.BIND_ERROR.getCode(), defaultMessage);
//        } else {
//            return Result.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
//        }
//    }
//
//}
