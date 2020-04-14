package com.ctbu.controller;

import com.ctbu.entity.Employee;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description 员工controller层
 * @Author LIYING
 */
@RestController
@RequestMapping("/employee")
@Api(description = "员工信息管理")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @ApiOperation(value = "添加员工信息")
    @PostMapping("/insertemployee")
    public Result insertEmployee(@Validated Employee employee) {
        try {
            employeeService.insertEmployee(employee);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
    }

    @PutMapping("/updateemployee")
    @ApiOperation(value = "更新员工信息")
    public Result updateEmployee(@Validated Employee employee) {
        try {
            employeeService.updateEmployee(employee);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }
    }


    @DeleteMapping("/deleteemployee")
    @ApiOperation(value = "通过员工编号删除员工")
    public Result deleteEmployee(@RequestParam Integer id) {
        try {
            Map<String,Object>  res = employeeService.getEmployeeById(id);
            if (res != null&&!res.isEmpty()) {
                employeeService.deleteEmployee(id);
                return Result.success();
            }
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
        }
    }
    @ApiOperation(value = "通过不同条件查询员工信息（支持模糊查询）")
    @GetMapping("/getEmp")
    public Result getEmp(Employee employee){
        List<Map<String,Object>> res = employeeService.getEmp(employee);
        if (res != null&&!res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }
    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "通过员工编号批量删除员工")
    public Result deleteBatch(@RequestParam Integer[] arr){
        try {
                int rows = employeeService.deleteBatch(arr);
                if(rows>=1){
                    return Result.success();
                }
                else{
                    return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
                }
        } catch (Exception e){
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
        }
    }

}
