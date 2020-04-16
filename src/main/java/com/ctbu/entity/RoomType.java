package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description  房间类型
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    private Integer roomType;
    @NotBlank(message="房间类型名称不能为空")
    private String typeName;
    //收费标准
    @Digits(integer = 10,fraction = 2,message = "请输入正确收费标准（最多2位小数）")
    @NotNull(message="收费标准不能为空")
    @Min(value = 1,message="收费标准必须大于1")
    private Double fee;
    //可住人数
    @Digits(integer = 2,fraction = 0,message ="可住人数不能有小数")
    @NotNull(message="可住人数不能为空")
    @Min(value = 1,message="可住人数必须大于1")
    private Integer maxNum;
}
