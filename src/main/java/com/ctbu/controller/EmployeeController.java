package com.ctbu.controller;

import com.ctbu.entity.Employee;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 员工controller层
 * @Author LIYING
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/listemployee")
    public Result listeEmployees() {
        List<Employee> res = employeeService.listEmployees();
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    @PostMapping("/insertemployee")
    public Result insertEmployee(@RequestBody Employee employee) {
        try {
            employeeService.insertEmployee(employee);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
    }

    @PutMapping("/updateemployee")
    public Result updateEmployee(@RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(employee);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }
    }

    @GetMapping("/getEmployeeById")
    public Result getEmployeeById(String id) {
        Employee res = employeeService.getEmployeeById(id);
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    @DeleteMapping("/deleteemployee")
    public Result deleteEmployee(String id){
        try {
            Employee res=employeeService.getEmployeeById(id);
            if(res!=null){
                employeeService.deleteEmployee(id);
                return Result.success();
            }
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(),ResultEnum.DATA_IS_NULL.getMsg());
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(),ResultEnum.DELETE_FAIL.getMsg());
        }
    }
}
