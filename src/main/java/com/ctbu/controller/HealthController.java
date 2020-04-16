package com.ctbu.controller;

import com.ctbu.entity.Health;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.ElderService;
import com.ctbu.service.HealthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@RestController
@RequestMapping("/health")
@Api(description = "老人健康管理档案")
public class HealthController {
    @Autowired
    HealthService healthService;
    @Autowired
    ElderService elderService;

    //查询老人健康档案（可模糊查询）
    @GetMapping("/getHealth")
    @ApiOperation(value = "查询老人健康档案")
    public Result getHealth(Health health) {
        List<Map<String, Object>> res = healthService.getHealth(health);
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    //新增老人健康档案
    @PostMapping("/insertHealth")
    @ApiOperation(value = "新增老人健康档案")
    public Result insertHealth(@RequestBody @Validated Health health, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            if (elderService.getOneElder(health.getElderId()) != null) {
                healthService.insertHealth(health);
                return Result.success();
            } else {
                return Result.error(ResultEnum.ELDER_NOT_EXIST.getCode(), ResultEnum.ELDER_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.HEALTH_EXIST.getCode(), ResultEnum.HEALTH_EXIST.getMsg());
        }
    }

    //更新老人健康档案
    @ApiOperation(value = "更新老人健康档案")
    @PutMapping("/updateHealth")
    public Result updateHealth(@RequestBody @Validated Health health, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            healthService.updateHealth(health);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }

    }
}
