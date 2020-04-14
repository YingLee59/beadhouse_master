package com.ctbu.service;

import com.ctbu.entity.Department;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
public interface DepartmentService {
    List<Department> listDepartment();
    Department getDepartmentByDepId(Integer depId);
    void deleteDepartment(Integer depId) throws Exception;
    void insertDepartment(Department department) throws  Exception;
    void updateDepartment(Department department) throws Exception;
}
