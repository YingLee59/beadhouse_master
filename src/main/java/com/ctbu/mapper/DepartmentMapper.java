package com.ctbu.mapper;

import com.ctbu.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@Mapper
@Component
public interface DepartmentMapper {
    //查询所有部门
    List<Department> listDepartment();
    //删除所有部门
    void deleteDepartment(Integer depId);
    //通过部门编号查询
    Department getDepartmentByDepId(Integer depId);
    //增加部门
    void insertDepartment(Department department);
    //修改部门信息
    void updateDep(Department department);
}
