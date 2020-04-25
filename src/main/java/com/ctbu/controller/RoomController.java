package com.ctbu.controller;

import com.ctbu.entity.Room;
import com.ctbu.entity.RoomType;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.CheckInService;
import com.ctbu.service.RoomService;
import com.ctbu.service.RoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description 房间controller层
 * @Author LIYING
 */
@RestController
@RequestMapping("/room")
@Api(description = "房间管理")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    CheckInService checkInService;
    //通过房间编号查询
    @GetMapping("/getRoom")
    @ApiOperation(value="通过房间编号查询")
    public Result  getRoom(String roomId){
        Map<String,Object> res = roomService.getRoom(roomId);
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    //通过房间类型编号查询
    @GetMapping("/getRoomByType")
    @ApiOperation(value="通过房间类型编号查询")
    public Result  getRoomByType(Integer roomType){
        List<Map<String,Object>> res = roomService.getRoomByType(roomType);
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    //查询空闲房间
    @GetMapping("/getFree")
    @ApiOperation(value="查询空闲房间")
    public Result  getFree(){
        List<Map<String,Object>> res = roomService.getFree();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    //查询所有房间
    @GetMapping("/getAll")
    @ApiOperation(value="查询所有房间")
    public Result getAll(){
        List<Map<String,Object>> res = roomService.getAll();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    //新增房间
    @PostMapping("/addRoom")
    @ApiOperation(value="新增房间")
    public Result addRoom(@RequestBody @Validated Room room, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.error(ResultEnum.BIND_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            //当房间号不存在并且房间类型存在时，才能执行添加
            if(roomService.getRoom(room.getRoomId())!=null){
                return Result.error(ResultEnum.ROOM_EXIST.getCode(),ResultEnum.ROOM_EXIST.getMsg());
            }else if(roomTypeService.getOneType(room.getRoomType())!=null){
                roomService.addRoom(room);
                return Result.success();
            } else{
                return Result.error(ResultEnum.ROOMTYPE_NOT_EXIST.getCode(),ResultEnum.ROOMTYPE_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
    }
    //通过房间编号删除房间
    @ApiOperation(value="根据房间编号删除房间")
    @DeleteMapping("/deleteRoom")
    public Result deleteRoom(@RequestParam String roomId){
        if(checkInService.getCheckInByRoomId(roomId)!=null&&!checkInService.getCheckInByRoomId(roomId).isEmpty()&&checkInService.getNum(roomId)>=1){
            return Result.error(ResultEnum.ROOM_EXIST_ELDER.getCode(),ResultEnum.ROOM_EXIST_ELDER.getMsg());
        }else{
            try {
                roomService.deleteRoom(roomId);
                return Result.success();
            } catch (Exception e) {
                return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
            }
        }
    }
    //修改房间信息
    @PutMapping("/updateRoom")
    @ApiOperation(value="修改房间信息")
    public Result updateRoom(@RequestBody @Validated Room room,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.error(ResultEnum.BIND_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            if(roomTypeService.getOneType(room.getRoomType())!=null){
                roomService.updateRoom(room);
                return Result.success();
            } else{
                return Result.error(ResultEnum.ROOMTYPE_NOT_EXIST.getCode(),ResultEnum.ROOMTYPE_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.ROOM_EXIST.getCode(),ResultEnum.ROOM_EXIST.getMsg());
        }
    }
}
