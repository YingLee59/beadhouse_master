package com.ctbu.controller;

import com.ctbu.entity.Elder;
import com.ctbu.entity.OutRecord;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.ElderService;
import com.ctbu.service.OutRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author LIYING
 */
@RestController
@RequestMapping("/outrecord")
@Api(description = "外出登记管理")
public class OutRecordController {
    @Autowired
    OutRecordService outRecordService;
    @Autowired
    ElderService elderService;

    //查询全部外出记录
    @ApiOperation(value = "查询所有外出记录")
    @GetMapping("/getAllOutRecord")
    public Result getAllOutRecord() {
        List<Map<String, Object>> res = outRecordService.getAllOutRecord();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    //通过老人编号查询外出记录
    @ApiOperation(value = "通过老人编号查询老人外出记录")
    @GetMapping("/getElderRecord")
    public Result getElderRecord(Integer elderId) {
        List<Map<String, Object>> res = outRecordService.getElderRecord(elderId);
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    //新增外出记录
    @ApiOperation(value = "新增外出记录")
    @PostMapping("/addOutRecord")
    public Result addOutRecord(@RequestBody @Validated OutRecord outRecord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            if (elderService.getOneElder(outRecord.getElderId()) != null) {
                List<Map<String, Object>> res = outRecordService.getElderRecord(outRecord.getElderId());
                if (res != null && !res.isEmpty()) {
                    for (Map<String, Object> map : res) {
                        Set<String> set = map.keySet();
                        for (String key : set) {
                             //System.out.println("key=:"+key+",value="+map.get(key));
                            if (key.equals("backTime")) {
                                if (map.get(key) == null) {
                                    return Result.error(ResultEnum.OUTRECORD_EXIST.getCode(), ResultEnum.OUTRECORD_EXIST.getMsg());
                                } else {
                                    outRecord.setOutTime(new Date());
                                    outRecordService.addOutRecord(outRecord);
                                    return Result.success();
                                }
                            }
                        }
                    }
                } else {
                    outRecord.setOutTime(new Date());
                    outRecordService.addOutRecord(outRecord);
                    return Result.success();
                }
            } else {
                return Result.error(ResultEnum.ELDER_NOT_EXIST.getCode(), ResultEnum.ELDER_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
        return null;
    }

    //返院登记
    @ApiOperation(value = "返院登记")
    @PutMapping("/addBackRecord")
    public Result addBackRecord(@RequestParam Integer outId) {
        if (outRecordService.getOneRecord(outId).getBackTime() == null) {
            outRecordService.addBackRecord(outId);
            return Result.success();
        } else {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }

    }

    //删除外出记录可批量删除
    @ApiOperation(value = "删除外出记录")
    @DeleteMapping("/deleteRecord")
    public Result deleteRecord(@RequestParam Integer[] arr) {
        try {
            int rows = outRecordService.deleteRecord(arr);
            if (rows >= 1) {
                return Result.success();
            } else {
                return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
        }
    }

    //查询未返院的老人信息
    @ApiOperation("查询未返院老人信息")
    @GetMapping("/getNotBack")
    public Result getNotBack() {
        List<Map<String, Object>> res = outRecordService.getNotBack();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
}
