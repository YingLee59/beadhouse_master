package com.ctbu.service.impl;

import com.ctbu.entity.RoomType;
import com.ctbu.mapper.RoomTypeMapper;
import com.ctbu.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class RoomTypeImpl implements RoomTypeService {
    @Autowired
    RoomTypeMapper roomTypeMapper;
    @Override
    public List<RoomType> getAllRoomType() {
        return roomTypeMapper.getAllRoomType();
    }

    @Override
    public RoomType getOneType(Integer roomType) {
        return roomTypeMapper.getOneType(roomType);
    }

    @Override
    public void insertRoomType(RoomType roomType) throws Exception {
       roomTypeMapper.insertRoomType(roomType);
    }

    @Override
    public void deleteRoomType(Integer roomType) throws Exception {
         roomTypeMapper.deleteRoomType(roomType);
    }

    @Override
    public void updateRoomType(RoomType roomType) throws Exception {
         roomTypeMapper.updateRoomType(roomType);
    }
}
