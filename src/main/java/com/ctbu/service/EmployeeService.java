package com.ctbu.service;

import com.ctbu.entity.Employee;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
public interface EmployeeService {
   void insertEmployee(Employee employee) throws Exception;
   void updateEmployee(Employee employee) throws Exception;
//   void deleteEmployee(Integer id) throws Exception;
//   Map<String,Object> getEmployeeById(Integer id);
   List<Map<String,Object>> getEmp(Employee employee);
   int deleteBatch(Integer[] arr) throws Exception;


}
