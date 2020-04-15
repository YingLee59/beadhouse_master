package com.ctbu.mapper;

import com.ctbu.entity.Elder;
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
public interface ElderMapper {
    //通过不同条件查询老人信息
    List<Elder> getElder(Elder elder);
    //通过老人编号删除老人信息(可批量删除)
    int deleteElder(Integer[] arr) throws Exception;
    //添加老人信息
    int insertElder(Elder elder) throws Exception;
    //更新老人信息
    int updateElder(Elder elder) throws Exception;

}
