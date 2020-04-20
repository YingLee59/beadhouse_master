package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer depId;
    @NotBlank(message="部门名称不能为空")
    private String depName;
}
