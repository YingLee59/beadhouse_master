package com.ctbu.service;

import com.ctbu.entity.RoomType;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
public interface RoomTypeService {
    //查询所有房间的类型
    List<RoomType> getAllRoomType();
    //通过编号查询房间类型
    RoomType getOneType(Integer roomType);
    //添加房间类型
    void insertRoomType(RoomType roomType) throws Exception;
    //通过编号删除房间类型
    void deleteRoomType(Integer roomType) throws Exception;
    //通过编号修改房间类型
    void updateRoomType(RoomType roomType) throws Exception;
}
