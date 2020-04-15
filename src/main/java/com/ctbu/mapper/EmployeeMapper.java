package com.ctbu.mapper;

import com.ctbu.entity.Employee;
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
public interface EmployeeMapper {
    //插入员工
    void insertEmployee(Employee employee);
    //更新员工信息
    void updateEmployee(Employee employee);
    //通过员工编号删除员工
//    void deleteEmployee(Integer id);
    //通过员工id查询员工信息
//    Map<String,Object> getEmployeeById(Integer id);
    //模糊查询
    List<Map<String,Object>> getEmp(Employee employee);
    //批量删除
    int deleteBatch(Integer[] arr);

}
