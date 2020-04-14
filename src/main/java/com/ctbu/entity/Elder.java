package com.ctbu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @Description
 * @Author LIYING
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Elder {
    private String relaName;//亲属名称
    private String relaPhnoe;//亲属电话
}
