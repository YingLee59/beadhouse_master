package com.ctbu.mapper;

import com.ctbu.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 房间mapper层
 * @Author LIYING
 */
@Component
@Mapper
public interface RoomMapper {
    //通过房间编号查询
    Map<String,Object> getRoom(String roomId);
    //通过房间类型编号查询
    List<Map<String,Object>> getRoomByType(Integer roomType);
    //查询空闲房间
    List<Map<String,Object>> getFree();
    //新增房间
    void addRoom(Room room) throws Exception;
    //通过房间编号删除房间
    void deleteRoom(String roomId) throws Exception;
    //修改房间信息
    void updateRoom(Room room)throws Exception;
    //修改房间状态（已满）
    void updateStateFull(String roomId)throws Exception;
    //修改房间状态（空闲）
    void updateStateFree(String roomId)throws Exception;
    //通过房间编号查询房间类型编号
    int getType(String roomId);
}
