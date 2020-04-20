package com.ctbu.mapper;

import com.ctbu.entity.OutRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Mapper
@Component
public interface OutRecordMapper {
    //查询全部外出记录
    List<Map<String,Object>> getAllOutRecord();
    //通过老人编号查询外出记录
    List<Map<String,Object>> getElderRecord(Integer elderId);
    //新增外出记录
    void addOutRecord(OutRecord outRecord) throws Exception;
    //返院登记
    void addBackRecord(Integer outId);
    //删除外出记录可批量删除
    int deleteRecord(Integer[] arr);
    //通过外出编号查询外出记录
    OutRecord getOneRecord(Integer outId);
    //查询未返院的老人信息
    List<Map<String,Object>> getNotBack();
}
