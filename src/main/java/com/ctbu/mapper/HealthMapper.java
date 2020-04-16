package com.ctbu.mapper;

import com.ctbu.entity.Health;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Component
@Mapper
public interface HealthMapper {
    //查询老人健康档案（可模糊查询）
    List<Map<String,Object>> getHealth(Health health);
    //新增老人健康档案
    void insertHealth(Health health) throws Exception;
    //更新老人健康档案
    void updateHealth(Health health) throws Exception;
//    当老人信息被删除时，老人相关的健康档案会被同步删除，
//    故此处没有提供相应的删除操作
}
