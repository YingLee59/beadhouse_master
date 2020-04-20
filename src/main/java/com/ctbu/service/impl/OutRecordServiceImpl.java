package com.ctbu.service.impl;

import com.ctbu.entity.OutRecord;
import com.ctbu.mapper.OutRecordMapper;
import com.ctbu.service.OutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class OutRecordServiceImpl implements OutRecordService {
    @Autowired
    OutRecordMapper outRecordMapper;
    @Override
    public List<Map<String, Object>> getAllOutRecord() {
        return outRecordMapper.getAllOutRecord();
    }

    @Override
    public List<Map<String, Object>> getElderRecord(Integer elderId) {
        return outRecordMapper.getElderRecord(elderId);
    }

    @Override
    public void addOutRecord(OutRecord outRecord) throws Exception {
        outRecordMapper.addOutRecord(outRecord);
    }

    @Override
    public void addBackRecord(Integer outId) {
        outRecordMapper.addBackRecord(outId);
    }

    @Override
    public int deleteRecord(Integer[] arr) throws Exception {
        return outRecordMapper.deleteRecord(arr);
    }

    @Override
    public List<Map<String, Object>> getNotBack() {
        return outRecordMapper.getNotBack();
    }

    @Override
    public OutRecord getOneRecord(Integer outId) {
        return outRecordMapper.getOneRecord(outId);
    }
}
