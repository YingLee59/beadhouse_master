package com.ctbu.controller;

import com.ctbu.entity.Department;
import com.ctbu.entity.Employee;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.DepartmentService;
import com.ctbu.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author LIYING
 */
@Log //lombok插件中的日志注解
@RestController
@RequestMapping("/department")
@Api(description = "部门信息管理")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/listDepartment")
    @ApiOperation(value = "查询所有部门")
    public Result listDepartment(){
        List<Department> res = departmentService.listDepartment();
        if (res != null&&!res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    @GetMapping("/getDepartmentByDepId")
    @ApiOperation(value = "通过部门编号查找部门")
    public Result getDepartmentByDepId(Integer depId){
        Department res = departmentService.getDepartmentByDepId(depId);
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    @DeleteMapping("/deleteDepartment")
    @ApiOperation(value = "删除指定部门")
    public Result deleteDepartment(@RequestParam Integer depId){
        try {
            Department res = departmentService.getDepartmentByDepId(depId);
            if (res != null) {
                departmentService.deleteDepartment(depId);
                return Result.success();
            }
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
        }

    }
    @PostMapping("/insertDepartment")
    @ApiOperation(value = "新增部门")
    public Result insertDepartment (@RequestBody @Validated Department department,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.error(ResultEnum.BIND_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            departmentService.insertDepartment(department);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.DATA_EXIST.getCode(), ResultEnum.DATA_EXIST.getMsg());
        }

    }
    @PutMapping("/updateDepartment")
    @ApiOperation(value = "更新指定编号的部门信息")
    public Result updateDepartment(@RequestBody @Validated Department department,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return Result.error(ResultEnum.BIND_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try{
            departmentService.updateDepartment(department);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.DATA_EXIST.getCode(), ResultEnum.DATA_EXIST.getMsg());
        }

    }
}
