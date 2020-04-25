package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 房间实体类
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Integer id;
    @NotBlank(message="房间号不能为空")
    private String roomId;//房间号
    @NotNull(message="编号不能为空")
    @Min(value = 1,message="编号不能小于0")
    private Integer roomType;//房间类型
    private String state;//房间状态
}
