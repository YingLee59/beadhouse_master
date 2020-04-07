package com.ctbu.service.impl;

import com.ctbu.entity.Employee;
import com.ctbu.mapper.EmployeeMapper;
import com.ctbu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> listEmployees() {
        return employeeMapper.listEmployees();
    }

    public void insertEmployee(Employee employee) throws Exception {
        employeeMapper.insertEmployee(employee);
    }
    public void updateEmployee(Employee employee) throws Exception{
        employeeMapper.updateEmployee(employee);
    }
    public Employee getEmployeeById(String id) {
        return employeeMapper.getEmployeeById(id);
    }
    public void deleteEmployee(String id) throws Exception{
        employeeMapper.deleteEmployee(id);
    }

}
