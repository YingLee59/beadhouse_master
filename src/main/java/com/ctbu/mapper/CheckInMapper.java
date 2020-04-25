package com.ctbu.mapper;

import com.ctbu.entity.CheckIn;
import com.ctbu.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Component
@Mapper
public interface CheckInMapper {
    //查询入住记录
    List<Map<String,Object>> getCheckIn(CheckIn checkIn);
    //通过房间号查询入住记录
    List<CheckIn> getCheckInByRoomId(String roomId);
    //新增入住记录
    void addCheckIn(CheckIn checkIn) throws Exception;
    //通过老人编号查询入住记录
    List<CheckIn>getCheckInByElderId(Integer elderId);
    //换房间
    void changeRoom(CheckIn checkIn) throws Exception;
    //续住
    void updateCheckIn(CheckIn checkIn) throws Exception;
    //查询房间的入住人数
    int getNum(String roomId);
    //退房
    void deleteCheckIn(CheckIn checkIn) throws Exception;
    //通过入住编号查询入住记录
    CheckIn getCheckInById(Integer checkId);
    //修改已用费用和余额
    void updateFee(CheckIn checkIn) throws Exception;
    //查询未退院老人
    List<Map<String,Object>> getElder();
}
