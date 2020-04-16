package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 房间实体类
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private String roomId;//房间号
    private Integer roomType;//房间类型
    private String state;//房间状态
}
