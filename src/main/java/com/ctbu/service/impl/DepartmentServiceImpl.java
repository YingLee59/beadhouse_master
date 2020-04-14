package com.ctbu.service.impl;

import com.ctbu.entity.Department;
import com.ctbu.mapper.DepartmentMapper;
import com.ctbu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> listDepartment() {
        return departmentMapper.listDepartment();
    }

    @Override
    public Department getDepartmentByDepId(Integer depId) {
        return departmentMapper.getDepartmentByDepId(depId);
    }

    @Override
    public void deleteDepartment(Integer depId) throws Exception{
        departmentMapper.deleteDepartment(depId);
    }

    @Override
    public void insertDepartment(Department department) throws Exception {
        departmentMapper.insertDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) throws Exception {
        departmentMapper.updateDep(department);
    }
}
