package com.ctbu.service;

import com.ctbu.entity.Employee;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
public interface EmployeeService {
   List<Employee> listEmployees();

   void insertEmployee(Employee employee) throws Exception;

   void updateEmployee(Employee employee) throws Exception;

   Employee getEmployeeById(String id);

   void deleteEmployee(String id) throws Exception;
}
