package com.ctbu.service;

import com.ctbu.entity.Elder;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
public interface ElderService {
//    通过不同条件查询老人信息
    List<Elder> getElder(Elder elder);
//    删除老人
    void  deleteElder(Integer id) throws Exception;
//    添加老人信息
    int insertElder(Elder elder) throws Exception;
//    更新老人信息
    int updateElder(Elder elder) throws Exception;
    //通过老人编号获取老人信息
    Elder getOneElder(Integer id);
}
