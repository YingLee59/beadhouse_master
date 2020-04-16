package com.ctbu.service.impl;

import com.ctbu.entity.Health;
import com.ctbu.mapper.HealthMapper;
import com.ctbu.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LIYING
 */
@Service
public class HealthServiceImpl implements HealthService {
    @Autowired
    HealthMapper healthMapper;
    @Override
    public List<Map<String, Object>> getHealth(Health health) {
        return healthMapper.getHealth(health);
    }

    @Override
    public void insertHealth(Health health) throws Exception {
        healthMapper.insertHealth(health);
    }

    @Override
    public void updateHealth(Health health) throws Exception {
        healthMapper.updateHealth(health);
    }
}
