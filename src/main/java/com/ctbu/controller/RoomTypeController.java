package com.ctbu.controller;

import com.ctbu.entity.RoomType;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.RoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@RestController
@RequestMapping("/roomtype")
@Api(description = "房间类型管理")
public class RoomTypeController {
    @Autowired
    RoomTypeService roomTypeService;

    //查询所有房间的类型
    @GetMapping("/getAllRoomType")
    @ApiOperation(value = "查询所有房间的类型")
    public Result getAllRoomType() {
        List<RoomType> res = roomTypeService.getAllRoomType();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    //通过编号查询房间类型
    @GetMapping("/getOneType")
    @ApiOperation(value = "通过编号查询房间类型")
    public Result getOneType(Integer roomType) {
        RoomType res = roomTypeService.getOneType(roomType);
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }

    }

    //添加房间类型
    @PostMapping("/insertRoomType")
    @ApiOperation(value = "添加房间类型")
    public Result insertRoomType(@RequestBody @Validated RoomType roomType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            roomTypeService.insertRoomType(roomType);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultEnum.DATA_EXIST.getCode(), ResultEnum.DATA_EXIST.getMsg());
        }
    }

    //通过编号删除房间类型
    @DeleteMapping("/deleteRoomType")
    @ApiOperation(value = "删除房间类型")
    public Result deleteRoomType(@RequestParam Integer roomType) {
        try {
            roomTypeService.deleteRoomType(roomType);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());

        }
    }

    //通过编号修改房间类型
    @PutMapping("/updateRoomType")
    @ApiOperation(value = "修改房间类型")
    public Result updateRoomType(@RequestBody @Validated RoomType roomType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            roomTypeService.updateRoomType(roomType);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(ResultEnum.DATA_EXIST.getCode(), ResultEnum.DATA_EXIST.getMsg());
        }
    }
}
