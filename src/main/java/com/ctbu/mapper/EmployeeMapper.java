package com.ctbu.mapper;

import com.ctbu.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@Mapper
@Component
public interface EmployeeMapper {
    //查询所有员工
    List<Employee> listEmployees();
    //插入员工
    void insertEmployee(Employee employee);
    //更新员工信息
    void updateEmployee(Employee employee);
    //通过员工编号删除员工
    void deleteEmployee(String id);
    //通过员工id查询员工信息
    Employee getEmployeeById(String id);
}
