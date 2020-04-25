package com.ctbu.service.impl;

import com.ctbu.entity.CheckIn;
import com.ctbu.mapper.CheckInMapper;
import com.ctbu.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class CheckInServiceImpl implements CheckInService {
    @Autowired
    CheckInMapper checkInMapper;
    @Override
    public List<Map<String, Object>> getCheckIn(CheckIn checkIn) {
        return checkInMapper.getCheckIn(checkIn);
    }

    @Override
    public List<CheckIn> getCheckInByRoomId(String roomId) {
        return checkInMapper.getCheckInByRoomId(roomId);
    }

    @Override
    public void addCheckIn(CheckIn checkIn) throws Exception {
        checkInMapper.addCheckIn(checkIn);
    }

    @Override
    public List<CheckIn> getCheckInByElderId(Integer elderId) {
        return checkInMapper.getCheckInByElderId(elderId);
    }

    @Override
    public void changeRoom(CheckIn checkIn) throws Exception {
        checkInMapper.changeRoom(checkIn);
    }

    @Override
    public void updateCheckIn(CheckIn checkIn ) throws Exception {
        checkInMapper.updateCheckIn(checkIn);
    }

    @Override
    public int getNum(String roomId) {
        return checkInMapper.getNum(roomId);
    }

    @Override
    public void deleteCheckIn(CheckIn checkIn) throws Exception {
        checkInMapper.deleteCheckIn(checkIn);
    }

    @Override
    public CheckIn getCheckInById(Integer checkId) {
        return checkInMapper.getCheckInById(checkId);
    }

    @Override
    public void updateFee(CheckIn checkIn) throws Exception {
        checkInMapper.updateFee(checkIn);
    }

    @Override
    public List<Map<String, Object>> getElder() {
        return checkInMapper.getElder();
    }
}
