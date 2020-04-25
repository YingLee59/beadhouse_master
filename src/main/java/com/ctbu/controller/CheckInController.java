package com.ctbu.controller;

import com.ctbu.entity.CheckIn;
import com.ctbu.mapper.CheckInMapper;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.CheckInService;
import com.ctbu.service.ElderService;
import com.ctbu.service.RoomService;
import com.ctbu.service.RoomTypeService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author LIYING
 */
@RestController
@RequestMapping("checkin")
@Api(description = "入住管理")
public class CheckInController {
    @Autowired
    CheckInService checkInService;
    @Autowired
    ElderService elderService;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomTypeService roomTypeService;

    //查询入住记录
    @GetMapping("/getCheckIn")
    @ApiOperation(value = "查询入住记录")
    public Result getCheckIn(CheckIn checkIn) {
        Integer cId = checkIn.getCheckId();
        Integer eId = checkIn.getElderId();
        String rId = checkIn.getRoomId();
        String cheDate=checkIn.getCheckDate();
        String newDate=checkIn.getNewCheckDate();
        String outDate=checkIn.getOutDate();
        List<Map<String, Object>> res = checkInService.getElder();
        if (res != null && !res.isEmpty()) {
            for(Map<String,Object> map:res){
                Set<String> set = map.keySet();
                for (String key : set) {
                    if(key.equals("checkId")){
                        //获取每条记录的id
                        Integer num=(Integer) map.get(key);
                        checkIn.setCheckId(num);
                        //获取入住日期
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date d2 = null;
                        if (checkInService.getCheckInById(num).getNewCheckDate() == null) {
                            try {
                                d2 = sdf.parse(checkInService.getCheckInById(num).getCheckDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                d2 = sdf.parse(checkInService.getCheckInById(num).getNewCheckDate());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        //查询日期
                        String newCheckDate = sdf.format(new Date());
                        Date d1 = null;
                        try {
                            d1 = sdf.parse(newCheckDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //获取房间类型
                        int roomType = roomService.getType(checkInService.getCheckInById(num).getRoomId());
                        //获取房间类型相应的收费标准
                        double fee = roomTypeService.getOneType(roomType).getFee();
                        //计算前期费用
                        long Millisecond = (d1.getTime() - d2.getTime());
                        double month = (double) Millisecond / 1000 / 60 / 60 / 24 / 30;
                        double cost = fee * month+checkInService.getCheckInById(num).getCost();
                        //计算余额
                        double leftBalance = checkInService.getCheckInById(num).getBalance() - fee * month;
                        checkIn.setNewCheckDate(newCheckDate);
                        checkIn.setCost(cost);
                        checkIn.setBalance(leftBalance);
                        try {
                            checkInService.updateFee(checkIn);
                        } catch (Exception e) {
                            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
                        }
                    }
                }
            }
            checkIn.setCheckId(cId);
            checkIn.setElderId(eId);
            checkIn.setRoomId(rId);
            checkIn.setCheckDate(cheDate);
            checkIn.setNewCheckDate(newDate);
            checkIn.setOutDate(outDate);
            List<Map<String, Object>> res1 = checkInService.getCheckIn(checkIn);
            return Result.success(res1);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    @PostMapping("/addCheckIn")
    @ApiOperation(value = "新增入住")
    //新增入住记录
    public Result addCheckIn(@RequestBody @Validated CheckIn checkIn, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {//判断入住老人是否存在
            if (elderService.getOneElder(checkIn.getElderId()) != null) {
                //判断老人是否已入住
                List<CheckIn> listCheckIn = checkInService.getCheckInByElderId(checkIn.getElderId());
                if (listCheckIn != null && !listCheckIn.isEmpty() && listCheckIn.get(0).getOutDate() == null) {
                    return Result.error(ResultEnum.CHECKIN_EXIST.getCode(), ResultEnum.CHECKIN_EXIST.getMsg());
                    //判断入住房间是否存在
                } else if (roomService.getRoom(checkIn.getRoomId()) != null && !roomService.getRoom(checkIn.getRoomId()).isEmpty()) {
                    //判断房间是否已住满
                    List<Map<String, Object>> res = roomService.getFree();
                    for (Map<String, Object> map : res) {
                        Set<String> set = map.keySet();
                        for (String key : set) {
                            if (key.equals("roomId")) {
                                if (map.get(key).equals(checkIn.getRoomId())) {
                                    int roomType = roomService.getType(checkIn.getRoomId());
                                    double fee = roomTypeService.getOneType(roomType).getFee();
                                    //预存十二个月的入住费用
                                    checkIn.setPrepaid(12 * fee);
                                    checkIn.setBalance(checkIn.getPrepaid());
                                    checkInService.addCheckIn(checkIn);
                                    //如果该房间的入住人数到达最大入住人数
                                    if (roomTypeService.getOneType(roomType).getMaxNum() == checkInService.getNum(checkIn.getRoomId())) {
                                        roomService.updateStateFull(checkIn.getRoomId());
                                    }
                                    return Result.success();
                                }
                            }
                        }
                    }
                    return Result.error(ResultEnum.ROOM_FULL.getCode(), ResultEnum.ROOM_FULL.getMsg());
                } else {
                    return Result.error(ResultEnum.ROOM_NOT_EXIST.getCode(), ResultEnum.ROOM_NOT_EXIST.getMsg());
                }
            } else {
                return Result.error(ResultEnum.ELDER_NOT_EXIST.getCode(), ResultEnum.ELDER_NOT_EXIST.getMsg());
            }

        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
    }

    //换房间
    @PutMapping("/changRoom")
    @ApiOperation(value = "换房间")
    public Result changeRoom(@RequestBody @Validated CheckIn checkIn, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            //判断入住房间是否存在
            if (roomService.getRoom(checkIn.getRoomId()) != null && !roomService.getRoom(checkIn.getRoomId()).isEmpty()) {
                //判断房间是否已住满
                List<Map<String, Object>> res = roomService.getFree();
                for (Map<String, Object> map : res) {
                    Set<String> set = map.keySet();
                    for (String key : set) {
                        if (key.equals("roomId")) {
                            if (map.get(key).equals(checkIn.getRoomId())) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                //判断之前有没有过换房记录
                                Date d2 = null;
                                if (checkInService.getCheckInById(checkIn.getCheckId()).getNewCheckDate() == null) {
                                    d2 = sdf.parse(checkInService.getCheckInById(checkIn.getCheckId()).getCheckDate());
                                } else {
                                    d2 = sdf.parse(checkInService.getCheckInById(checkIn.getCheckId()).getNewCheckDate());
                                }
                                String dateStr = sdf.format(new Date());
                                Date d1 = sdf.parse(dateStr);
                                long Millisecond = (d1.getTime() - d2.getTime());
                                double month = (double) Millisecond / 1000 / 60 / 60 / 24 / 30;
                                //获得换房前类型
                                int roomType = roomService.getType(checkInService.getCheckInById(checkIn.getCheckId()).getRoomId());
                                double fee = roomTypeService.getOneType(roomType).getFee();
                                //获取退房前房号
                                String roomNum = checkInService.getCheckInById(checkIn.getCheckId()).getRoomId();
                                //计算换房前花费的金额
                                double fees = month * fee;
                                //checkInService.getCheckInById(checkId).setNewCheckDate(d1);
                                checkIn.setCost(fees+checkInService.getCheckInById(checkIn.getCheckId()).getCost());
                                checkIn.setBalance(checkInService.getCheckInById(checkIn.getCheckId()).getBalance() - fees);
                                checkInService.changeRoom(checkIn);
                                //将退住房间改为空闲
                                roomService.updateStateFree(roomNum);
                                //如果新入住房间的入住人数到达最大入住人数
                                if (roomTypeService.getOneType(roomService.getType(checkIn.getRoomId())).getMaxNum() == checkInService.getNum(checkIn.getRoomId())) {
                                    roomService.updateStateFull(checkIn.getRoomId());
                                }
                                return Result.success();
                            }
                        }
                    }
                }
                return Result.error(ResultEnum.ROOM_FULL.getCode(), ResultEnum.ROOM_FULL.getMsg());
            } else {
                return Result.error(ResultEnum.ROOM_NOT_EXIST.getCode(), ResultEnum.ROOM_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }
    }


    //续住
    @PutMapping("/updateCheckIn")
    @ApiOperation(value = "续住")
    public Result updateCheckIn(@RequestBody @Validated CheckIn checkIn, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            //获取入住日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d2 = null;
            if (checkInService.getCheckInById(checkIn.getCheckId()).getNewCheckDate() == null) {
                d2 = sdf.parse(checkInService.getCheckInById(checkIn.getCheckId()).getCheckDate());
            } else {
                d2 = sdf.parse(checkInService.getCheckInById(checkIn.getCheckId()).getNewCheckDate());
            }
            //续住日期
            String newCheckDate = sdf.format(new Date());
            Date d1 = sdf.parse(newCheckDate);
            //获取房间类型
            int roomType = roomService.getType(checkInService.getCheckInById(checkIn.getCheckId()).getRoomId());
            //获取房间类型相应的收费标准
            double fee = roomTypeService.getOneType(roomType).getFee();
            //计算前期费用
            long Millisecond = (d1.getTime() - d2.getTime());
            double month = (double) Millisecond / 1000 / 60 / 60 / 24 / 30;
            double cost = fee * month+checkInService.getCheckInById(checkIn.getCheckId()).getCost();
            //计算余额
            double leftBalance = checkInService.getCheckInById(checkIn.getCheckId()).getBalance() - fee * month;
            //办理续住
            double balance = checkIn.getBalance() + leftBalance;
            checkIn.setNewCheckDate(newCheckDate);
            checkIn.setCost(cost);
            checkIn.setBalance(balance);
            checkInService.updateCheckIn(checkIn);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }
    }

    //退房
    @PutMapping("/deleteCheckIn")
    @ApiOperation(value = "退房")
    public Result deleteCheckIn(@RequestBody @Validated CheckIn checkIn, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            //结算余额
            //获取入住日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d2 = null;
            if (checkInService.getCheckInById(checkIn.getCheckId()).getNewCheckDate() == null) {
                d2 = sdf.parse(checkInService.getCheckInById(checkIn.getCheckId()).getCheckDate());
            } else {
                d2 = sdf.parse(checkInService.getCheckInById(checkIn.getCheckId()).getNewCheckDate());
            }
            //退住日期
            String outDate = sdf.format(new Date());
            Date d1 = sdf.parse(outDate);
            //获取房间类型
            int roomType = roomService.getType(checkInService.getCheckInById(checkIn.getCheckId()).getRoomId());
            //获取房间类型相应的收费标准
            double fee = roomTypeService.getOneType(roomType).getFee();
            //计算前期费用
            long Millisecond = (d1.getTime() - d2.getTime());
            double month = (double) Millisecond / 1000 / 60 / 60 / 24 / 30;
            double cost = fee * month+checkInService.getCheckInById(checkIn.getCheckId()).getCost();
            //计算余额
            double leftBalance = checkInService.getCheckInById(checkIn.getCheckId()).getBalance() - fee * month;
            checkIn.setCost(cost);
            checkIn.setBalance(leftBalance);
            //退房
            String roomId = checkInService.getCheckInById(checkIn.getCheckId()).getRoomId();
            checkInService.deleteCheckIn(checkIn);
            //将退房的房间状态改为空闲
            roomService.updateStateFree(roomId);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
        }
    }
    //查询未退院老人
    @ApiOperation(value="查询在住老人")
    @GetMapping("/getElder")
    public Result getElder(){
        List<Map<String, Object>> res = checkInService.getElder();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        }else{
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(),ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
}
