package com.ctbu.service;

import com.ctbu.entity.Health;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
public interface HealthService {
    //查询老人健康档案（可模糊查询）
    List<Map<String,Object>> getHealth(Health health);
    //新增老人健康档案
    void insertHealth(Health health) throws Exception;
    //更新老人健康档案
    void updateHealth(Health health) throws Exception;
}
