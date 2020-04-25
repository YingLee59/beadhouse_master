package com.ctbu.service;

import com.ctbu.entity.Room;

import java.util.List;
import java.util.Map;

/**
 * @Description 房间service层
 * @Author LIYING
 */
public interface RoomService {
    //通过房间编号查询
    Map<String,Object> getRoom(String roomId);
    //通过房间类型编号查询
    List<Map<String,Object>> getRoomByType(Integer roomType);
    //查询空闲房间
    List<Map<String,Object>> getFree();
    //新增房间
    void addRoom(Room room)throws Exception;
    //通过房间编号删除房间
    void deleteRoom(String roomId)throws Exception;
    //修改房间信息
    void updateRoom(Room room)throws Exception;
    //修改房间状态
    void updateStateFull(String roomId)throws Exception;
    //通过房间编号查询房间类型编号
    int getType(String roomId);
    //修改房间状态（空闲）
    void updateStateFree(String roomId)throws Exception;
    //查询所有房间
    List<Map<String,Object>> getAll();
}
