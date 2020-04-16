package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description  老人健康档案管理
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Health {
    private Integer healthId;//健康档案编号
    @NotNull(message="编号不能为空")
    @Min(value = 1,message="编号不能小于0")
    private Integer elderId;//老人编号
    @NotBlank(message="血型不能为空")
    private String blood;//血型
    @NotBlank(message="自理能力不能为空")
    private String selfCare;//自理能力
    @NotBlank(message="相关病史不能为空")
    private String medicalHistory;//相关病史
    @NotBlank(message="过敏史不能为空")
    private String allergy;//过敏史


}
