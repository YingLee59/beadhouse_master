package com.ctbu.service.impl;

import com.ctbu.entity.Leave;
import com.ctbu.mapper.LeaveMapper;
import com.ctbu.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class LeaveServiceImpl  implements LeaveService {
    @Autowired
    LeaveMapper leaveMapper;
    @Override
    public List<Map<String, Object>> getLeave() {
        return leaveMapper.getLeave();
    }

    @Override
    public List<Map<String, Object>> getNotBack() {
        return leaveMapper.getNotBack();
    }

    @Override
    public void addLeave(Leave leave) throws Exception {
        leaveMapper.addLeave(leave);
    }

    @Override
    public void addBack(Integer leaveId) {
        leaveMapper.addBack(leaveId);
    }

    @Override
    public void deleteLeave(Integer[] arr) throws Exception {
        leaveMapper.deleteLeave(arr);
    }

    @Override
    public Leave getOneLeave(Integer leaveId) {
        return leaveMapper.getOneLeave(leaveId);
    }

    @Override
    public List<Map<String,Object>> getLeaveByEmpId(Integer employeeId) {
        return leaveMapper.getLeaveByEmpId(employeeId);
    }
}
