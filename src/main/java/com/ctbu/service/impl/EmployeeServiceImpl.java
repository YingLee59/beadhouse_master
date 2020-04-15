package com.ctbu.service.impl;

import com.ctbu.entity.Employee;
import com.ctbu.mapper.EmployeeMapper;
import com.ctbu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public void insertEmployee(Employee employee) throws Exception {
        employeeMapper.insertEmployee(employee);
    }
    public void updateEmployee(Employee employee) throws Exception{
        employeeMapper.updateEmployee(employee);
    }
//    public void deleteEmployee(Integer id) throws Exception{
//        employeeMapper.deleteEmployee(id);
//    }

//    public Map<String, Object> getEmployeeById(Integer id) {
//        return employeeMapper.getEmployeeById(id);
//    }

    public List<Map<String,Object>> getEmp(Employee employee){
        return employeeMapper.getEmp(employee);
    }

    @Override
    public int deleteBatch(Integer[] arr) throws Exception {
        return employeeMapper.deleteBatch(arr);
    }

}
