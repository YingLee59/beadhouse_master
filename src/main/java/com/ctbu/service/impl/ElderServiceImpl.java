package com.ctbu.service.impl;

import com.ctbu.entity.Elder;
import com.ctbu.mapper.ElderMapper;
import com.ctbu.service.ElderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class ElderServiceImpl implements ElderService {
    @Autowired
    ElderMapper elderMapper;

    //    通过不同条件查询老人信息
    @Override
    public List<Elder> getElder(Elder elder) {
        return elderMapper.getElder(elder);
    }

    @Override
    public int deleteElder(Integer[] arr) throws Exception{
        return elderMapper.deleteElder(arr);
    }

    @Override
    public int insertElder(Elder elder) throws Exception {
        return elderMapper.insertElder(elder);
    }

    @Override
    public int updateElder(Elder elder) throws Exception {
        return elderMapper.updateElder(elder);
    }
    public Elder getOneElder(Integer id){
        return elderMapper.getOneElder(id);
    }
}
