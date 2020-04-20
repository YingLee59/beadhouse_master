package com.ctbu.controller;

import com.ctbu.entity.Leave;
import com.ctbu.result.Result;
import com.ctbu.result.ResultEnum;
import com.ctbu.service.EmployeeService;
import com.ctbu.service.LeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 员工请假controller类
 * @Author LIYING
 */
@RestController
@RequestMapping("/leave")
@Api(description = "员工请假管理")
public class LeaveController {
    @Autowired
    LeaveService leaveService;
    @Autowired
    EmployeeService employeeService;

    //查询员工请假信息
    @GetMapping("/getLeave")
    @ApiOperation(value = "查询员工请假信息")
    public Result getLeave() {
        List<Map<String, Object>> res = leaveService.getLeave();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    //查询未销假员工
    @GetMapping("/getNotBack")
    @ApiOperation(value = "查询未销假员工")
    public Result getNotBack() {
        List<Map<String, Object>> res = leaveService.getNotBack();
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

    //新增请假记录
    @PostMapping("/addLeave")
    @ApiOperation(value = "添加员工请假记录")
    public Result addLeave(@RequestBody @Validated Leave leave, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(ResultEnum.BIND_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            if (employeeService.getEmployeeById(leave.getEmployeeId()) != null) {
                List<Map<String, Object>> res = leaveService.getLeaveByEmpId(leave.getEmployeeId());
                if (res != null && !res.isEmpty()) {
                    for (Map<String, Object> map : res) {
                        Set<String> set = map.keySet();
                        for (String key : set) {
                            //System.out.println("key=:"+key+",value="+map.get(key));
                            if (key.equals("backDate")) {
                                if (map.get(key) == null) {
                                    return Result.error(ResultEnum.LEAVE_EXIST.getCode(), ResultEnum.LEAVE_EXIST.getMsg());
                                } else {
                                    leaveService.addLeave(leave);
                                    return Result.success();
                                }
                            }
                        }
                    }
                } else {
                    leaveService.addLeave(leave);
                    return Result.success();
                }
            } else {
                return Result.error(ResultEnum.EMPLOYEE_NOT_EXIST.getCode(), ResultEnum.EMPLOYEE_NOT_EXIST.getMsg());
            }
        } catch (Exception e) {
            return Result.error(ResultEnum.INSERT_FAIL.getCode(), ResultEnum.INSERT_FAIL.getMsg());
        }
        return null;
    }

    //员工销假
    @PutMapping("/addBack")
    @ApiOperation(value = "员工销假")
    public Result addBack(@RequestParam Integer leaveId) {
        if (leaveService.getOneLeave(leaveId).getBackDate() == null) {
            leaveService.addBack(leaveId);
            return Result.success();
        } else {
            return Result.error(ResultEnum.UPDATE_FAIL.getCode(), ResultEnum.UPDATE_FAIL.getMsg());
        }
    }

    //删除员工请假记录（可批量删除）
    @DeleteMapping("/deleteLeave")
    @ApiOperation(value = "删除请假记录")
    public Result deleteLeave(@RequestParam Integer[] arr) {
        try {
            leaveService.deleteLeave(arr);
            return Result.success();
        } catch (Exception e) {
            return Result.error(ResultEnum.DELETE_FAIL.getCode(), ResultEnum.DELETE_FAIL.getMsg());
        }
    }
    //通过员工编号查询员工的请假情况
    @GetMapping("/getLeaveByEmpId")
    @ApiOperation(value="通过员工编号查询员工请假记录")
    public Result getLeaveByEmpId(Integer employeeId){
        List<Map<String, Object>> res = leaveService.getLeaveByEmpId(employeeId);
        if (res != null && !res.isEmpty()) {
            return Result.success(res);
        } else {
            return Result.error(ResultEnum.DATA_IS_NULL.getCode(), ResultEnum.DATA_IS_NULL.getMsg());
        }
    }

}
