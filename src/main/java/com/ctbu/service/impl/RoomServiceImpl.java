package com.ctbu.service.impl;

import com.ctbu.entity.Room;
import com.ctbu.mapper.RoomMapper;
import com.ctbu.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomMapper roomMapper;
    @Override
    public Map<String, Object> getRoom(String roomId) {
        return roomMapper.getRoom(roomId);
    }

    @Override
    public List<Map<String, Object>> getRoomByType(Integer roomType) {
        return roomMapper.getRoomByType(roomType);
    }

    @Override
    public List<Map<String, Object>> getFree() {
        return roomMapper.getFree();
    }

    @Override
    public void addRoom(Room room) throws Exception{
        roomMapper.addRoom(room);
    }

    @Override
    public void deleteRoom(String roomId) throws Exception{
        roomMapper.deleteRoom(roomId);
    }

    @Override
    public void updateRoom(Room room) throws Exception{
        roomMapper.updateRoom(room);
    }

    @Override
    public void updateStateFull(String roomId) throws Exception{
        roomMapper.updateStateFull(roomId);
    }

    @Override
    public int getType(String roomId) {
        return roomMapper.getType(roomId);
    }

    @Override
    public void updateStateFree(String roomId) throws Exception {
        roomMapper.updateStateFree(roomId);
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return roomMapper.getAll();
    }
}
