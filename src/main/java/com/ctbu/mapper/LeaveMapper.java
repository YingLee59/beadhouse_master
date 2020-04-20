package com.ctbu.mapper;

import com.ctbu.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 员工请假
 * @Author LIYING
 */
@Component
@Mapper
public interface LeaveMapper {
    //查询员工请假信息
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
