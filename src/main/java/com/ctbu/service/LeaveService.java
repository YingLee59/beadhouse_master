package com.ctbu.service;

import com.ctbu.entity.Leave;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 员工请假Service
 * @Author LIYING
 */
public interface LeaveService {
    //查询员工请假信息（支持模糊查询）
    List<Map<String,Object>> getLeave();
    //查询未销假员工
    List<Map<String,Object>> getNotBack();
    //新增请假记录
    void addLeave(Leave leave) throws Exception;
    //销假
    void addBack(Integer leaveId);
    //删除员工请假记录（可批量删除）
    void deleteLeave(Integer[] arr) throws Exception;
    //通过请假编号查询请假信息
    Leave getOneLeave(Integer leaveId);
    //通过员工编号查询员工的请假情况
    List<Map<String,Object>> getLeaveByEmpId(Integer employeeId);
}
