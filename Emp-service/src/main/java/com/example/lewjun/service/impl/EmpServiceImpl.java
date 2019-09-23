package com.example.lewjun.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.lewjun.dto.EmpDTO;
import com.example.lewjun.mapper.EmpMapper;
import com.example.lewjun.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author LewJun
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public int save(EmpDTO emp) {
        return empMapper.save(emp);
    }

    @Override
    public int del(Serializable pk) {
        return empMapper.del(pk);
    }

    @Override
    public int update(EmpDTO emp) {
        return empMapper.update(emp);
    }

    @Override
    public List<EmpDTO> findAll() {
        return empMapper.findAll();
    }

    @Override
    public EmpDTO findByPk(Serializable pk) {
        return empMapper.findByPk(pk);
    }
}
