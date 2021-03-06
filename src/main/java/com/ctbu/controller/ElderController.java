package com.ctbu.controller;

import com.ctbu.entity.CheckIn;
import com.ctbu.entity.Elder;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.CheckInService;
import com.ctbu.service.ElderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@RestController
@RequestMapping("/elder")
@Api(description = "老人信息管理")
public class ElderController {
    @Autowired
    private ElderService elderService;
    @Autowired
    private CheckInService checkInService;

    @ApiOperation(value = "通过不同条件获取老人信息")
    @GetMapping("/getElder")
    public Result getElder(Elder elder) {
        List<Elder> res = elderService.getElder(elder);
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }

    }

    @ApiOperation(value = "删除老人")
    @DeleteMapping("/deleteElder")

    public Result deleteElder(@RequestParam Integer id) {
            List<CheckIn> res = checkInService.getCheckInByElderId(id);
            if(res!=null && ! res.isEmpty()&&res.get(0).getOutDate()==null){
                return Result.error(ResultEnum.CHECKIN_EXIST.getCode(),ResultEnum.CHECKIN_EXIST.getMsg());
            }else{
                try {
                    elderService.deleteElder(id);
                    return Result.success();
                } catch (Exception e) {
                    return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());

                }
            }
        }

    @ApiOperation(value = "添加老人")
    @PostMapping("/insertElder")
    public Result insertElder(@RequestBody @Validated Elder elder,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return Result.error(ResultEnum.BIND_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            int rows = elderService.insertElder(elder);
            if (rows == 1) {
                return Result.success();
            } else {
                return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
    }

    @ApiOperation(value = "更新老人信息")
    @PutMapping("/updateElder")
    public Result updateElder(@RequestBody @Validated Elder elder, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return Result.error(ResultEnum.BIND_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            int rows = elderService.updateElder(elder);
            if (rows == 1) {
                return Result.success();
            } else {
                return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }
    }

}
